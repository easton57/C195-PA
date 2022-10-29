package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomersController {

    /**
     * Code for the Home Screen window for the application
     */
    public static void CustomerWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomersController.class.getResource("customers-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle("Customers");
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