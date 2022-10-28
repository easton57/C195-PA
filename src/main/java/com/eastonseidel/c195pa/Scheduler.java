package com.eastonseidel.c195pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Scheduler extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Scheduler.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        // Send a log that the login screen made it
        ScheduleLogger.addToLog("App launched successfully!", "info");
    }

    public static void main(String[] args) {
        // Launch our GUI
        launch(args);
    }

}
