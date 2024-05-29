package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueArme;
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
        this.mapPane.setPrefColumns(40);
        this.mapPane.setPrefRows(40);
        this.mapPane.setPrefWidth(this.mapPane.getPrefTileWidth()*this.mapPane.getPrefColumns());
        this.mapPane.setPrefHeight(this.mapPane.getPrefTileHeight()*this.mapPane.getPrefRows());

        this.map = new Monde(new Joueur((int)mapPane.getPrefWidth()/2, (int)mapPane.getPrefHeight()/2, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns(), mapPane.getPrefRows()));
        Ennemi ennemi = new Ennemi(150, 120, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns(),  mapPane.getPrefRows());
        this.map.addEnnemi(ennemi);
        VueEntite vueJoueur = new VueEntite(this.map.getJoueur(), this.paneEntites);
        VueTerrain vueTerrain = new VueTerrain(this.map, this.mapPane);
        VueEntite vueEnnemi = new VueEntite(ennemi,paneEntites);
        VueInventaire vueInv = new VueInventaire(this.boxInventaire, this.map.getJoueur());
        this.map.getJoueur().getInv().getListeItems().addListener(new ObservateurItems(vueInv, this.paneEntites));
        VueArme vueArme = new VueArme(this.map.getJoueur(), this.paneEntites);

        this.map.getJoueur().getBarreDeVie().setLayoutX(1050);
        this.map.getJoueur().getBarreDeVie().setLayoutY(10);

        KeyHandler keyHandler = new KeyHandler(this.map, vueInv, vueArme);
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        paneEntites.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        initAnimation();
        paneEntites.getChildren().add(this.map.getJoueur().getBarreDeVie());
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
                    int x1 = map.getJoueur().getX(), y1 = map.getJoueur().getY();
                    this.map.getJoueur().deplacement(map);
                    int depx = map.getJoueur().getX() -x1, depy = map.getJoueur().getY() - y1;

                    mapPane.setTranslateY(mapPane.getTranslateY()-depy);
                    mapPane.setTranslateX(mapPane.getTranslateX()-depx);
                    paneEntites.setTranslateY(paneEntites.getTranslateY()-depy);
                    paneEntites.setTranslateX(paneEntites.getTranslateX()-depx);
                    map.getJoueur().getBarreDeVie().setTranslateX(map.getJoueur().getBarreDeVie().getTranslateX()+depx);
                    map.getJoueur().getBarreDeVie().setTranslateY(map.getJoueur().getBarreDeVie().getTranslateY()+depy);

                    if (temps%2==0)
                        this.map.deplacementEnnemi();

                    afficheBarreDeVieEnnemi();
                    actualisePositionEnnemi();
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public void actualisePositionEnnemi() {
        for (Ennemi ennemi : this.map.getListeEnnemis()) {
            ennemi.updatePositionBarreDeVie();
        }
    }

    public void afficheBarreDeVieEnnemi() {
        for (Ennemi ennemi : this.map.getListeEnnemis()) {
            if (!this.paneEntites.getChildren().contains(ennemi.getBarreDeVie())) {
                this.paneEntites.getChildren().add(ennemi.getBarreDeVie());
            }
        }
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
