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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

// TODO: button size adjustments

public class ScheduleController {

    @FXML private RadioButton weekRadio;
    @FXML private RadioButton monthRadio;
    @FXML private Text viewText;
    @FXML private Text actionsText;
    @FXML private Text rangeText;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button cancelButton;
    @FXML private Button leftArrow;
    @FXML private Button rightArrow;
    @FXML private TableColumn appointmentId;
    @FXML private TableColumn appointmentTitle;
    @FXML private TableColumn appointmentDescription;
    @FXML private TableColumn appointmentLocation;
    @FXML private TableColumn appointmentType;
    @FXML private TableColumn appointmentStart;
    @FXML private TableColumn appointmentEnd;
    @FXML private TableColumn appointmentCustomerId;
    @FXML private TableColumn userId;
    @FXML private TableColumn appointmentContact;
    @FXML private TableView appointmentTable;
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * Code for the Home Screen window for the application
     */
    public static void ScheduleWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleController.class.getResource("schedule-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle(Translator.ln.get("schedule"));
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Initializer for page elements
     */
    public void initialize() {
        // setup the table cells
        appointmentId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("start"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<Appointment, Date>("end"));
        appointmentCustomerId.setCellValueFactory(new PropertyValueFactory<Appointment, String>("customerId"));
        userId.setCellValueFactory(new PropertyValueFactory<Appointment, String>("userId"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contactId"));

        // set the labels for...
        // Buttons
        addButton.setText(Translator.ln.get("new"));
        updateButton.setText(Translator.ln.get("edit"));
        deleteButton.setText(Translator.ln.get("delete"));
        cancelButton.setText(Translator.ln.get("cancel"));

        // text
        viewText.setText(Translator.ln.get("viewScheduleBy"));
        actionsText.setText(Translator.ln.get("appointmentActions"));
        rangeText.setText(Translator.ln.get("currentMonth"));

        // radio buttons
        weekRadio.setText(Translator.ln.get("week"));
        monthRadio.setText(Translator.ln.get("month"));

        // Table Columns
        appointmentId.setText("ID");
        appointmentTitle.setText(Translator.ln.get("title"));
        appointmentDescription.setText(Translator.ln.get("description"));
        appointmentLocation.setText(Translator.ln.get("location"));
        appointmentType.setText(Translator.ln.get("type"));
        appointmentStart.setText(Translator.ln.get("startTime"));
        appointmentEnd.setText(Translator.ln.get("endTime"));
        appointmentCustomerId.setText(Translator.ln.get("customerId"));
        userId.setText(Translator.ln.get("userId"));
        appointmentContact.setText(Translator.ln.get("contactId"));

        // Radio button filled by default
        monthRadio.setSelected(true);

        // change element size adn locations if needed

        // fill the table view variable
        appointmentTableRefresh();

        // set the table list
        appointmentTable.setItems(appointments);
    }

    /**
     * Method to refresh the table data after edits
     */
    public static void appointmentTableRefresh() {
        // Empty the var and table
        appointments.removeAll();

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
            resultSet = statement.executeQuery(
                    "SELECT * FROM appointments"
            );

            while (resultSet.next()) {
                // Add customer to table
                appointments.add(new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"),
                        resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"),
                        resultSet.getDate("Start"), resultSet.getDate("End"), resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"), resultSet.getInt("Contact_ID")));
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
    }

    /**
     * Method to close the pop-up
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        // close the active window
        Node node = (Node) event.getSource();
        Stage active = (Stage) node.getScene().getWindow();
        active.close();
    }
}
