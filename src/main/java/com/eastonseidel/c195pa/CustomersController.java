package com.eastonseidel.c195pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomersController {

    @FXML private Button cancelButton;
    @FXML private Button newButton;
    @FXML private Button deleteButton;
    @FXML private Button editButton;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn customerColumn;
    @FXML private Text customersTitle;
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();

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
        // Set up the table cells
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

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

        // Fill the tableview
        dbRefresh();

        // set the table view to the list
        customerTable.setItems(customers);
    }

    /**
     * Method to refresh the table data after edits
     */
    public static void dbRefresh() {
        // Empty the var and table
        customers.removeAll();

        Connection localDb;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            localDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/client_schedule",
                    "sqlUser", "Passw0rd!"
            );

            Statement statement;
            statement = localDb.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "SELECT * FROM customers"
            );

            while (resultSet.next()) {
                // Add customer to table
                customers.add(new Customer(resultSet.getInt("Customer_ID"), resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"), resultSet.getString("Postal_Code"), resultSet.getString("Phone"),
                        resultSet.getString("Create_Date"), resultSet.getString("Created_By"), resultSet.getString("Last_Update"),
                        resultSet.getString("Last_Updated_By"), resultSet.getInt("Division_ID")));
            }
            resultSet.close();
            statement.close();
            localDb.close();
        } catch (Exception exception) {
            String errorString = Translator.ln.get("dbFailed").toString() + exception;

            // Log the error
            SchedulerLogger.addToLog(errorString, "severe");

            // Alert that an error occured
            // Create a popup
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Database Error!");
            errorAlert.setContentText(errorString);
            errorAlert.showAndWait();
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

    /**
     * Method for creating a new customer
     */
    @FXML
    protected void onNewButtonClick(ActionEvent event) throws IOException {
        customerTable.getItems().clear();

        CustomerActionsController.NewCustomer();
    }

    /**
     * Method for editing a customer
     */
    @FXML
    protected void onEditButtonClick(ActionEvent event) throws IOException {
        // Get the table position and call the edit window
        try {
            TablePosition pos = customerTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Customer oldCustomer = customerTable.getItems().get(row);

            CustomerActionsController.EditCustomer(oldCustomer);

            customerTable.getItems().clear();
        }
        catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(Translator.ln.get("editCustomerTitleError").toString());
            errorAlert.setContentText(Translator.ln.get("editCustomerErrorText").toString() + e);
            errorAlert.showAndWait();

            // Log the error
            SchedulerLogger.addToLog("warning", "There was an error attempting to edit a customer" + e);
        }
    }

    /**
     * Method for deleting a customer
     */
    @FXML
    protected void onDeleteButtonClick(ActionEvent event) {
        Customer oldCustomer = null;

        // Get the table position and call the edit window
        try {
            TablePosition pos = customerTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            oldCustomer = customerTable.getItems().get(row);
        }
        catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(Translator.ln.get("editCustomerTitleError").toString());
            errorAlert.setContentText(Translator.ln.get("editCustomerErrorText").toString() + e);
            errorAlert.showAndWait();

            // Log the error
            SchedulerLogger.addToLog("warning", "There was an error attempting to edit a customer" + e);
        }

        // Delete the selected user
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
            boolean result;
            result = statement.execute(
                    "DELETE FROM customers WHERE Customer_ID=" + oldCustomer.getId()
            );

            if (!result) {
                // Refresh the table
                customerTable.getItems().clear();
                dbRefresh();
            }
            else {
                String errorString = Translator.ln.get("WriteCustomerError").toString();

                // Log the error
                SchedulerLogger.addToLog(errorString, "severe");

                // Alert that an error occured
                // Create a popup
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Database Error!");
                errorAlert.setContentText(errorString);
                errorAlert.showAndWait();
            }

            statement.close();
            localDb.close();

            // Notify of the action
            Alert deleteSuccess = new Alert(Alert.AlertType.INFORMATION);
            deleteSuccess.setHeaderText(Translator.ln.get("deleteCustomerTitleAlert").toString());
            deleteSuccess.setContentText(Translator.ln.get("deleteCustomerAlertText").toString() + oldCustomer.getName());
            deleteSuccess.showAndWait();
        } catch (Exception exception) {
            String errorString = Translator.ln.get("dbFailed").toString() + exception;

            // Log the error
            SchedulerLogger.addToLog(errorString, "severe");

            // Alert that an error occured
            // Create a popup
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Database Error!");
            errorAlert.setContentText(errorString);
            errorAlert.showAndWait();
        }
    }
}
