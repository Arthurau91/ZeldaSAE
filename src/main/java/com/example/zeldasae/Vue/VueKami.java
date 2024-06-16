package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.entities.Kami;
import javafx.animation.PauseTransition;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class VueKami extends VueEntite{

    private VueTerrain vueTerrain;

    public VueKami(Kami entite, Pane paneEntites, IntegerProperty temps, VueTerrain vueTerrain) {
        super(entite, paneEntites, temps);
        super.loadSprites(new Image("file:src/main/resources/com/example/zeldasae/assets/Kami/Kami.png", 240, 90, false, false), 30, 30);
        statusAnim = 0;
        this.vueBarreDeVie = new VueBarreDeVie(90, 10, paneEntites);
        bindBarreDeViePosition();
        super.creerImageEntite();
        this.vueTerrain = vueTerrain;
    }

    @Override
    public void supprimerImageEntite() {
        Image image = new Image("file:src/main/resources/com/example/zeldasae/assets/explosion.gif", 90, 90, false, false);
        imgEntite.setImage(image);
        getEntite().setX(getEntite().getX()-30);
        getEntite().setY(getEntite().getY()-30);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            super.supprimerImageEntite();
            vueTerrain.spawnBombe(getEntite().getX()+30, getEntite().getY()+30);
        });
        pause.play();
        vueTerrain.explosion(getEntite().getX(), getEntite().getY());
    }

    @Override
    public Image getImageBas() {
        return sprites[2*8+((statusAnim++)%8)];
    }

    @Override
    public Image getImageHaut() {
        return sprites[2*8+((statusAnim++)%8)];
    }

    @Override
    public Image getImageDroite() {
        return sprites[8+((statusAnim++)%8)];
    }

    @Override
    public Image getImageGauche() {
        return sprites[((statusAnim++)%8)];
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
