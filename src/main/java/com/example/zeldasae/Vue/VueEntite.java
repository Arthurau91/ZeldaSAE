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
        image = new Image(this.getImageDroite());
        imgEntite.setImage(image);

        imgEntite.setId(entite.getId());
        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public void changeImage(){
        if (entite.getDirection().contains("up")) {
            imgEntite.setImage(new Image(this.getImageHaut()));
            directionImage = "up";
        }
        if (entite.getDirection().contains("down")) {
            imgEntite.setImage(new Image(this.getImageBas()));
            directionImage = "down";
        }
        if (entite.getDirection().contains("right")) {
            imgEntite.setImage(new Image(this.getImageDroite()));
            directionImage = "right";
        }
        if (entite.getDirection().contains("left")){
            imgEntite.setImage(new Image(this.getImageGauche()));
            directionImage = "left";
        }
    }


    public abstract String getImageBas();
    public abstract String getImageHaut();
    public abstract String getImageDroite();
    public abstract String getImageGauche();
    public String getDirectionImage() {
        return directionImage;
    }
}
