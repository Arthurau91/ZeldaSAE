package com.example.zeldasae.controller;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.*;
import com.example.zeldasae.Vue.*;
import com.example.zeldasae.modele.entities.*;
import com.example.zeldasae.modele.Monde;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane fenetre;
    @FXML
    private Pane boxInventaire;
    @FXML
    private Pane paneEntites;
    @FXML
    private Pane boxCoffre1;
    @FXML
    private Pane boxCoffre2;
    @FXML
    private TilePane mapPane;
    private ImageView fond;
    private Monde map;
    private Timeline gameLoop;
    private Button resetButton;
    private VueArme vueArme;
    private VueCollectible vueCollectible;
    private KeyHandler keyHandler;
    private IntegerProperty temps;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetButton = new Button();
        resetButton.setOnAction(actionEvent -> {
            resetButton.setDisable(true);
            resetButton.setVisible(false);
            fond.setVisible(false);
            lancementJeu();
            stopfreeze();
            resetButton.setOnAction(actionEvent1 -> {
                relanceJeu();
                stopfreeze();
            });
        });
        resetButton.setTranslateX(600);
        resetButton.setTranslateY(500);
        resetButton.setText("Lancer");
        resetButton.setDisable(false);
        resetButton.setVisible(true);
        fenetre.getChildren().add(resetButton);
        fond = new ImageView(new Image("file:src/main/resources/com/example/zeldasae/assets/fond.png", 1200, 1000, false, false));
        fenetre.getChildren().add(fond);
        resetButton.toFront();
    }

    private void lancementJeu(){
        this.temps = new SimpleIntegerProperty(0);
        LoadJSON loadJSON = new LoadJSON("src/main/resources/com/example/zeldasae/assets/map.json");
        this.mapPane.setPrefColumns(loadJSON.getPrefColumns());
        this.mapPane.setPrefRows(loadJSON.getPrefRows());
        this.mapPane.setPrefWidth(this.mapPane.getPrefTileWidth()*this.mapPane.getPrefColumns());
        this.mapPane.setPrefHeight(this.mapPane.getPrefTileHeight()*this.mapPane.getPrefRows());

        BFS bfs =new BFS();
        Joueur joueur = new Joueur(600, 510, mapPane.getPrefColumns(), mapPane.getPrefRows());
        VueJoueur vueJoueur = new VueJoueur(joueur, paneEntites, temps);
        joueur.pvProperty().addListener(new ObservateurVie(joueur, vueJoueur));

        this.map = new Monde(joueur, bfs, loadJSON.getPrefRows());

        initEnnemis();

        VueTerrain vueTerrain = new VueTerrain(this.map, this.mapPane, loadJSON.getMap(), loadJSON.getMap2());
        VueInventaire vueInv = new VueInventaire(this.boxInventaire, this.map.getJoueur());
        this.vueArme = new VueArme(this.map.getJoueur(), this.paneEntites, map, this.mapPane);
        this.vueCollectible = new VueCollectible(paneEntites, map);

        this.map.getJoueur().getInv().getListeItems().addListener(new ObservateurItems(vueInv, null));
        this.map.getListeProjectiles().addListener(new ObservateurProjectiles(vueArme));
        this.map.getListeCollectibles().addListener(new ObservateurCollectibles(vueCollectible));

        bfs.lanceAlgo(map, mapPane.getPrefColumns(), mapPane.getPrefRows());

        ObservateurMouvement observateurMouvement = new ObservateurMouvement(this.map, this.mapPane, this.paneEntites, vueJoueur);

        this.map.getJoueur().xProperty().addListener(observateurMouvement);
        this.map.getJoueur().yProperty().addListener(observateurMouvement);

        // Création des coffres

        Coffre coffre = new Coffre(2160, 2190, 0);
        VueCoffre vueCoffre = new VueCoffre(boxCoffre1, this.map.getJoueur(), coffre, vueInv);
        this.map.addCoffre(coffre);
        coffre.getListeItem().addListener(new ObservateurItems(null, vueCoffre));

        Coffre coffre2 = new Coffre(2640, 750, 1);
        VueCoffre vueCoffre2 = new VueCoffre(boxCoffre2, this.map.getJoueur(), coffre2, vueInv);
        this.map.addCoffre(coffre2);
        coffre2.getListeItem().addListener(new ObservateurItems(null, vueCoffre2));

        this.keyHandler = new KeyHandler(this.map, vueInv, vueTerrain, vueArme, vueCollectible, Arrays.asList(vueCoffre, vueCoffre2));

        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        paneEntites.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        initAnimation();

    }

    private void initEnnemis(){
        Skeleton skeleton = new Skeleton(500, 120, mapPane.getPrefColumns(),  mapPane.getPrefRows(), map.getBfs());
        skeleton.pvProperty().addListener(new ObservateurVie(skeleton, new VueSkeleton(skeleton, paneEntites, temps)));
        this.map.addEnnemi(skeleton);

        Boss boss = new Boss(740, 1800, 50, 65, mapPane.getPrefColumns(),  mapPane.getPrefRows(), map.getBfs());
        boss.pvProperty().addListener(new ObservateurVie(boss, new VueBoss(boss, paneEntites, temps)));
        this.map.addEnnemi(boss);

        Sentinelle sentinelle = new Sentinelle(500, 1800, mapPane.getPrefColumns(),  mapPane.getPrefRows(), map.getBfs());
        sentinelle.pvProperty().addListener(new ObservateurVie(sentinelle, new VueSentinelle(sentinelle, paneEntites, temps)));
        this.map.addEnnemi(sentinelle);
    }

    private void relanceJeu(){
        clearJeu();
        lancementJeu();
    }

    private void clearJeu(){
        paneEntites.getChildren().clear();
        paneEntites.setTranslateX(0);
        paneEntites.setTranslateY(0);
        mapPane.getChildren().clear();
        mapPane.setTranslateX(0);
        mapPane.setTranslateY(0);
        boxInventaire.getChildren().clear();
        boxInventaire.setTranslateX(0);
        boxInventaire.setTranslateY(0);
        this.map = null;
        resetButton.setDisable(true);
        resetButton.setVisible(false);
        fond.setVisible(false);
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.040),
                // on définit ce qui se passe à chaque frame
                (ev ->{
                    this.map.getJoueur().deplacement(map);

                    if (temps.getValue()%2==0) {
                        this.map.deplacementEnnemi();
                        this.map.deplacerProjectiles();
                    }

                    temps.setValue(temps.getValue()+1);
                    if (!map.getJoueur().verifVivant()) {
                        clearJeu();
                        paneEntites.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                        paneEntites.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                        resetButton.setDisable(false);
                        resetButton.setVisible(true);
                        boxInventaire.setVisible(false);
                        boxCoffre1.setVisible(false);
                        boxCoffre2.setVisible(false);
                        fond.setVisible(true);
                        gameLoop.stop();
                    }

                    this.vueCollectible.checkCollectiblesRamasses();
                    map.setEnnemisMorts();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void stopfreeze(){
        paneEntites.requestFocus();
        gameLoop.play();
    }

}
