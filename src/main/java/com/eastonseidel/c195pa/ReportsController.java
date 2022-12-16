package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Houses options for various types of reports as well as the code to display those reports in a pop-up window
 */
public class ReportsController {

    @FXML Text rptText;
    @FXML Button rptByType;
    @FXML Button rptByContact;
    @FXML Button rptByMonth;
    @FXML Button cancelButton;

    /**
     * Code for the Home Screen window for the application
     * @exception IOException required by javafx
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
        rptText.setText(Translator.ln.get("reports"));
        rptByType.setText(Translator.ln.get("rptByType"));
        rptByContact.setText(Translator.ln.get("rptByContact"));
        rptByMonth.setText(Translator.ln.get("rptByMonth"));
        cancelButton.setText(Translator.ln.get("cancel"));

        if (cancelButton.getText().equals("Annuler")) {
            // Cancel Button
            cancelButton.setPrefWidth(80);
            cancelButton.setLayoutX(310);
        }
    }

    /**
     * Method to close the pop-up
     * @param event used to close the window without further action
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
     * @param event used to get the text of the string that was clicked for use in the selected report
     */
    @FXML
    protected void onButtonClick(ActionEvent event) throws IOException {
        String sqlCommand = null;
        String title = null;

        // Get the text and setup the query
        String buttonText = event.getSource().toString();

        // Decide on what sql data is needed
        if (buttonText.contains("id=rptByType")) {
            sqlCommand = "SELECT MONTH(Start) as Month, Type, COUNT(*) as Total FROM appointments GROUP BY MONTH(Start), Type";
            title = Translator.ln.get("rptByType");
        }
        else if (buttonText.contains("id=rptByContact")) {
            sqlCommand = "SELECT * FROM appointments";
            title = Translator.ln.get("rptByContact");
        }
        else {
            sqlCommand = "SELECT MONTH(Start) as Month, COUNT(*) as Total FROM appointments GROUP BY MONTH(Start)";
            title = Translator.ln.get("rptByMonth");
        }

        // Open the report
        OpenedReportController.OpenedReportWindow(sqlCommand, title);
    }
}
