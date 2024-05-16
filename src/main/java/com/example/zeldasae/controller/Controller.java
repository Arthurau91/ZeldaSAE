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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        this.mapPane.setPrefColumns(40);
        this.mapPane.setPrefRows(40);
        this.mapPane.setPrefWidth(this.mapPane.getPrefTileWidth()*this.mapPane.getPrefColumns());
        this.mapPane.setPrefHeight(this.mapPane.getPrefTileHeight()*this.mapPane.getPrefRows());
        this.joueur = new Joueur(15, 15);
        this.map = new Monde(this.joueur);
        System.out.println(map.getMap().size());
        this.vueJoueur = new VueJoueur(this.joueur, this.paneEntites);
        this.vueTerrain = new VueTerrain(this.map, this.mapPane);
        this.vueJoueur.creerSpriteJoueur(this.joueur);
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler(map, mapPane));
        this.vueTerrain.afficherMap();
    }





    public void persoFocus(MouseEvent mouseEvent){
        paneEntites.requestFocus();
    }

}
