package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Pursuer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class VueEntite {

    private Entite entite;
    private Pane paneEntites;
    private String directionImage;
    private ImageView imgEntite;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.directionImage = "right";
        this.entite = entite;
        this.paneEntites = paneEntites;
    }

    public void creerImageEntite() {
        imgEntite = new ImageView();
        Image image;
        image = this.getImageDroite();
        imgEntite.setImage(image);

        imgEntite.setId(entite.getId());
        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public void changeImage(){
        if (entite.getDirection().contains("up")) {
            imgEntite.setImage(this.getImageHaut());
            directionImage = "up";
        }
        if (entite.getDirection().contains("down")) {
            imgEntite.setImage(this.getImageBas());
            directionImage = "down";
        }
        if (entite.getDirection().contains("right")) {
            imgEntite.setImage(this.getImageDroite());
            directionImage = "right";
        }
        if (entite.getDirection().contains("left")){
            imgEntite.setImage(this.getImageGauche());
            directionImage = "left";
        }
    }


    public abstract Image getImageBas();
    public abstract Image getImageHaut();
    public abstract Image getImageDroite();
    public abstract Image getImageGauche();
    public String getDirectionImage() {
        return directionImage;
    }
}
