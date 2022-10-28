package com.eastonseidel.c195pa;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    /**
     * Code for the Home Screen window for the application
     * @throws IOException
     */
    public static void HomeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage homeStage = new Stage();
        homeStage.setTitle("Login");
        homeStage.setScene(scene);
        homeStage.show();
    }

}
