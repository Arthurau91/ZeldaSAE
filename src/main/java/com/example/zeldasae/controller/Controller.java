package com.example.zeldasae.controller;

import com.example.zeldasae.Main;
import com.example.zeldasae.modele.Monde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
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


    /**
     * Méthode qui permet d'afficher la map
     */

    public void afficherMap() {

        ArrayList<Integer> m = this.map.getMap();

        for (int x = 0 ; x < m.size() ; x++) {
                ImageView imageView = new ImageView();
                switch (m.get(x)) {
                    case 0:
                        Image image = new Image("file:src/main/resources/com/example/zeldasae/img/grass.jpg");
                        imageView.setImage(image);
                        break;
                }


                this.mapPane.getChildren().add(imageView);

            }

    }

}
