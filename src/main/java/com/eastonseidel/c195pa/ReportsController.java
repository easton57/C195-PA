package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsController {

    @FXML Button rptByType;
    @FXML Button rptByContact;
    @FXML Button rptByMonth;
    @FXML Button cancelButton;

    /**
     * Code for the Home Screen window for the application
     */
    public static void ReportsWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReportsController.class.getResource("reports-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle(Translator.ln.get("reports"));
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Initializer method for text and language on the page
     */
    public void initialize() {
        rptByType.setText(Translator.ln.get("rptByType"));
        rptByContact.setText(Translator.ln.get("rptByContact"));
        rptByMonth.setText(Translator.ln.get("rptByMonth"));
        cancelButton.setText(Translator.ln.get("cancel"));
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
     * Method to close the pop-up
     */
    @FXML
    protected void onButtonClick(ActionEvent event) throws IOException {
        // close the active window
        OpenedReportController.OpenedReportWindow();
    }
}

class OpenedReportController {
    @FXML Button cancelButton;

    /**
     * Code for the Home Screen window for the application
     */
    public static void OpenedReportWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReportsController.class.getResource("opened-report-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle(Translator.ln.get("reports"));
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Initializer method for text and language on the page
     */
    public void initialize() {
        cancelButton.setText(Translator.ln.get("cancel"));
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
