package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
    @FXML private Button rightButton;
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
