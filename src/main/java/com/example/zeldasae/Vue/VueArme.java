package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Joueur;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class VueArme {

    private Joueur joueur;
    private Pane paneEntites;

    public VueArme (Joueur joueur, Pane paneEntites) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
    }

    public void donnerCoup(int x, int y) {
        Image img = new Image ("file:src/main/resources/com/example/zeldasae/assets/" + joueur.getInv().getArmeActuelle().getNom() + "Attaque.png");
        ImageView imageView = new ImageView(img);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);

        imageView.setFitWidth(joueur.getInv().getArmeActuelle().getHitBox().getLarge());
        imageView.setFitHeight(joueur.getInv().getArmeActuelle().getHitBox().getHaut());

        paneEntites.getChildren().add(imageView);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> paneEntites.getChildren().remove(imageView));
        pause.play();
    }

}
