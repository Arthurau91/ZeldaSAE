package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueBoss extends VueEntite{

    private String imageDroite = "file:src/main/resources/com/example/zeldasae/assets/boss.png";
    private String imageGauche = "file:src/main/resources/com/example/zeldasae/assets/boss.png";
    private String imageHaut = "file:src/main/resources/com/example/zeldasae/assets/boss.png";
    private String imageBas = "file:src/main/resources/com/example/zeldasae/assets/boss.png";

    public VueBoss(Entite entite, Pane paneEntites) {
        super(entite, paneEntites);
        super.creerImageEntite();
        this.vueBarreDeVie = new VueBarreDeVie(90, 10, paneEntites);
        bindBarreDeViePosition();
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
}
