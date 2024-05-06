package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Monde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TilePane mapPane;

    private Monde map;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Monde();

        afficherMap();
    }


    public void afficherMap() {

        int[][] m = this.map.getMap();

        for (int x = 0 ; x < m.length ; x++) {
            for (int y = 0 ; y < m[x].length ; y++) {
                Rectangle rectangle = new Rectangle(30, 30);
                switch (m[x][y]) {
                    case 0 -> rectangle.setFill(Color.GREEN);
                    case 1 -> rectangle.setFill(Color.GRAY);
                }
                mapPane.getChildren().add(rectangle);
            }
        }
    }

}
