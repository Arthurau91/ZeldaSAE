package com.example.zeldasae.Vue.VueArmes;

import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Bombe;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class VueBombe extends VueArme {

    public VueBombe(Joueur joueur, Pane paneEntites, Monde map, VueTerrain vueTerrain) {
        super(joueur, paneEntites, map, vueTerrain);
    }

    @Override
    public void donnerCoup(int x, int y, KeyEvent keyEvent) {
        afficherBombe(keyEvent);

        PauseTransition pause = new PauseTransition(Duration.seconds(Bombe.delaiExplosion));
        pause.setOnFinished(event -> {
            afficherExplosion(x, y);
            vueTerrain.explosion(x, y);
        });
        pause.play();
    }

    public void afficherExplosion(int x, int y) {
        Image image = new Image("file:src/main/resources/com/example/zeldasae/assets/explosion.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(getJoueur().getInv().getArmeActuelle().getHitBox().getLarge());
        imageView.setFitHeight(getJoueur().getInv().getArmeActuelle().getHitBox().getHaut());
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);

        getPaneEntites().getChildren().add(imageView);

        PauseTransition pauseHitbox = new PauseTransition(Duration.seconds(1));
        pauseHitbox.setOnFinished(event -> getPaneEntites().getChildren().remove(imageView));
        pauseHitbox.play();
    }

    public void afficherBombe(KeyEvent keyEvent) {
        Image image = new Image("file:src/main/resources/com/example/zeldasae/assets/BombeAttaque.png");
        ImageView imageView = new ImageView(image);
        int largeur = 30, hauteur = 30;
        imageView.setFitWidth(largeur);
        imageView.setFitHeight(hauteur);
        imageView.setTranslateX(getJoueur().getX());
        imageView.setTranslateY(getJoueur().getY());

        switch (keyEvent.getCode()) {
            case LEFT:
                imageView.setTranslateX(getJoueur().getX()-largeur);
                break;
            case RIGHT:
                imageView.setTranslateX(getJoueur().getX()+largeur);
                break;
            case UP:
                imageView.setTranslateY(getJoueur().getY()-hauteur);
                break;
            case DOWN:
                imageView.setTranslateY(getJoueur().getY()+hauteur);
                break;
        }

        getPaneEntites().getChildren().add(imageView);

        PauseTransition pause = new PauseTransition(Duration.seconds(Bombe.delaiExplosion));
        pause.setOnFinished(event -> getPaneEntites().getChildren().remove(imageView));
        pause.play();
    }

}
