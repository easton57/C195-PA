package com.eastonseidel.c195pa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    // FXML objects
    @FXML Button quitButton;
    @FXML Button scheduleButton;
    @FXML Button reportsButton;
    @FXML Button customersButton;

    /**
     * Code for the Home Screen window for the application
     */
    public static void HomeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle(Translator.ln.get("homeTitle"));
        homeStage.setScene(scene);
        homeStage.show();
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
     */
    @FXML
    protected void onCustomersButtonClick() throws IOException {
        // Open the cutomers window
        CustomersController.CustomerWindow();
    }

    /**
     * Method to open the reports window
     */
    @FXML
    protected void onReportsButtonClick() throws IOException {
        // Open the reports window
        ReportsController.ReportsWindow();
    }

    /**
     * Method to open the schedule
     */
    @FXML
    protected void onScheduleButtonClick() throws IOException {
        // Open the schedule window
        ScheduleController.ScheduleWindow();
    }
}
