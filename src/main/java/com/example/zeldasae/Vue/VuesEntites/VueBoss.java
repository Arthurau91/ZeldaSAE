package com.example.zeldasae.Vue.VuesEntites;

import com.example.zeldasae.Vue.VueBarreDeVie;
import com.example.zeldasae.modele.entities.Boss;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueBoss extends VueEntite {

    public VueBoss(Boss entite, Pane paneEntites, IntegerProperty temps) {
        super(entite, paneEntites, temps);
        super.loadSprites(new Image("file:src/main/resources/com/example/zeldasae/assets/Boss/Boss.png", 144, 256, false, false), 48, 64);
        statusAnim = 0;
        super.creerImageEntite();
        this.vueBarreDeVie = new VueBarreDeVie(90, 10, paneEntites);
        bindBarreDeViePosition();
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
