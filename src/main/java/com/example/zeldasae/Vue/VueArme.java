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
    private boolean peutDonnerCoup;

    public VueArme (Joueur joueur, Pane paneEntites) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
        this.peutDonnerCoup = true;
    }

    public boolean isPeutDonnerCoup() {
        return this.peutDonnerCoup;
    }

    public void donnerCoup(int x, int y) {
        Image img = new Image ("file:src/main/resources/com/example/zeldasae/assets/" + joueur.getInv().getArmeActuelle().getNom() + "Attaque.png");
        ImageView imageView = new ImageView(img);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);

        imageView.setFitWidth(joueur.getInv().getArmeActuelle().getHitBox().getLarge());
        imageView.setFitHeight(joueur.getInv().getArmeActuelle().getHitBox().getHaut());

        paneEntites.getChildren().add(imageView);
        this.peutDonnerCoup = false;
        PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> gererCooldown(imageView));
        pause.play();
    }

    public void gererCooldown(ImageView imageView) {
        paneEntites.getChildren().remove(imageView);
        this.peutDonnerCoup = true;
    }
}
