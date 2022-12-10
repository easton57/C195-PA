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

import java.sql.Date;
import java.time.LocalDateTime;

import java.io.IOException;
import java.sql.*;
import java.util.*;

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
    @FXML private TableView<Appointment> appointmentTable;
    private static Dictionary<Integer, ObservableList<Appointment>> months = new Hashtable();
    private static Dictionary<Integer, ObservableList<Appointment>> weeks = new Hashtable();
    private static Dictionary<Integer, String> weekDates = new Hashtable();
    private String[] monthNames = { Translator.ln.get("January"),  Translator.ln.get("February"),  Translator.ln.get("March"),  Translator.ln.get("April"),  Translator.ln.get("May"),  Translator.ln.get("June"),  Translator.ln.get("July"),  Translator.ln.get("August"),  Translator.ln.get("September"),  Translator.ln.get("October"),  Translator.ln.get("November"),  Translator.ln.get("December") };

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

        // fill the table view variable
        appointmentTableRefresh();

        // get the local date
        LocalDateTime now = LocalDateTime.now();

        // set the table list
        appointmentTable.setItems(months.get(now.getMonthValue()-1));
        rangeText.setText(monthNames[now.getMonthValue()-1]);
    }

    /**
     * Method to refresh the table data after edits
     */
    public static void appointmentTableRefresh() {
        // Create the observable list for the months array
        for (int i=0; i<12; i++) {
            ObservableList<Appointment> temp = FXCollections.observableArrayList();
            months.put(i, temp);
        }

        // create the observable list for the weeks array
        Calendar calendar = Calendar.getInstance();
        Calendar currentDay = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1);
        String tempKey = null;
        int weekDatesLocation = 0;

        for (int i=0; i<calendar.getMaximum(Calendar.DAY_OF_YEAR)-1; i++) {
            // Check for the beginning of the week
            if(tempKey == null) {
                // Some small stuff
                String month;
                String day;

                // Add a zero to the beginning of numbers less than 10
                // months
                if (currentDay.get(Calendar.MONTH) + 1 < 10) {
                    month = "0" + (currentDay.get(Calendar.MONTH) + 1);
                }
                else {
                    month = Integer.toString((currentDay.get(Calendar.MONTH) + 1));
                }

                // days
                if (currentDay.get(Calendar.DATE) < 10) {
                    day = "0" + currentDay.get(Calendar.DATE);
                }
                else {
                    day = Integer.toString(currentDay.get(Calendar.DATE));
                }

                // convert the date to a string and then
                tempKey= month + "/" + day;
            }

            // Check to see if it's also the end of that week
            if(currentDay.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && tempKey != null || i==(calendar.getMaximum(Calendar.DAY_OF_YEAR) - 1) && tempKey != null) {
                // Some small stuff
                String month;
                String day;

                // Add a zero to the beginning of numbers less than 10
                // months
                if (currentDay.get(Calendar.MONTH) + 1 < 10) {
                    month = "0" + (currentDay.get(Calendar.MONTH) + 1);
                }
                else {
                    month = Integer.toString((currentDay.get(Calendar.MONTH) + 1));
                }

                // days
                if (currentDay.get(Calendar.DATE) < 10) {
                    day = "0" + currentDay.get(Calendar.DATE);
                }
                else {
                    day = Integer.toString(currentDay.get(Calendar.DATE));
                }

                // Finalize our keys and create a temp list
                ObservableList<Appointment> temp = FXCollections.observableArrayList();
                tempKey += "-" + month + "/" + day;

                // Add to our global arrays
                weekDates.put(weekDatesLocation, tempKey);
                weeks.put(weekDatesLocation, temp);

                // Destroy our stuff and iterate
                tempKey = null;
                weekDatesLocation += 1;
            }

            currentDay.add(Calendar.DATE, 1);
        }

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
                // Add customer to array
                Appointment temp = new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"),
                        resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"),
                        resultSet.getTimestamp("Start"), resultSet.getTimestamp("End"), resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"), resultSet.getInt("Contact_ID"));

                // separate appointments month
                for(int i=0; i<months.size(); i++) {
                    int num = i + 1;
                    if (resultSet.getString("Start").contains("-" + num + "-") || resultSet.getString("Start").contains("-0" + num + "-")) {
                        months.get(i).add(temp);
                        break;
                    }
                }

                // separate appointments by week
                for(int i=0; i<weeks.size(); i++) {
                    String m1 = weekDates.get(i).split("/")[0];
                    String m2 = weekDates.get(i).split("/")[1].split("-")[1];
                    int d1 = Integer.parseInt(weekDates.get(i).split("/")[1].split("-")[0]);
                    int d2 = Integer.parseInt(weekDates.get(i).split("/")[2]);
                    int evalDay = Integer.parseInt(resultSet.getString("Start").split("-")[2].split(" ")[0]);

                    // Some logic to consider if each week is indexed as M1/D1-M2/D2... if M1==resultSetMonth OR M2==resultSetMonth AND D1 <= resultSetDay <= D2, add to array
                    if ((resultSet.getString("Start").contains("-" + m1 + "-") && (d1 <= evalDay && evalDay <= d2)) ||
                            (resultSet.getString("Start").contains("-" + m1 + "-") && !resultSet.getString("Start").contains("-" + m2 + "-")) && (d1 >= evalDay && evalDay >= d2) ||
                            (!resultSet.getString("Start").contains("-" + m1 + "-") && resultSet.getString("Start").contains("-" + m2 + "-")) && (d1 >= evalDay && evalDay <= d2)) {
                        weeks.get(i).add(temp);
                        break;
                    }
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

    /**
     * Method for creating a new customer
     */
    @FXML
    protected void onNewButtonClick(ActionEvent event) throws IOException {
        try {
            //appointmentTable.getItems().clear();
        }
        catch (NullPointerException e) {
            SchedulerLogger.addToLog("Table is Empty" + e, "info");
        }

        AppointmentActionsController.NewAppointment();
    }

    /**
     * Method for editing a customer
     */
    @FXML
    protected void onEditButtonClick(ActionEvent event) throws IOException {
        // Get the table position and call the edit window
        try {
            TablePosition pos = appointmentTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Appointment oldAppointment = appointmentTable.getItems().get(row);

            AppointmentActionsController.EditAppointment(oldAppointment);
        }
        catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(Translator.ln.get("editCustomerTitleError"));
            errorAlert.setContentText(Translator.ln.get("editCustomerErrorText") + e);
            errorAlert.showAndWait();

            // Log the error
            SchedulerLogger.addToLog("There was an error attempting to edit an appointment" + e, "warning");
        }
    }

    /**
     * Method for deleting a customer
     */
    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {
        Appointment oldAppointment = null;

        // Get the table position and call the edit window
        try {
            TablePosition pos = appointmentTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            oldAppointment = appointmentTable.getItems().get(row);
        }
        catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(Translator.ln.get("editApptTitleError"));
            errorAlert.setContentText(Translator.ln.get("editApptErrorText") + e);
            errorAlert.showAndWait();

            // Log the error
            SchedulerLogger.addToLog("There was an error attempting to edit a customer" + e, "warning");
        }

        // Delete the selected user
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
            boolean result;
            result = statement.execute(
                    "DELETE FROM appointments WHERE Appointment_ID=" + oldAppointment.getAppointmentId()
            );

            if (!result) {
                // Refresh the table
                appointmentTable.getItems().clear();
                appointmentTableRefresh();
            }
            else {
                String errorString = Translator.ln.get("writeApptError");

                // Log the error
                SchedulerLogger.addToLog(errorString, "severe");

                // Alert that an error occured
                // Create a popup
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Database Error!");
                errorAlert.setContentText(errorString);
                errorAlert.showAndWait();
            }

            statement.close();
            localDb.close();

            // Notify of the action
            Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION);
            deleteSuccess.setHeaderText(Translator.ln.get("deleteApptTitleAlert"));
            deleteSuccess.setContentText(Translator.ln.get("deleteApptAlertText") + oldAppointment.getAppointmentId() + " " + oldAppointment.getType());
            deleteSuccess.showAndWait();
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

    @FXML
    protected void onLeftArrowClick(ActionEvent event) {
        int location;
        try {
            appointmentTable.getItems();
        }
        catch (NullPointerException e) {
            SchedulerLogger.addToLog("Table is Empty" + e, "info");
        }

        if (monthRadio.isSelected()) {
            // Loop through the array to get value
            for (int i = 0; i < monthNames.length; i++) {
                if (monthNames[i].equals(rangeText.getText())) {
                    if (i == 0) {
                        // replace the table values and the string values
                        rangeText.setText(monthNames[11]);
                        appointmentTable.setItems(months.get(11));
                        break;
                    } else {
                        // replace the table values and the string values
                        rangeText.setText(monthNames[i - 1]);
                        appointmentTable.setItems(months.get(i - 1));
                        break;
                    }
                }
            }
        }
        else {
            // Loop through the array to get value
            for (int i = 0; i < weekDates.size(); i++) {
                if (weekDates.get(i).equals(rangeText.getText())) {
                    if (i == 0) {
                        // replace the table values and the string values
                        rangeText.setText(weekDates.get(weekDates.size() - 1));
                        appointmentTable.setItems(weeks.get(weekDates.size() - 1));
                        break;
                    } else {
                        // replace the table values and the string values
                        rangeText.setText(weekDates.get(i - 1));
                        appointmentTable.setItems(weeks.get(i - 1));
                        break;
                    }
                }
            }
        }
    }

    @FXML
    protected void onRightArrowClick(ActionEvent event) {
        int location;
        try {
            appointmentTable.getItems();
        }
        catch (NullPointerException e) {
            SchedulerLogger.addToLog("Table is Empty" + e, "info");
        }

        if (monthRadio.isSelected()) {
            // Loop through the array to get value
            for (int i = 0; i < monthNames.length; i++) {
                if (monthNames[i].equals(rangeText.getText())) {
                    if (i == 11) {
                        // replace the table values and the string values
                        rangeText.setText(monthNames[0]);
                        appointmentTable.setItems(months.get(0));
                        break;
                    } else {
                        // replace the table values and the string values
                        rangeText.setText(monthNames[i + 1]);
                        appointmentTable.setItems(months.get(i + 1));
                        break;
                    }
                }
            }
        }
        else {
            // Loop through the array to get value
            for (int i = 0; i < weekDates.size(); i++) {
                if (weekDates.get(i).equals(rangeText.getText())) {
                    if (i == weekDates.size() - 1) {
                        // replace the table values and the string values
                        rangeText.setText(weekDates.get(0));
                        appointmentTable.setItems(weeks.get(0));
                        break;
                    } else {
                        // replace the table values and the string values
                        rangeText.setText(weekDates.get(i + 1));
                        appointmentTable.setItems(weeks.get(i + 1));
                        break;
                    }
                }
            }
        }
    }

    @FXML
    protected void onMonthRadioClick() {
        // Toggle the other radio
        weekRadio.setSelected(false);
        monthRadio.setSelected(true);

        // Set to current month
        LocalDateTime now = LocalDateTime.now();

        // set the table list
        appointmentTable.setItems(months.get(now.getMonthValue()-1));
        rangeText.setText(monthNames[now.getMonthValue()-1]);
    }

    @FXML
    protected void onWeekRadioClick() {
        // Toggle the other radio
        monthRadio.setSelected(false);
        weekRadio.setSelected(true);

        // Set to current week
        String now = LocalDateTime.now().toString();

        for (int i=0; i<weekDates.size(); i++) {
            String m1 = weekDates.get(i).split("/")[0];
            String m2 = weekDates.get(i).split("/")[1].split("-")[1];
            int d1 = Integer.parseInt(weekDates.get(i).split("/")[1].split("-")[0]);
            int d2 = Integer.parseInt(weekDates.get(i).split("/")[2]);
            int evalDay = Integer.parseInt(now.split("-")[2].split("T")[0]);
            String evalMonth = now.split("-")[1];

            if ((evalMonth.equals(m1) && (d1 <= evalDay && evalDay <= d2)) || ((evalMonth.equals(m1) && !evalMonth.equals(m2)) && (d1 <= evalDay && evalDay >= d2))) {
                appointmentTable.setItems(weeks.get(i));
                rangeText.setText(weekDates.get(i));
                break;
            }
        }
    }
}