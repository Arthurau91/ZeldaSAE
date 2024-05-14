package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Ennemi;
import com.example.zeldasae.modele.Entite;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane paneEntites;
    @FXML
    private TilePane mapPane;
    private Monde map;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Monde(new Joueur(20, 20));
        creerSprite(this.map.getJoueur());
        afficherMap();
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler(this.map, mapPane));
        creerSprite(new Ennemi(90, 90, "#1"));
    }

    public void afficherMap() {

        ArrayList<Integer> m = this.map.getTerrain().getMap();

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

    public void creerSprite(Entite e) {
        Circle c = new Circle(15);
        c.setId(e.getId());
        c.translateXProperty().bind(e.xProperty());
        c.translateYProperty().bind(e.yProperty());
        paneEntites.getChildren().add(c);

    }

    public void persoFocus(MouseEvent mouseEvent){
        paneEntites.requestFocus();

    }

}
