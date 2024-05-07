package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, EventHandler<KeyEvent> {

    @FXML
    private Pane paneEntites;

    @FXML
    private TilePane mapPane;

    private Monde map;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Monde(new Joueur(20, 20));
        KeyHandler touche = new KeyHandler(this.map);

        afficherMap();
    }

    public void afficherMap() {

        int[][] m = this.map.getMap();

        for (int x = 0 ; x < m.length ; x++) {
            for (int y = 0 ; y < m[x].length ; y++) {
                Rectangle rectangle = new Rectangle(30, 30);
                switch (m[x][y]) {
                    case 0 -> rectangle.setFill(Color.RED);
                }
                mapPane.getChildren().add(rectangle);
            }
        }
        creerSpriteJoueur(this.map.getJoueur());

    }



    public void creerSpriteJoueur(Joueur j) {
        Circle c = new Circle(20);
        c.setId(j.getId());
        c.translateXProperty().bind(j.xProperty());
        c.translateYProperty().bind(j.yProperty());
        paneEntites.getChildren().add(c);

    }

    @Override
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


