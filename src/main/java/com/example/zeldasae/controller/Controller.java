package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueJoueur;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane paneEntites;
    @FXML
    private TilePane mapPane;
    private Monde map;
    private Timeline gameLoop;
    private int temps;
    private VueJoueur vueJoueur;
    private VueTerrain vueTerrain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Monde(new Joueur(20, 20, (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns()));
        this.vueJoueur = new VueJoueur(this.map.getJoueur(), this.paneEntites);
        this.vueTerrain = new VueTerrain(this.map.getJoueur(), this.mapPane);
        this.vueJoueur.creerSpriteJoueur(this.map.getJoueur());
        afficherMap();
        paneEntites.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler(this.map));
//        Ennemi ennemi = new Ennemi(90, 90, "#1", (int)mapPane.getPrefTileWidth(), (int)mapPane.getPrefTileHeight(), mapPane.getPrefColumns());
//        this.map.addEnnemi(ennemi);
//        creerSprite(ennemi);
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
