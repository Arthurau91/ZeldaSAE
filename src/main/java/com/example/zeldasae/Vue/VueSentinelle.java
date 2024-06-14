package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.entities.Sentinelle;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public class VueSentinelle extends VueEntite{

    private int statusAnim;

    public VueSentinelle(Sentinelle entite, Pane paneEntites, IntegerProperty temps) {
        super(entite, paneEntites, temps);
        super.loadSprites(new Image("file:src/main/resources/com/example/zeldasae/assets/Sentinelle/Sentinelle.png", 90, 120, false, false), 30, 30);
        statusAnim = 0;
        super.creerImageEntite();
    }

    @Override
    public Image getImageBas() {
        return sprites[2*3+((statusAnim++)%3)];
    }
    @Override
    public Image getImageDroite() {
        return sprites[3+((statusAnim++)%3)];
    }
    @Override
    public Image getImageGauche() {
        return sprites[3*3+((statusAnim++)%3)];
    }
    @Override
    public Image getImageHaut() {
        return sprites[((statusAnim++)%3)];
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
