package com.eastonseidel.c195pa;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class LoginController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button loginButton;

    /**
     * Method for the cancel button
     */
    @FXML
    protected void onCancelButtonClick() {
        System.exit(-1);
    }

    /**
     * Method for the login button
     */
    @FXML
    protected void onLoginButtonClick() {
        // Set the enter button as default here for convenience
        loginButton.setDefaultButton(true);

        // Username/password dictionary
        Dictionary creds = new Hashtable();

        // Connect to the DB
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
                    "SELECT User_Name, Password FROM users"
            );
            while (resultSet.next()) {
                // Add username and password to dictionary
                creds.put(resultSet.getString("User_Name"), resultSet.getString("Password"));
            }
            resultSet.close();
            statement.close();
            localDb.close();

            // Log connecting to the DB
            logger.addToLog("Successfully connected to DB", "info");
        }
        catch (Exception exception) {
            String errorString = "Please verify the local database is running and accessible!\n" + exception;

            // Log the error
            logger.addToLog(errorString, "fatal");

            // Alert that an error occured
            // Create a popup
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Database Error!");
            errorAlert.setContentText(errorString);
            errorAlert.showAndWait();
        }

        // Check the username and password against the DB
        try {
            // Check the password
            if (creds.get(username.getText()).equals(password.getText())) {
                // Log the success
                logger.addToLog("Log in successful by: " + username.getText(), "info");

                // Next part of the app
                // Wrong Password
                Alert incorrectPassword = new Alert(Alert.AlertType.WARNING);
                incorrectPassword.setHeaderText("Made it!");
                incorrectPassword.setContentText("You made it!");
                incorrectPassword.showAndWait();
            }
            else {
                // log failed password
                logger.addToLog("Log in failed: Invalid Password", "error");

                // Wrong Password
                Alert incorrectPassword = new Alert(Alert.AlertType.WARNING);
                incorrectPassword.setHeaderText("Incorrect Password!");
                incorrectPassword.setContentText("Password is incorrect, please check your password and try again.");
                incorrectPassword.showAndWait();
            }
        }
        catch (Exception exception) {
            // log invalid username
            logger.addToLog("Log in failed: Invalid Username", "error");

            // Print a username failure
            Alert invalidUser = new Alert(Alert.AlertType.WARNING);
            invalidUser.setHeaderText("Invalid Username!");
            invalidUser.setContentText("Username doesn't exists in application, please check your Username and try again.");
            invalidUser.showAndWait();
        }
    }
}
