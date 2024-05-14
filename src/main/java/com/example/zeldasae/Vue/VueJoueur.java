package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Joueur;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class VueJoueur {

    private Joueur joueur;
    private Pane paneEntites;

    public VueJoueur(Joueur joueur, Pane paneEntites) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
    }

    public void creerSpriteJoueur(Joueur j) {
        Circle c = new Circle(20);
        c.setId(j.getId());
        c.translateXProperty().bind(j.xProperty());
        c.translateYProperty().bind(j.yProperty());
        this.paneEntites.getChildren().add(c);

    }

}
