package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerActionsController {
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private TextField phoneNumberInput;
    @FXML private TextField postalCodeInput;
    @FXML private TextField addressInput;
    @FXML private TextField customerNameInput;
    @FXML private TextField customerIdInput;
    @FXML private Text phoneNumberLabel;
    @FXML private Text postalCodeLabel;
    @FXML private Text addressLabel;
    @FXML private Text customerNameLabel;
    @FXML private Text customerIdLabel;
    @FXML private ComboBox countryId;

    // Bad workaround for now...
    private static TextField phoneNumberInputBad;
    private static TextField postalCodeInputBad;
    private static TextField addressInputBad;
    private static TextField customerNameInputBad;
    private static TextField customerIdInputBad;
    private static ComboBox countryIdBad;

    /**
     * edit customer window
     * @throws IOException for javafx
     */
    public static void EditCustomer(Customer oldCustomer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerActionsController.class.getResource("edit-customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(Translator.ln.get("editCustomer").toString());
        pageElements(scene);

        // fill in the old info
        customerNameInputBad.setText(oldCustomer.getName());
        addressInputBad.setText(oldCustomer.getAddress());
        postalCodeInputBad.setText(oldCustomer.getPostalCode());
        phoneNumberInputBad.setText(oldCustomer.getPhone());
        customerIdInputBad.setText(Integer.toString(oldCustomer.getId()));

        // Show the scene
        stage.setScene(scene);
        stage.show();
    }

    /**
     * New customer window
     * @throws IOException for javafx
     */
    public static void NewCustomer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerActionsController.class.getResource("edit-customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(Translator.ln.get("createCustomer").toString());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * initializer class
     */
    public void initialize() {
        // set the text for the objects
        phoneNumberLabel.setText(Translator.ln.get("phoneNumber").toString());
        postalCodeLabel.setText(Translator.ln.get("postalCode").toString());
        addressLabel.setText(Translator.ln.get("address").toString());
        customerNameLabel.setText(Translator.ln.get("customerName").toString());
        customerIdLabel.setText(Translator.ln.get("id").toString());
        saveButton.setText(Translator.ln.get("save").toString());
        cancelButton.setText(Translator.ln.get("cancel").toString());

        customerIdInput.setDisable(true);

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
    private static void pageElements(Scene scene) {
        // Setup the input fields
        phoneNumberInputBad = (TextField) scene.lookup("#phoneNumberInput");
        postalCodeInputBad = (TextField) scene.lookup("#postalCodeInput");
        addressInputBad = (TextField) scene.lookup("#addressInput");
        customerNameInputBad = (TextField) scene.lookup("#customerNameInput");
        customerIdInputBad = (TextField) scene.lookup("#customerIdInput");
        countryIdBad = (ComboBox) scene.lookup("#countryId");

        // Set the country id

        // first level division ID
    }

    /**
     * Method for saving the customer
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {

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
