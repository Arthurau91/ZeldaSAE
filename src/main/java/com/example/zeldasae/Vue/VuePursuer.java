package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.entities.Pursuer;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VuePursuer extends VueEntite{

    private String imageDroite = "file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png";
    private String imageGauche = "file:src/main/resources/com/example/zeldasae/assets/monstre/monstredroite.png";
    private String imageHaut = "file:src/main/resources/com/example/zeldasae/assets/monstre/monstrebas.png";
    private String imageBas = "file:src/main/resources/com/example/zeldasae/assets/monstre/monstrebas.png";

    public VuePursuer(Pursuer entite, Pane paneEntites, IntegerProperty temps) {
        super(entite, paneEntites, temps);
        super.creerImageEntite();
    }
    @Override
    public Image getImageBas() {
        return new Image(imageBas);
    }
    @Override
    public Image getImageDroite() {
        return new Image(imageDroite);
    }
    @Override
    public Image getImageGauche() {
        return new Image(imageGauche);
    }
    @Override
    public Image getImageHaut() {
        return new Image(imageHaut);
    }

    @Override
    public Image getImageStatiqueBas() {
        return getImageBas();
    }
    @Override
    public Image getImageStatiqueHaut() {
        return getImageHaut();
    }
    @Override
    public Image getImageStatiqueDroite() {
        return getImageDroite();
    }
    @Override
    public Image getImageStatiqueGauche() {
        return getImageGauche();
    }
}
