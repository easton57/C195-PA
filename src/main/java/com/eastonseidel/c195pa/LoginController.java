package com.eastonseidel.c195pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Dictionary;
import java.util.Hashtable;

public class LoginController {
    @FXML
    private TextField username;
    @FXML private PasswordField password;
    @FXML private Button loginButton;
    @FXML private Label locationVar;
    private Dictionary creds = new Hashtable();

    public static void LoginController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.setScene(scene);
        loginStage.show();

        // Send a log that the login screen made it
        ScheduleLogger.addToLog("App launched successfully!", "info");
    }

    public void initialize() {
        // Set the location var based on the zoneID
        String zoneId = ZoneId.systemDefault().getId();
        locationVar.setText(zoneId);
    }

    /**
     * Method for the cancel button
     */
    @FXML
    protected void onCancelButtonClick() {
        // Log the app closing
        ScheduleLogger.addToLog("Login Cancelled", "info");

        System.exit(-1);
    }

    /**
     * Method for the login button
     */
    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        // Set the enter button as default here for convenience
        loginButton.setDefaultButton(true);

        // Connect to the DB
        if (creds.isEmpty()) {
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
                ScheduleLogger.addToLog("Successfully connected to DB", "info");
            } catch (Exception exception) {
                String errorString = "Please verify the local database is running and accessible!\n" + exception;

                // Log the error
                ScheduleLogger.addToLog(errorString, "severe");

                // Alert that an error occured
                // Create a popup
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Database Error!");
                errorAlert.setContentText(errorString);
                errorAlert.showAndWait();
            }
        }

        // Check the username and password against the DB
        try {
            // Check the password
            if (creds.get(username.getText()).equals(password.getText())) {
                // Log the success
                ScheduleLogger.addToLog("Log in successful by: " + username.getText(), "info");

                // close the active window
                Node node = (Node) event.getSource();
                Stage active = (Stage) node.getScene().getWindow();
                active.close();

                // Next part of the app
                HomeController.HomeWindow();
            }
            else {
                // log failed password
                ScheduleLogger.addToLog("Log in failed: Invalid Password", "warning");

                // Wrong Password
                Alert incorrectPassword = new Alert(Alert.AlertType.WARNING);
                incorrectPassword.setHeaderText("Incorrect Password!");
                incorrectPassword.setContentText("Password is incorrect, please check your password and try again.");
                incorrectPassword.showAndWait();
            }
        }
        catch (Exception exception) {
            // log invalid username
            ScheduleLogger.addToLog("Log in failed: Invalid Username", "warning");

            // Print a username failure
            Alert invalidUser = new Alert(Alert.AlertType.WARNING);
            invalidUser.setHeaderText("Invalid Username!");
            invalidUser.setContentText("Username doesn't exists in application, please check your Username and try again.");
            invalidUser.showAndWait();
        }
    }
}
