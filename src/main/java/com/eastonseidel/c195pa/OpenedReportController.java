package com.eastonseidel.c195pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class OpenedReportController {
    @FXML Button cancelButton;
    @FXML ComboBox contactIdBox;
    @FXML Text contactId;
    @FXML TableView reportTable;
    @FXML TableColumn<TableEvent, String> leftColumn;
    @FXML TableColumn<TableEvent, String> rightColumn;
    private static String sqlCommand;
    private static String title;
    private Dictionary<String, ObservableList> appointmentValues = new Hashtable();
    private final String[] monthNames = { Translator.ln.get("January"),  Translator.ln.get("February"),  Translator.ln.get("March"),  Translator.ln.get("April"),  Translator.ln.get("May"),  Translator.ln.get("June"),  Translator.ln.get("July"),  Translator.ln.get("August"),  Translator.ln.get("September"),  Translator.ln.get("October"),  Translator.ln.get("November"),  Translator.ln.get("December") };

    /**
     * Code for the Home Screen window for the application
     */
    public static void OpenedReportWindow(String newSqlCommand, String newTitle) throws IOException {
        sqlCommand = newSqlCommand;
        title = newTitle;

        FXMLLoader fxmlLoader = new FXMLLoader(ReportsController.class.getResource("opened-report-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();

        homeStage.setTitle(title);
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Initializer method for text and language on the page
     */
    public void initialize() {
        // set button and other value text
        cancelButton.setText(Translator.ln.get("cancel"));

        // For the contact Id's report
        ObservableList contactIds = FXCollections.observableArrayList();

        // For the others
        ObservableList reportValues = FXCollections.observableArrayList();

        if (cancelButton.getText().equals("Annuler")) {
            // Cancel Button
            cancelButton.setPrefWidth(80);
            cancelButton.setLayoutX(310);
        }

        // run Sql command
        Connection localDb;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            localDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/client_schedule",
                    "sqlUser", "Passw0rd!"
            );

            Statement statement;
            statement = localDb.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                // Add values to table
                if (title.toLowerCase().contains("contact")) {
                    Appointment temp = new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"),
                            resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"),
                            resultSet.getTimestamp("Start"), resultSet.getTimestamp("End"), resultSet.getInt("Customer_ID"),
                            resultSet.getInt("User_ID"), resultSet.getInt(  "Contact_ID"));

                    try {
                        // Maybe add to the array
                        appointmentValues.get(resultSet.getString("Contact_ID")).add(temp);
                    }
                    catch (Exception e) {
                        // Create the array and associate it with the contact ID, also add it to the contact ID list
                        ObservableList tempArray = FXCollections.observableArrayList();
                        appointmentValues.put(resultSet.getString("Contact_ID"), tempArray);
                        contactIds.add(resultSet.getString("Contact_ID"));

                        // Add the object to the new array
                        appointmentValues.get(resultSet.getString("Contact_ID")).add(temp);
                    }
                }
                else if (title.toLowerCase().contains("type")) {
                    TableEvent temp = new TableEvent(monthNames[resultSet.getInt("Month") - 1] + " - " + resultSet.getString("Type"), resultSet.getString("Total"));
                    reportValues.add(temp);
                }
                else {
                    TableEvent temp = new TableEvent(monthNames[resultSet.getInt("Month") - 1], resultSet.getString("Total"));
                    reportValues.add(temp);
                }
            }
            resultSet.close();
            statement.close();
            localDb.close();
        } catch (Exception exception) {
            String errorString = Translator.ln.get("dbFailed") + exception;

            // Log the error
            SchedulerLogger.addToLog(errorString, "severe");

            // Alert that an error occured
            // Create a popup
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Database Error!");
            errorAlert.setContentText(errorString);
            errorAlert.showAndWait();
        }

        // Show some elements if we are doing a contact report
        if (title.toLowerCase().contains("contact")) {
            // set hidden elements visible
            contactIdBox.setVisible(true);
            contactId.setVisible(true);

            // add and remove columns
            reportTable.getColumns().clear();

            // Create new columns
            TableColumn<Appointment, Integer> appointmentId = new TableColumn<>();
            TableColumn<Appointment, String> appointmentTitle = new TableColumn<>();
            TableColumn<Appointment, String> appointmentDescription = new TableColumn<>();
            TableColumn<Appointment, String> appointmentType = new TableColumn<>();
            TableColumn<Appointment, Date> appointmentStart = new TableColumn<>();
            TableColumn<Appointment, Date> appointmentEnd = new TableColumn<>();
            TableColumn<Appointment, String> appointmentCustomerId = new TableColumn<>();

            // Table Columns
            appointmentId.setText("ID");
            appointmentTitle.setText(Translator.ln.get("title"));
            appointmentDescription.setText(Translator.ln.get("description"));
            appointmentType.setText(Translator.ln.get("type"));
            appointmentStart.setText(Translator.ln.get("startTime"));
            appointmentEnd.setText(Translator.ln.get("endTime"));
            appointmentCustomerId.setText(Translator.ln.get("customerId"));
            contactId.setText(Translator.ln.get("contactId") + ": ");

            // setup the table cells
            appointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
            appointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
            appointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

            // Add columns to the table
            reportTable.getColumns().addAll(appointmentId, appointmentTitle, appointmentType, appointmentDescription, appointmentStart, appointmentEnd, appointmentCustomerId);

            // Set some items
            contactIdBox.setPromptText(contactIds.iterator().next().toString());
            contactIdBox.setItems(contactIds.sorted());
            reportTable.setItems(appointmentValues.elements().nextElement());
        }
        else {
            leftColumn.setCellValueFactory(new PropertyValueFactory<>("left"));
            rightColumn.setCellValueFactory(new PropertyValueFactory<>("right"));

            if (title.toLowerCase().contains("type")) {
                leftColumn.setText(Translator.ln.get("monthType"));
                rightColumn.setText(Translator.ln.get("totalType"));
            }
            else {
                leftColumn.setText(Translator.ln.get("month"));
                rightColumn.setText(Translator.ln.get("totalAppt"));
            }

            // Set the values in the table
            reportTable.setItems(reportValues);
        }
    }

    /**
     * Method for choosing which schedule to show based on contacts
     */
    @FXML
    protected void onComboBoxChange() {
        reportTable.setItems(appointmentValues.get(contactIdBox.getValue().toString()));
    }

    /**
     * Method to close the pop-up
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        reportTable.getItems().clear();

        // close the active window
        Node node = (Node) event.getSource();
        Stage active = (Stage) node.getScene().getWindow();
        active.close();
    }

    public static class TableEvent {

        private String left;
        private String right;

        public TableEvent (String left, String right) {
            this.left = left;
            this.right = right;
        }

        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public void setRight(String right) {
            this.right = right;
        }
    }
}
