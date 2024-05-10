package com.example.zeldasae;

import com.example.zeldasae.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


import java.io.IOException;


public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Zelda!");
        Controller controller = fxmlLoader.getController();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, controller.getKeyHandler());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}