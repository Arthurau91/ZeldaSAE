package com.example.zeldasae.controller;

import com.example.zeldasae.Main;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane paneEntites;

    @FXML
    private TilePane mapPane;

    private Monde map;
    public Joueur joueur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.joueur = new Joueur(20, 20);
        this.map = new Monde(this.joueur);
        creerSpriteJoueur(this.map.getJoueur());
        afficherMap();
    }


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

    public void creerSpriteJoueur(Joueur j) {
        Circle c = new Circle(20);
        c.setId(j.getId());
        c.translateXProperty().bind(j.xProperty());
        c.translateYProperty().bind(j.yProperty());
        paneEntites.getChildren().add(c);

    }


    @FXML
    public void handle(KeyEvent keyEvent) {

        System.out.println("ici");
        switch (keyEvent.getText()) {
            case "z", "Z":
                map.getJoueur().deplacementZQSD('z');
                break;
            case "q", "Q":
                map.getJoueur().deplacementZQSD('q');
                break;
            case "s", "S":
                map.getJoueur().deplacementZQSD('s');
                break;
            case "d", "D":
                map.getJoueur().deplacementZQSD('d');
                break;
        }

    }

}
