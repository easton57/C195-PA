package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomersController {

    @FXML Button cancelButton;
    @FXML Button newButton;
    @FXML Button deleteButton;
    @FXML Button editButton;
    @FXML TableColumn customerColumn;
    @FXML Text customersTitle;

    /**
     * Code for the Home Screen window for the application
     */
    public static void CustomerWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomersController.class.getResource("customers-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle(Translator.ln.get("customers").toString());
        homeStage.setScene(scene);
        homeStage.show();
    }

    /**
     * Initializer class for text and language on the page
     */
    public void initialize() {
        // Change language variables
        cancelButton.setText(Translator.ln.get("cancel").toString());
        newButton.setText(Translator.ln.get("new").toString());
        deleteButton.setText(Translator.ln.get("delete").toString());
        editButton.setText(Translator.ln.get("edit").toString());
        customerColumn.setText(Translator.ln.get("customerName").toString());
        customersTitle.setText(Translator.ln.get("customers").toString());

        // change the button sizes if needed
        if (cancelButton.getText().equals("Annuler")) {
            // Cancel Button
            cancelButton.setPrefWidth(80);
            cancelButton.setLayoutX(135);

            // New Button
            newButton.setPrefWidth(80);
            newButton.setLayoutX(25);

            // Delete Button
            deleteButton.setPrefWidth(80);
            deleteButton.setLayoutX(245);

            // Edit Button
            editButton.setPrefWidth(80);
            editButton.setLayoutX(135);

            // Customer Title
            customersTitle.setLayoutX(126);
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
