package com.example.zeldasae.controller;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.Main;
import com.example.zeldasae.Vue.VueArmes.CreateurVueArme;
import com.example.zeldasae.Vue.*;
import com.example.zeldasae.Vue.VuesEntites.VueJoueur;
import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Epee;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private Label labelMort;
    private Label labelHelp;
    private CreateurVueArme createurVueArme;
    private VueProjectile vueProjectile;
    private VueCollectible vueCollectible;
    private KeyHandler keyHandler;
    private IntegerProperty temps;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelMort = new Label("Vous êtes Mort !");
        labelHelp = new Label("       Aides Touches :\n  Z,Q,S,D : Mouvements\nTAB : Ouvrir l'inventaire\n← ↑ → ↓ : Attaquer\n" +
                "Clic Gauche(dans l'inventaire) : Equiper/Utiliser Item/Arme\n  A : Changer d'arme équipée(2 emplacements possibles)\n" +
                "       SHIFT : Pousser une boite");
        labelMort.setTextFill(Color.RED);
        labelMort.setFont(new Font("Calibri", 36));
        labelHelp.setTextFill(Color.WHITE);
        labelHelp.setFont(new Font("Calibri", 24));
        labelHelp.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(60), null)));
        labelMort.setTranslateX(480);
        labelMort.setTranslateY(250);
        resetButton = new Button();
        resetButton.setOnAction(actionEvent -> {
            labelHelp.setVisible(false);
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
        resetButton.setTranslateX(595);
        resetButton.setTranslateY(500);
        resetButton.setText("Lancer");
        resetButton.setDisable(false);
        resetButton.setVisible(true);
        fenetre.getChildren().add(resetButton);
        fond = new ImageView(new Image(Main.class.getResourceAsStream("assets/fond.png"), 1200, 1000, false, false));
        fenetre.getChildren().add(fond);
        fenetre.getChildren().add(labelMort);
        fenetre.getChildren().add(labelHelp);
        resetButton.toFront();
        labelMort.toFront();
        labelHelp.toFront();
        labelMort.setVisible(false);
        labelHelp.setVisible(true);
    }

    private void lancementJeu(){
        this.temps = new SimpleIntegerProperty(0);
        LoadJSON loadJSON = new LoadJSON("assets/map.json");
        this.mapPane.setPrefColumns(loadJSON.getPrefColumns());
        this.mapPane.setPrefRows(loadJSON.getPrefRows());
        this.mapPane.setPrefWidth(this.mapPane.getPrefTileWidth()*this.mapPane.getPrefColumns());
        this.mapPane.setPrefHeight(this.mapPane.getPrefTileHeight()*this.mapPane.getPrefRows());

        BFS bfs =new BFS();
        Joueur joueur = new Joueur(600, 510, mapPane.getPrefColumns(), mapPane.getPrefRows());
        VueJoueur vueJoueur = new VueJoueur(joueur, paneEntites, temps);
        joueur.pvProperty().addListener(new ObservateurVie(joueur, vueJoueur));

        this.map = new Monde(joueur, bfs, loadJSON.getPrefRows(), loadJSON.getPrefColumns());

        VueTerrain vueTerrain = new VueTerrain(this.map, this.mapPane, loadJSON.getMap(), loadJSON.getMap2());
        this.map.getListeEnnemis().addListener(new ObservateurEnnemis(paneEntites, temps, vueTerrain));
        this.map.initEnnemis();


        VueInventaire vueInv = new VueInventaire(this.boxInventaire, this.map.getJoueur());
        this.createurVueArme = new CreateurVueArme(joueur, this.paneEntites, map, vueTerrain);
        this.vueCollectible = new VueCollectible(paneEntites);
        this.vueProjectile = new VueProjectile(paneEntites);

        joueur.getInv().getListeItems().addListener(new ObservateurItems(vueInv));
        joueur.getInv().ajouterItem(new Epee());
        this.map.getListeProjectiles().addListener(new ObservateurProjectiles(vueProjectile));
        this.map.getListeCollectibles().addListener(new ObservateurCollectibles(vueCollectible));

        bfs.lanceAlgo(map, mapPane.getPrefColumns(), mapPane.getPrefRows());

        ObservateurMouvement observateurMouvement = new ObservateurMouvement(this.map, this.mapPane, this.paneEntites, vueJoueur);

        joueur.xProperty().addListener(observateurMouvement);
        joueur.yProperty().addListener(observateurMouvement);

        // Création des coffres

        Coffre coffre = new Coffre(2160, 2190, 0);
        VueCoffre vueCoffre = new VueCoffre(boxCoffre1, joueur, coffre, vueInv);
        this.map.addCoffre(coffre);
        coffre.getListeItem().addListener(new ObservateurItemsCoffre(vueCoffre));

        Coffre coffre2 = new Coffre(2640, 750, 1);
        VueCoffre vueCoffre2 = new VueCoffre(boxCoffre2, joueur, coffre2, vueInv);
        this.map.addCoffre(coffre2);
        coffre2.getListeItem().addListener(new ObservateurItemsCoffre(vueCoffre2));

        map.initCoffres();

        this.keyHandler = new KeyHandler(this.map, vueInv, vueTerrain, createurVueArme, Arrays.asList(vueCoffre, vueCoffre2));

        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        paneEntites.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        initAnimation();
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
        boxCoffre1.getChildren().clear();
        boxCoffre2.getChildren().clear();
        this.map = null;
        resetButton.setDisable(true);
        resetButton.setVisible(false);
        fond.setVisible(false);
        labelHelp.setVisible(false);
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.040),
                (ev ->{
                    this.map.getJoueur().deplacement(map);

                    if (temps.getValue()%2==0) {
                        this.map.deplacementEnnemi();
                        this.map.deplacerProjectiles();
                    }

                    temps.setValue(temps.getValue()+1);
                    map.setEnnemisMorts();
                    if (!map.getJoueur().verifVivant()) {
                        labelMort.setVisible(true);
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.setOnFinished(event -> {
                            clearJeu();
                            paneEntites.removeEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
                            paneEntites.removeEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
                            resetButton.setDisable(false);
                            resetButton.setVisible(true);
                            boxInventaire.setVisible(false);
                            boxCoffre1.setVisible(false);
                            boxCoffre2.setVisible(false);
                            fond.setVisible(true);
                            labelMort.setVisible(false);
                            labelHelp.setVisible(true);
                        });
                        pause.play();
                        gameLoop.stop();
                    }

                    map.checkCollectiblesRamasses();
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private void stopfreeze(){
        paneEntites.requestFocus();
        gameLoop.play();
    }

}
