package com.eastonseidel.c195pa;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

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
    @FXML private ComboBox<String> countryIdBox;
    @FXML private ComboBox<String> divisionIdBox;

    private static final Dictionary<String, String> divisionToCountryId = new Hashtable<>();
    private static final Dictionary<String, String> countryIds = new Hashtable<>();
    private static final Dictionary<String, String> divisionIds = new Hashtable<>();
    private static final Dictionary<String, String> divisionNames = new Hashtable<>();
    private static final List<String> usDivisions = new LinkedList<>();
    private static final List<String> canadaDivisions = new LinkedList<>();
    private static final List<String> ukDivisions = new LinkedList<>();
    private static List<String> divisionList = new LinkedList<>();
    private static String title;

    /**
     * edit customer window
     * @throws IOException for javafx
     */
    public static void EditCustomer(Customer oldCustomer) throws IOException {
        title = Translator.ln.get("editCustomer");
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerActionsController.class.getResource("edit-customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        pageElements(scene, oldCustomer);

        // Show the scene
        stage.setScene(scene);
        stage.show();
    }

    /**
     * New customer window
     * @throws IOException for javafx
     */
    public static void NewCustomer() throws IOException {
        title = Translator.ln.get("createCustomer");
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerActionsController.class.getResource("edit-customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * initializer class
     */
    public void initialize() {
        // set the text for the objects
        phoneNumberLabel.setText(Translator.ln.get("phoneNumber"));
        postalCodeLabel.setText(Translator.ln.get("postalCode"));
        addressLabel.setText(Translator.ln.get("address"));
        customerNameLabel.setText(Translator.ln.get("customerName"));
        customerIdLabel.setText(Translator.ln.get("customerId"));
        saveButton.setText(Translator.ln.get("save"));
        cancelButton.setText(Translator.ln.get("cancel"));

        customerIdInput.setDisable(true);

        // find the new customer ID
        if (title.contains("Create") || title.contains("Créer")) {
            List<Integer> customerIds = new LinkedList<>();

            // grab the id's and divisions from the db
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
                ResultSet resultSet;
                resultSet = statement.executeQuery(
                        "SELECT Customer_ID FROM customers"
                );
                while (resultSet.next()) {
                    // Add username and password to dictionary
                    customerIds.add(resultSet.getInt("Customer_ID"));
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

            // Check maximum element using for loop
            int maxValue = 0;

            for (Integer integer : customerIds) {
                if (integer > maxValue)
                    maxValue = integer;
            }

            customerIdInput.setText(Integer.toString(maxValue + 1));
        }

        List<String> countryList = new LinkedList<>();

        // grab the id's and divisions from the db
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
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "SELECT Country_ID, Country FROM countries"
            );
            while (resultSet.next()) {
                // Add username and password to dictionary
                countryIds.put(resultSet.getString("Country_ID"), resultSet.getString("Country"));
                countryList.add(resultSet.getString("Country"));
            }

            // grab the division ID's
            resultSet = statement.executeQuery(
                    "SELECT Division_ID, Division, Country_ID FROM first_level_divisions"
            );
            while (resultSet.next()) {
                // Add username and password to dictionary
                divisionNames.put(resultSet.getString("Division"), resultSet.getString("Division_ID"));
                divisionIds.put(resultSet.getString("Division_ID"), resultSet.getString("Division"));
                divisionToCountryId.put(resultSet.getString("Division_ID"), countryIds.get(resultSet.getString("Country_ID")));

                if (resultSet.getInt("Country_ID") == 1) {
                    usDivisions.add(resultSet.getString("Division"));
                }
                else if (resultSet.getInt("Country_ID") == 2) {
                    ukDivisions.add(resultSet.getString("Division"));
                }
                else {
                    canadaDivisions.add(resultSet.getString("Division"));
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


        // Set the country id
        countryIdBox.setItems(FXCollections.observableArrayList(countryList));
        countryIdBox.setPromptText(Translator.ln.get("countryPromptText"));

        // first level division ID
        divisionList.add(Translator.ln.get("countryPromptText"));
        divisionIdBox.setItems(FXCollections.observableArrayList(divisionList));
        divisionIdBox.setPromptText(Translator.ln.get("divisionPromptText"));

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
    private static void pageElements(Scene scene, Customer oldCustomer) {
        // Setup the input fields
        // Bad workaround for now...
        TextField phoneNumberInputBad = (TextField) scene.lookup("#phoneNumberInput");
        TextField postalCodeInputBad = (TextField) scene.lookup("#postalCodeInput");
        TextField addressInputBad = (TextField) scene.lookup("#addressInput");
        TextField customerNameInputBad = (TextField) scene.lookup("#customerNameInput");
        TextField customerIdInputBad = (TextField) scene.lookup("#customerIdInput");
        ComboBox countryIdBad = (ComboBox) scene.lookup("#countryId");
        ComboBox divisionIdBad = (ComboBox) scene.lookup("#divisionId");

        // fill in the old info
        customerNameInputBad.setText(oldCustomer.getName());
        addressInputBad.setText(oldCustomer.getAddress());
        postalCodeInputBad.setText(oldCustomer.getPostalCode());
        phoneNumberInputBad.setText(oldCustomer.getPhone());
        customerIdInputBad.setText(Integer.toString(oldCustomer.getCustomerId()));
        countryIdBad.setValue(divisionToCountryId.get(Integer.toString(oldCustomer.getDivisionId())));
        divisionIdBad.setValue(divisionIds.get(Integer.toString(oldCustomer.getDivisionId())));

        // fill the drop downs
        String boxText = countryIdBad.getValue().toString();

        // Determine the other list values
        if (boxText.contains("U.S")) {
            divisionList = usDivisions;
        }
        else if (boxText.contains("Canada")) {
            divisionList = canadaDivisions;
        }
        else {
            divisionList = ukDivisions;
        }

        // first level division ID
        divisionIdBad.setItems(FXCollections.observableArrayList(divisionList));
    }

    /**
     * Method for saving the customer
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        // Write the updated customer to the DB
        Connection localDb;
        try {
            String sqlStatement;

            Class.forName("com.mysql.cj.jdbc.Driver");
            localDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/client_schedule",
                    "sqlUser", "Passw0rd!"
            );

            if (title.contains("Edit") || title.contains("Modifier")) {
                sqlStatement = "UPDATE customers SET Customer_Name=\"" + customerNameInput.getText() +
                        "\", Address=\"" + addressInput.getText() +
                        "\", Postal_Code=\"" + postalCodeInput.getText() +
                        "\", Phone=\"" + phoneNumberInput.getText() +
                        "\", Division_ID=" + Integer.parseInt(divisionNames.get(divisionIdBox.getValue())) +
                        " WHERE Customer_ID=" + Integer.parseInt(customerIdInput.getText()) + ";";
            }
            else {
                sqlStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID)" +
                        " VALUES(\"" + customerNameInput.getText() + "\", \"" + addressInput.getText() + "\", \"" +
                        postalCodeInput.getText() + "\", \"" + phoneNumberInput.getText() + "\", " +
                        Integer.parseInt(divisionNames.get(divisionIdBox.getValue())) + ");";
            }

            Statement statement;
            statement = localDb.createStatement();
            boolean result;
            result = statement.execute(sqlStatement);

            statement.close();
            localDb.close();

            if (!result) {
                // close the active window
                Node node = (Node) event.getSource();
                Stage active = (Stage) node.getScene().getWindow();
                active.close();
            }
            else {
                String errorString = Translator.ln.get("WriteCustomerError");

                // Log the error
                SchedulerLogger.addToLog(errorString, "severe");

                // Alert that an error occured
                // Create a popup
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Database Error!");
                errorAlert.setContentText(errorString);
                errorAlert.showAndWait();
            }
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

        // refresh db on previous page?
        CustomersController.customerTableRefresh();
    }

    /**
     * Method to close the pop-up
     */
    @FXML
    protected void onCancelButtonClick(ActionEvent event) {
        // refresh db on previous page?
        divisionList = new LinkedList<>();
        CustomersController.customerTableRefresh();

        // close the active window
        Node node = (Node) event.getSource();
        Stage active = (Stage) node.getScene().getWindow();
        active.close();
    }

    /**
     * Method for hopefully checking the combo box value and changing the available values in the other box
     */
    @FXML
    protected void onCountryComboBoxChange() {
        String boxText = countryIdBox.getValue();

        // Determine the other list values
        if (boxText.contains("U.S")) {
            divisionList = usDivisions;
        }
        else if (boxText.contains("Canada")) {
            divisionList = canadaDivisions;
        }
        else {
            divisionList = ukDivisions;
        }

        // first level division ID
        divisionIdBox.setItems(FXCollections.observableArrayList(divisionList));
    }
}
