package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    private HBox boxInventaire;
    @FXML
    private Pane paneEntites;
    @FXML
    private TilePane mapPane;
    private Monde map;
    private KeyHandler keyHandler;
    private VueInventaire vueInv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Monde(new Joueur(15, 15));
        this.vueInv = new VueInventaire(this.boxInventaire);
        this.keyHandler = new KeyHandler(this.map, mapPane, this.vueInv);
        creerSpriteJoueur(this.map.getJoueur());
        afficherMap();
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, this.keyHandler);
        this.vueInv.creerBoxInventaire();
        this.map.getJoueur().getInv().getListeItems().addListener(new ObservateurItems(this.boxInventaire));
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
        Circle c = new Circle(15);
        c.setId(j.getId());
        c.translateXProperty().bind(j.xProperty());
        c.translateYProperty().bind(j.yProperty());
        paneEntites.getChildren().add(c);

    }


    public void persoFocus(MouseEvent mouseEvent){
        paneEntites.requestFocus();
    }

}
