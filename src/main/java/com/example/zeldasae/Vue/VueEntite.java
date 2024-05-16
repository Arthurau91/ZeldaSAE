package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class VueEntite {

    private Entite entite;
    private Pane paneEntites;

    public VueEntite(Entite entite, Pane paneEntites) {
        this.entite = entite;
        this.paneEntites = paneEntites;
        creerSpriteEntite(entite);
    }


    public void creerSpriteEntite(Entite e) {
        Circle c = new Circle(20);
        c.setId(e.getId());
        c.translateXProperty().bind(e.xProperty());
        c.translateYProperty().bind(e.yProperty());
        this.paneEntites.getChildren().add(c);

    }
}
