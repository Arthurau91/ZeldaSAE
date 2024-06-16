package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.entities.Joueur;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueJoueur extends VueEntite{
    private int statusStatique;

    public VueJoueur(Joueur entite, Pane paneEntites, IntegerProperty temps) {
        super(entite, paneEntites, temps);
        super.loadSprites(new Image("file:src/main/resources/com/example/zeldasae/assets/joueur/link.png", 300, 240, false, false), 30, 30);
        statusAnim = 0;
        statusStatique = 0;
        this.vueBarreDeVie = new VueBarreDeVie(100, 20, paneEntites);
        vueBarreDeVie.setLayoutX(1050);
        vueBarreDeVie.setLayoutY(10);
        super.creerImageEntite();
    }

    @Override
    public Image getImageBas() {
        return sprites[4*10+((statusAnim++)%10)];
    }
    @Override
    public Image getImageDroite() {
        return sprites[7*10+((statusAnim++)%10)];
    }
    @Override
    public Image getImageGauche() {
        return sprites[5*10+((statusAnim++)%10)];
    }
    @Override
    public Image getImageHaut() {
        return sprites[6*10+((statusAnim++)%10)];
    }

    @Override
    public Image getImageStatiqueBas() {
        return sprites[((statusStatique++)%3)];
    }
    @Override
    public Image getImageStatiqueHaut() {
        return sprites[2*10];
    }
    @Override
    public Image getImageStatiqueDroite() {
        return sprites[3*10+((statusStatique++)%3)];
    }
    @Override
    public Image getImageStatiqueGauche() {
        return sprites[10+((statusStatique++)%3)];
    }
}
