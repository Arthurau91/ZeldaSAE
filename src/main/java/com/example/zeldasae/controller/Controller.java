package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueEntite;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.*;
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
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane paneEntites;
    @FXML
    private TilePane mapPane;
    private Monde map;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Monde(new Joueur(20, 20, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns()));
        Ennemi ennemi = new Ennemi(90, 90, "#1", (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns());
        this.map.addEnnemi(ennemi);

        VueEntite vueJoueur = new VueEntite(this.map.getJoueur(), this.paneEntites);
        VueTerrain vueTerrain = new VueTerrain(this.map, this.mapPane);
        VueEntite vueEnnemi = new VueEntite(ennemi,paneEntites);

        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler(this.map));
        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'où le lambda
                (ev ->{
//                    if(temps==100){//temps à 100 à changer pour faire un exemple plus long
//                        System.out.println("fini");
//                        gameLoop.stop();
//                    }
//                    else
                    if (temps%5==0){
                        System.out.println("un tour");
                        this.map.deplacementEnnemi();
                    }
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

    }

}
