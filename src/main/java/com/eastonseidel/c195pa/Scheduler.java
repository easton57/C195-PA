package com.eastonseidel.c195pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;


public class Scheduler extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // test variable for french without needing to change system language for now
        boolean frTest = false;

        // Get the locale and set the language
        if (Locale.getDefault().getLanguage().contains("fr") || frTest)  //french
        {
            Translator.French();
        }
        else {
            Translator.English();
        }

        // setup the javafx app (filler so we can be a little more graceful later)
        FXMLLoader fxmlLoader = new FXMLLoader(Scheduler.class.getResource("loading.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Loading/Chargement");
        stage.setScene(scene);

        //  Start the javafx app
        LoginController.LoginWindow();
    }

    public static void main(String[] args) {
        // Launch our GUI
        launch(args);
    }
}
