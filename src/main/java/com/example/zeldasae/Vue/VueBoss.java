package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import javafx.scene.layout.Pane;

public class VueBoss extends VueEntite{

    private String imageDroite = "file:src/main/resources/com/example/zeldasae/assets/boss.png";
    private String imageGauche = "file:src/main/resources/com/example/zeldasae/assets/boss.png";
    private String imageHaut = "file:src/main/resources/com/example/zeldasae/assets/boss.png";
    private String imageBas = "file:src/main/resources/com/example/zeldasae/assets/boss.png";

    public VueBoss(Entite entite, Pane paneEntites) {
        super(entite, paneEntites);
        super.creerImageEntite();
    }
    @Override
    public String getImageBas() {
        return imageBas;
    }
    @Override
    public String getImageDroite() {
        return imageDroite;
    }
    @Override
    public String getImageGauche() {
        return imageGauche;
    }
    @Override
    public String getImageHaut() {
        return imageHaut;
    }
}
