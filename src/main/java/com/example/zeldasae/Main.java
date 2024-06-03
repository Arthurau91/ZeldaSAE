package com.example.zeldasae;

import com.example.zeldasae.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 1000);
        stage.setMaxHeight(1000);
        stage.setMaxWidth(1200);
        stage.setTitle("Zelda!");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}