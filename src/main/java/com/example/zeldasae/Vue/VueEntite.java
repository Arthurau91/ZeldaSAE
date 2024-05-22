package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueEntite {

    private Entite entite;
    private Pane paneEntites;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.entite = entite;
        this.paneEntites = paneEntites;
        creerImageEntite();
    }

    /**
     * Méthode qui crée un sprite sur le pane pour l'entite
     */
    public void creerImageEntite() {
        ImageView imgEntite = new ImageView();
        Image image;
        if (this.entite instanceof Joueur) {
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier.png");
        } else {
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/monstre.png");
        }
        imgEntite.setImage(image);

        imgEntite.setId(entite.getId());
        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public Entite getEntite() {
        return entite;
    }

}
