package com.eastonseidel.c195pa;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Home page to house the options for the program
 */
public class HomeController {

    // FXML objects
    @FXML Button quitButton;
    @FXML Button scheduleButton;
    @FXML Button reportsButton;
    @FXML Button customersButton;

    /**
     * Code for the Home Screen window for the application
     * @exception IOException required by javafx
     */
    public static void HomeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle(Translator.ln.get("homeTitle"));
        homeStage.setScene(scene);
        homeStage.show();

        // Check for any incoming appointments
        ObservableList<Appointment> warning = AppointmentActionsController.appointmentWarning();
        LocalDateTime now = LocalDateTime.now();
        Alert apptAlert = new Alert(Alert.AlertType.INFORMATION);
        apptAlert.setHeaderText(Translator.ln.get("NoApptHeader"));
        apptAlert.setContentText(Translator.ln.get("NoApptText"));

        if (!warning.isEmpty()) {
            for (Appointment i : warning) {
                LocalDateTime startTime = LocalDateTime.parse(i.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));

                if (startTime.isBefore(now.plusMinutes(15)) && startTime.isAfter(now)) {
                    apptAlert.setHeaderText(Translator.ln.get("15MinuteHeader"));
                    apptAlert.setContentText(Translator.ln.get("15MinuteText") +
                            "ID: " + i.getAppointmentId() +
                            "\n" + Translator.ln.get("startDateTime") + ": " + i.getStart());
                }
            }
        }

        apptAlert.showAndWait();
    }

    /**
     * Initializer class for text and language on the page
     */
    public void initialize() {
        // Change language variables
        quitButton.setText(Translator.ln.get("quitButton"));
        scheduleButton.setText(Translator.ln.get("schedule"));
        reportsButton.setText(Translator.ln.get("reports"));
        customersButton.setText(Translator.ln.get("customers"));

        // change the button sizes if needed
        if (quitButton.getText().equals("Quitter")) {
            quitButton.setPrefWidth(90);
            quitButton.setLayoutX(130);
        }
    }

    /**
     * Method to quit the program
     */
    @FXML
    protected void onQuitButtonClick() {
        // Log the app closing
        SchedulerLogger.addToLog("App closed", "info");

        System.exit(0);
    }

    /**
     * Method to open the Customers list dialog box
     * @exception IOException required by javafx
     */
    @FXML
    protected void onCustomersButtonClick() throws IOException {
        // Open the cutomers window
        CustomersController.CustomerWindow();
    }

    /**
     * Method to open the reports window
     * @exception IOException required by javafx
     */
    @FXML
    protected void onReportsButtonClick() throws IOException {
        // Open the reports window
        ReportsController.ReportsWindow();
    }

    /**
     * Method to open the schedule
     * @exception IOException required by javafx
     */
    @FXML
    protected void onScheduleButtonClick() throws IOException {
        // Open the schedule window
        ScheduleController.ScheduleWindow();
    }
}
