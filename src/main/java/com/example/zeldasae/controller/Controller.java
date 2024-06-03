package com.example.zeldasae.controller;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.Vue.VueEntite;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Ennemi;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane boxInventaire;
    @FXML
    private Pane paneEntites;
    @FXML
    private TilePane mapPane;
    private Monde map;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoadJSON loadJSON = new LoadJSON("src/main/resources/com/example/zeldasae/assets/map.json");
        this.mapPane.setPrefColumns(loadJSON.getPrefColumns());
        this.mapPane.setPrefRows(loadJSON.getPrefRows());
        this.mapPane.setPrefWidth(this.mapPane.getPrefTileWidth()*this.mapPane.getPrefColumns());
        this.mapPane.setPrefHeight(this.mapPane.getPrefTileHeight()*this.mapPane.getPrefRows());

        BFS bfs =new BFS();
        this.map = new Monde(new Joueur(600, 500, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns(), mapPane.getPrefRows(), paneEntites), bfs);
        Ennemi ennemi = new Ennemi(120, 120, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns(),  mapPane.getPrefRows(), bfs, paneEntites);
        this.map.addEnnemi(ennemi);
        new VueTerrain(this.map, this.mapPane, loadJSON.getMap());
        VueInventaire vueInv = new VueInventaire(this.boxInventaire, this.map.getJoueur());
        this.map.getJoueur().getInv().getListeItems().addListener(new ObservateurItems(vueInv, this.paneEntites));
        bfs.lanceAlgo(map, mapPane.getPrefColumns(), mapPane.getPrefRows());

        ObservateurMouvement observateurMouvement = new ObservateurMouvement(this.map, this.mapPane, this.paneEntites);

        this.map.getJoueur().xProperty().addListener(observateurMouvement);
        this.map.getJoueur().yProperty().addListener(observateurMouvement);

        KeyHandler keyHandler = new KeyHandler(this.map, vueInv);
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        paneEntites.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        initAnimation();
        paneEntites.getChildren().add(this.map.getJoueur().getVueBarreDeVie());

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.040),
                // on définit ce qui se passe à chaque frame
                (ev ->{
                    this.map.getJoueur().deplacement(map);

                    if (temps%2==0)
                        this.map.deplacementEnnemi();
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    /**
     * Méthode permettant de mettre le focus sur le pane des entitées pour pouvoir faire des actions avec les touches
     * @param mouseEvent l'event du clic
     */
    public void persoFocus(MouseEvent mouseEvent){
        paneEntites.requestFocus();
        gameLoop.play();
    }

}
