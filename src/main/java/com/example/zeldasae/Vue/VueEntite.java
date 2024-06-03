package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueEntite {

    private Entite entite;
    private Pane paneEntites;

    private ImageView imgEntite;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.entite = entite;
        this.paneEntites = paneEntites;
        creerImageEntite();
    }

    /**
     * Méthode qui crée un sprite sur le pane pour l'entite
     */
    public void creerImageEntite() {
        imgEntite = new ImageView();
        Image image;
        if (this.entite instanceof Joueur) {
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierdroite.png");
        } else {
            image = new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png");
        }
        imgEntite.setImage(image);

        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public void supprimerImageEntite() {
        imgEntite.setId(entite.getId());
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + entite.getId()));}


    public void changeImage(){
        if (this.entite instanceof Joueur) {
            if (entite.getDirection().contains("up"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierhaut.png"));
            if (entite.getDirection().contains("down"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierbas.png"));
            if (entite.getDirection().contains("right"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevalierdroite.png"));
            if (entite.getDirection().contains("left"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/chevalier/chevaliergauche.png"));
        }
        else {
            if (entite.getDirection().contains("up"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstrebas.png"));
            if (entite.getDirection().contains("down"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstrebas.png"));
            if (entite.getDirection().contains("right"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png"));
            if (entite.getDirection().contains("left"))
                imgEntite.setImage(new Image("file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png"));
        }
    }
}
