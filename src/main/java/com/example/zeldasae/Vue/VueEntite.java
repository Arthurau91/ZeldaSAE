package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class VueEntite {

    private Entite entite;
    private Pane paneEntites;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.entite = entite;
        this.paneEntites = paneEntites;
        if (entite instanceof Joueur){
            creerImageJoueur();
        }
        else creerSpriteEntite();
    }

    /**
     * Méthode qui crée un sprite sur le pane pour l'entite
     */
    public void creerSpriteEntite() {
        ImageView imgMonstre = new ImageView();
        Image image = new Image("file:src/main/resources/com/example/zeldasae/assets/monstre.png");
        imgMonstre.setImage(image);

        imgMonstre.setId(entite.getId());
        imgMonstre.translateXProperty().bind(entite.xProperty());
        imgMonstre.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgMonstre);
    }

    public void creerImageJoueur() {
        ImageView imgJoueur = new ImageView();
        Image image = new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier.png");
        imgJoueur.setImage(image);

        imgJoueur.setId(entite.getId());
        imgJoueur.translateXProperty().bind(entite.xProperty());
        imgJoueur.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgJoueur);
    }
}
