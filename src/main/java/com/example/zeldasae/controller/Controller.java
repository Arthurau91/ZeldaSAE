package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueJoueur;
import com.example.zeldasae.Vue.VueTerrain;
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
    private KeyHandler keyHandler;
    private Joueur joueur;
    private VueJoueur vueJoueur;
    private VueTerrain vueTerrain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.joueur = new Joueur(20, 20);
        this.map = new Monde(this.joueur);
        this.keyHandler = new KeyHandler(this.map);
        this.vueJoueur = new VueJoueur(this.joueur, this.paneEntites);
        this.vueTerrain = new VueTerrain(this.map, this.mapPane);
        this.vueJoueur.creerSpriteJoueur(this.joueur);
        this.vueTerrain.afficherMap();
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, this.keyHandler);
    }






    public void persoFocus(MouseEvent mouseEvent){
        paneEntites.requestFocus();
    }

}
