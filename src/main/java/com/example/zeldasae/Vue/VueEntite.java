package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class VueEntite {

    private Entite entite;
    private Pane paneEntites;
    private ImageView imgEntite;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.entite = entite;
        this.paneEntites = paneEntites;
        this.entite.directionProperty().addListener((obs, old, nouv)-> changeImage(nouv));
    }

    public void creerImageEntite() {
        imgEntite = new ImageView();
        Image image;
        image = this.getImageDroite();
        imgEntite.setImage(image);

        //mettre une directionProperty avec un listener
        imgEntite.setId(entite.getId());
        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public void changeImage(String nouv){
        if (nouv.contains("up")) {
            imgEntite.setImage(this.getImageHaut());
        }
        if (nouv.contains("down")) {
            imgEntite.setImage(this.getImageBas());
        }
        if (nouv.contains("right")) {
            imgEntite.setImage(this.getImageDroite());
        }
        if (nouv.contains("left")){
            imgEntite.setImage(this.getImageGauche());
        }
    }

    public abstract Image getImageBas();
    public abstract Image getImageHaut();
    public abstract Image getImageDroite();
    public abstract Image getImageGauche();
}
