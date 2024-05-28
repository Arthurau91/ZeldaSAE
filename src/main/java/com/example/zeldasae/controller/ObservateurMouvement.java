package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Ennemi;
import com.example.zeldasae.modele.Monde;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class ObservateurMouvement implements ChangeListener<Number> {

    private final Monde map;
    private final TilePane mapPane;
    private final Pane paneEntites;
    private int positionPrecedenteX;
    private int positionPrecedenteY;

    public ObservateurMouvement(Monde map, TilePane mapPane, Pane paneEntites) {
        this.map = map;
        this.mapPane = mapPane;
        this.paneEntites = paneEntites;

        this.positionPrecedenteX = map.getJoueur().getX();
        this.positionPrecedenteY = map.getJoueur().getY();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        int currentX = map.getJoueur().getX();
        int currentY = map.getJoueur().getY();
        int deltaX = currentX - positionPrecedenteX;
        int deltaY = currentY - positionPrecedenteY;

        this.map.getJoueur().getVueBarreDeVie().setLayoutX(1050);
        this.map.getJoueur().getVueBarreDeVie().setLayoutY(10);

        if (deltaX != 0 || deltaY != 0) {

            mapPane.setTranslateX(mapPane.getTranslateX() - deltaX);
            mapPane.setTranslateY(mapPane.getTranslateY() - deltaY);
            paneEntites.setTranslateX(paneEntites.getTranslateX() - deltaX);
            paneEntites.setTranslateY(paneEntites.getTranslateY() - deltaY);

            map.getJoueur().getVueBarreDeVie().setTranslateX(map.getJoueur().getVueBarreDeVie().getTranslateX() + deltaX);
            map.getJoueur().getVueBarreDeVie().setTranslateY(map.getJoueur().getVueBarreDeVie().getTranslateY() + deltaY);

            positionPrecedenteX = currentX;
            positionPrecedenteY = currentY;

            afficheBarreDeVieEnnemi();
            actualisePositionEnnemi();

        }
    }

    public void actualisePositionEnnemi() {
        for (Ennemi ennemi : this.map.getListeEnnemis()) {
            ennemi.updatePositionBarreDeVie();
        }
    }

    public void afficheBarreDeVieEnnemi() {
        for (Ennemi ennemi : this.map.getListeEnnemis()) {
            if (!this.paneEntites.getChildren().contains(ennemi.getVueBarreDeVie())) {
                this.paneEntites.getChildren().add(ennemi.getVueBarreDeVie());
            }

        }
    }
}