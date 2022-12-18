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
import java.util.Hashtable;

/**
 * Queries the database to log in a user into the application w/ a specified account
 */
public class LoginController {
    @FXML
    private TextField username;
    @FXML private PasswordField password;
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private Label locationVar;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label locationLabel;

    private final Hashtable<String, String> creds = new Hashtable<>();

    /**
     * class to launch the login window for the application
     * @throws IOException required by javafx
     */
    public static void LoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage loginStage = new Stage();
        loginStage.setTitle(Translator.ln.get("appTitle"));
        loginStage.setScene(scene);
        loginStage.show();

        // Send a log that the login screen made it
        SchedulerLogger.addToLog("App launched successfully!", "info");
    }

    /**
     * Initializer method for text and language on the page
     */
    public void initialize() {
        // Set the location var based on the zoneID
        String zoneId = ZoneId.systemDefault().getId();
        locationVar.setText(zoneId);

        // Change language variables
        loginButton.setText(Translator.ln.get("login"));
        cancelButton.setText(Translator.ln.get("cancel"));
        passwordLabel.setText(Translator.ln.get("password"));
        locationLabel.setText(Translator.ln.get("location:"));
        usernameLabel.setText(Translator.ln.get("username"));

        // Move the french objects if needed
        if (loginButton.getText().equals("Connectez-vous")) {
            // login button mods
            loginButton.setPrefWidth(125);
            loginButton.setLayoutX(71);

            // Cancel Button
            cancelButton.setPrefWidth(125);
            cancelButton.setLayoutX(316);

            // Location text
            locationLabel.setPrefWidth(101);

            // Location Variable text
            locationVar.setPrefWidth(393);
            locationVar.setLayoutX(105);
        }
    }

    /**
     * Method for the cancel button and quit the program
     */
    @FXML
    protected void onCancelButtonClick() {
        // Log the app closing
        SchedulerLogger.addToLog("Login Cancelled", "info");

        System.exit(-1);
    }

    /**
     * Method for the login button
     * @param event used to close the window after a successful login
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
                SchedulerLogger.addToLog("Successfully connected to DB", "info");
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
        }

        // Check the username and password against the DB
        try {
            // Check the password
            if (creds.get(username.getText()).equals(password.getText())) {
                // Log the success
                SchedulerLogger.addToLog("Log in successful by: " + username.getText(), "info");

                // close the active window
                Node node = (Node) event.getSource();
                Stage active = (Stage) node.getScene().getWindow();
                active.close();

                // Next part of the app
                HomeController.HomeWindow();
            }
            else {
                // log failed password
                SchedulerLogger.addToLog("Invalid Password: Log in failed for user: " + username.getText(), "warning");

                // Wrong Password
                Alert incorrectPassword = new Alert(Alert.AlertType.WARNING);
                incorrectPassword.setHeaderText(Translator.ln.get("invalidPasswordTitle"));
                incorrectPassword.setContentText(Translator.ln.get("invalidPassword"));
                incorrectPassword.showAndWait();
            }
        }
        catch (Exception exception) {
            // log invalid username
            SchedulerLogger.addToLog("Log in failed for invalid Username: " + username.getText(), "warning");

            // Print a username failure
            Alert invalidUser = new Alert(Alert.AlertType.WARNING);
            invalidUser.setHeaderText(Translator.ln.get("invalidUsernameTitle"));
            invalidUser.setContentText(Translator.ln.get("invalidUsername"));
            invalidUser.showAndWait();
        }
    }
}
