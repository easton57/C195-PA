package com.eastonseidel.c195pa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    /**
     * Code for the Home Screen window for the application
     */
    public static void HomeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle("Home");
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Method to quit the program
     */
    @FXML
    protected void onQuitButtonClick() {
        // Log the app closing
        ScheduleLogger.addToLog("App closed", "info");

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
