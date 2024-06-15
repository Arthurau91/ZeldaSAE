package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.entities.Skeleton;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueSkeleton extends VueEntite{

    private int statusAnim;

    public VueSkeleton(Skeleton entite, Pane paneEntites, IntegerProperty temps) {
        super(entite, paneEntites, temps);
        super.loadSprites(new Image("file:src/main/resources/com/example/zeldasae/assets/Skeleton/skeleton.png", 390, 630, false, false), 30, 30);
        statusAnim = 0;
        this.vueBarreDeVie = new VueBarreDeVie(90, 10, paneEntites);
        bindBarreDeViePosition();
        super.creerImageEntite();
    }

    @Override
    public Image getImageBas() {
        return sprites[10*13+((statusAnim++)%9)];
    }
    @Override
    public Image getImageDroite() {
        return sprites[11*13+((statusAnim++)%9)];
    }
    @Override
    public Image getImageGauche() {
        return sprites[9*13+((statusAnim++)%9)];
    }
    @Override
    public Image getImageHaut() {
        return sprites[8*13+((statusAnim++)%9)];
    }

    @Override
    public Image getImageStatiqueBas() {
        return sprites[6*13+((statusAnim++)%8)];
    }
    @Override
    public Image getImageStatiqueHaut() {
        return sprites[4*13+((statusAnim++)%8)];
    }
    @Override
    public Image getImageStatiqueDroite() {
        return sprites[7*13+((statusAnim++)%8)];
    }
    @Override
    public Image getImageStatiqueGauche() {
        return sprites[5*13+((statusAnim++)%8)];
    }

}
