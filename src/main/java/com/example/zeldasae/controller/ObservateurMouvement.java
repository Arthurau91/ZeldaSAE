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

    public ObservateurMouvement(Monde map, TilePane mapPane, Pane paneEntites) {
        this.map = map;
        this.mapPane = mapPane;
        this.paneEntites = paneEntites;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (observable == this.map.getJoueur().xProperty()) {
            int deltaX = newValue.intValue() - oldValue.intValue();
            miseAJourDeplacement(deltaX, 0);
        } else {
            int deltaY = newValue.intValue() - oldValue.intValue();
            miseAJourDeplacement(0, deltaY);
        }

        actualiserEnnemi();
    }


    private void miseAJourDeplacement(int deltaX, int deltaY) {

        if (deltaX != 0 || deltaY != 0) {
            scrollMap(deltaX, deltaY);
            mettreAJourPositionBarreDeVieJoueur(deltaX, deltaY);
        }

    }

    private void scrollMap(int deltaX, int deltaY) {
        this.mapPane.setTranslateX(mapPane.getTranslateX() - deltaX);
        this.mapPane.setTranslateY(mapPane.getTranslateY() - deltaY);
        this.paneEntites.setTranslateX(paneEntites.getTranslateX() - deltaX);
        this.paneEntites.setTranslateY(paneEntites.getTranslateY() - deltaY);
    }

    private void mettreAJourPositionBarreDeVieJoueur(int deltaX, int deltaY) {
        this.map.getJoueur().getVueBarreDeVie().setTranslateX(map.getJoueur().getVueBarreDeVie().getTranslateX() + deltaX);
        this.map.getJoueur().getVueBarreDeVie().setTranslateY(map.getJoueur().getVueBarreDeVie().getTranslateY() + deltaY);
    }

    private void actualiserEnnemi() {
        for (Ennemi ennemi : map.getListeEnnemis()) {
            ajouterBarreDeVieEnnemiAuPane(ennemi);
        }
    }

    private void ajouterBarreDeVieEnnemiAuPane(Ennemi ennemi) {
        if (!paneEntites.getChildren().contains(ennemi.getVueBarreDeVie())) {
            paneEntites.getChildren().add(ennemi.getVueBarreDeVie());
        }
    }



}