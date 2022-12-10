package com.eastonseidel.c195pa;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class AppointmentActionsController {
    @FXML private TextField appointmentIdInput;
    @FXML private TextField appointmentTitleInput;
    @FXML private TextField appointmentDescriptionInput;
    @FXML private TextField startTimeInput;
    @FXML private TextField endTimeInput;
    @FXML private TextField appointmentLocationInput;
    @FXML private TextField appointmentTypeInput;
    @FXML private TextField appointmentCustomerIdInput;
    @FXML private TextField appointmentUserIdInput;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private DatePicker startDateInput;
    @FXML private DatePicker endDateInput;
    @FXML private ComboBox<Object> appointmentContactIdComboBox;
    @FXML private Text appointmentIdLabel;
    @FXML private Text appointmentTitleLabel;
    @FXML private Text appointmentDescriptionLabel;
    @FXML private Text startDateTime;
    @FXML private Text appointmentLocationLabel;
    @FXML private Text appointmentContactLabel;
    @FXML private Text appointmentTypeLabel;
    @FXML private Text endDateTime;
    @FXML private Text appointmentCustomerIdLabel;
    @FXML private Text appointmentUserIdLabel;
    private static List<String> contactIds = new LinkedList<>();


    private static String title;

    /**
     * edit customer window
     * @throws IOException for javafx
     */
    public static void EditAppointment(Appointment oldAppointment) throws IOException {
        title = Translator.ln.get("editAppointment");
        FXMLLoader fxmlLoader = new FXMLLoader(AppointmentActionsController.class.getResource("edit-appointment-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        pageElements(scene, oldAppointment);

        // Show the scene
        stage.setScene(scene);
        stage.show();
    }

    /**
     * New customer window
     * @throws IOException for javafx
     */
    public static void NewAppointment() throws IOException {
        title = Translator.ln.get("createAppointment");
        FXMLLoader fxmlLoader = new FXMLLoader(AppointmentActionsController.class.getResource("edit-appointment-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * initializer class
     */
    public void initialize() {
        // Set variable text
        appointmentIdLabel.setText(Translator.ln.get("id"));
        appointmentTitleLabel.setText(Translator.ln.get("title"));
        appointmentDescriptionLabel.setText(Translator.ln.get("description"));
        startDateTime.setText(Translator.ln.get("startDateTime"));
        appointmentLocationLabel.setText(Translator.ln.get("location"));
        appointmentContactLabel.setText(Translator.ln.get("contactId"));
        appointmentTypeLabel.setText(Translator.ln.get("type"));
        endDateTime.setText(Translator.ln.get("endDateTime"));
        appointmentCustomerIdLabel.setText(Translator.ln.get("customerId"));
        appointmentUserIdLabel.setText(Translator.ln.get("userId"));

        saveButton.setText(Translator.ln.get("save"));
        cancelButton.setText(Translator.ln.get("cancel"));

        appointmentIdInput.setDisable(true);

        // find the new appointment ID
        if (title.contains("Create") || title.contains("Créer")) {
            List<Integer> customerIds = new LinkedList<>();

            // grab the id's and divisions from the db
            Connection localDb;

            try {
                // Grab the country Id's
                Class.forName("com.mysql.cj.jdbc.Driver");
                localDb = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/client_schedule",
                        "sqlUser", "Passw0rd!"
                );

                Statement statement;
                statement = localDb.createStatement();
                ResultSet resultSet;
                resultSet = statement.executeQuery(
                        "SELECT Appointment_ID FROM appointments"
                );
                while (resultSet.next()) {
                    // Add username and password to dictionary
                    customerIds.add(resultSet.getInt("Appointment_ID"));
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

            // Check maximum element using for loop
            int maxValue = 0;

            for (Integer integer : customerIds) {
                if (integer > maxValue)
                    maxValue = integer;
            }

            appointmentIdInput.setText(Integer.toString(maxValue + 1));
        }

        // grab the id's and divisions from the db
        Connection localDb;
        try {
            // Grab the country Id's
            Class.forName("com.mysql.cj.jdbc.Driver");
            localDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/client_schedule",
                    "sqlUser", "Passw0rd!"
            );

            Statement statement;
            statement = localDb.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "SELECT Contact_ID FROM contacts"
            );
            while (resultSet.next()) {
                // Add username and password to dictionary
                contactIds.add(resultSet.getString("Contact_ID"));
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

        // Set the country id
        appointmentContactIdComboBox.setItems(FXCollections.observableArrayList(contactIds));

        // change the button sizes if needed
        if (cancelButton.getText().equals("Annuler")) {
            // Cancel Button
            cancelButton.setPrefWidth(100);
            cancelButton.setLayoutX(410);

            // Save Button
            saveButton.setPrefWidth(100);
            saveButton.setLayoutX(190);
        }
    }

    /**
     * Method to support the bad workaround hhhhhh
     */
    private static void pageElements(Scene scene, Appointment oldAppointment) {
        // Setup the input fields
        TextField appointmentIdInputBad = (TextField) scene.lookup("#appointmentIdInput");
        TextField appointmentTitleInputBad = (TextField) scene.lookup("#appointmentTitleInput");
        TextField appointmentDescriptionInputBad = (TextField) scene.lookup("#appointmentDescriptionInput");
        TextField startTimeInputBad = (TextField) scene.lookup("#startTimeInput");
        TextField endTimeInputBad = (TextField) scene.lookup("#endTimeInput");
        TextField appointmentLocationInputBad = (TextField) scene.lookup("#appointmentLocationInput");
        TextField appointmentTypeInputBad = (TextField) scene.lookup("#appointmentTypeInput");
        TextField appointmentCustomerIdInputBad = (TextField) scene.lookup("#appointmentCustomerIdInput");
        TextField appointmentUserIdInputBad = (TextField) scene.lookup("#appointmentUserIdInput");
        DatePicker startDateInputBad = (DatePicker) scene.lookup("#startDateInput");
        DatePicker endDateInputBad = (DatePicker) scene.lookup("#endDateInput");
        ComboBox appointmentContactIdComboBoxBad = (ComboBox) scene.lookup("#appointmentContactIdComboBox");

        // Get the date and time setup
        LocalDate startDate = oldAppointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = oldAppointment.getEnd().toLocalDateTime().toLocalDate();
        String startTime = new SimpleDateFormat("HH:mm").format(oldAppointment.getStart());
        String endTime = new SimpleDateFormat("HH:mm").format(oldAppointment.getEnd());

        // fill in the old info
        appointmentIdInputBad.setText(Integer.toString(oldAppointment.getAppointmentId()));
        appointmentTitleInputBad.setText(oldAppointment.getTitle());
        appointmentDescriptionInputBad.setText(oldAppointment.getDescription());
        startTimeInputBad.setText(startTime);
        endTimeInputBad.setText(endTime);
        appointmentLocationInputBad.setText(oldAppointment.getLocation());
        appointmentTypeInputBad.setText(oldAppointment.getType());
        appointmentCustomerIdInputBad.setText(Integer.toString(oldAppointment.getCustomerId()));
        appointmentUserIdInputBad.setText(Integer.toString(oldAppointment.getUserId()));
        startDateInputBad.setValue(startDate);
        endDateInputBad.setValue(endDate);
        appointmentContactIdComboBoxBad.setValue(contactIds.get(oldAppointment.getContactId() - 1));
    }

    /**
     * Method for saving the customer
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        // Write the updated customer to the DB
        Connection localDb;
        try {
            String sqlStatement;

            Class.forName("com.mysql.cj.jdbc.Driver");
            localDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/client_schedule",
                    "sqlUser", "Passw0rd!"
            );

            // Some Time conversion/setup
            Timestamp start = Appointment.convertToUtc(Timestamp.valueOf(startDateInput.getValue() + " " + startTimeInput.getText() + ":00"), "local");
            Timestamp end = Appointment.convertToUtc(Timestamp.valueOf(endDateInput.getValue() + " " + endTimeInput.getText() + ":00"), "local");

            // Make sure there aren't any time conflicts

            if (title.contains("Edit") || title.contains("Modifier")) {
                sqlStatement = "UPDATE appointments SET Title=\"" + appointmentTitleInput.getText() +
                        "\", Description=\"" + appointmentDescriptionInput.getText() +
                        "\", Location=\"" + appointmentLocationInput.getText() +
                        "\", Type=\"" + appointmentTypeInput.getText() +
                        "\", Start=\"" + start +
                        "\", End=\"" + end +
                        "\", Customer_ID=" + Integer.parseInt(appointmentCustomerIdInput.getText()) +
                        ", User_ID=" + Integer.parseInt(appointmentUserIdInput.getText()) +
                        ", Contact_ID=" + Integer.parseInt(appointmentContactIdComboBox.getValue().toString()) +
                        " WHERE Customer_ID=" + Integer.parseInt(appointmentIdInput.getText()) + ";";
            }
            else {
                sqlStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)" +
                        " VALUES(\"" + appointmentTitleInput.getText() + "\", \"" + appointmentDescriptionInput.getText() + "\", \"" +
                        appointmentLocationInput.getText() + "\", \"" + appointmentTypeInput.getText() + "\", \"" + start + "\", \"" + end + "\", " +
                        Integer.parseInt(appointmentCustomerIdInput.getText()) + ", " + Integer.parseInt(appointmentUserIdInput.getText()) +
                        ", " + Integer.parseInt(appointmentUserIdInput.getText()) + ");";
            }

            Statement statement;
            statement = localDb.createStatement();
            boolean result;
            result = statement.execute(sqlStatement);

            statement.close();
            localDb.close();

            if (!result) {
                // close the active window
                Node node = (Node) event.getSource();
                Stage active = (Stage) node.getScene().getWindow();
                active.close();
            }
            else {
                String errorString = Translator.ln.get("WriteCustomerError");

                // Log the error
                SchedulerLogger.addToLog(errorString, "severe");

                // Alert that an error occured
                // Create a popup
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Database Error!");
                errorAlert.setContentText(errorString);
                errorAlert.showAndWait();
            }
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

        // refresh db on previous page?
        ScheduleController.appointmentTableRefresh();
    }

    /**
     * Method to close the pop-up
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        // clear contact ID
        contactIds = new LinkedList<>();

        // close the active window
        Node node = (Node) event.getSource();
        Stage active = (Stage) node.getScene().getWindow();
        active.close();
    }
}
