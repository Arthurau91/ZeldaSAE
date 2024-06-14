package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueJoueur;
import com.example.zeldasae.modele.entities.Ennemi;
import com.example.zeldasae.modele.Monde;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class ObservateurMouvement implements ChangeListener<Number> {

    private Monde map;
    private TilePane mapPane;
    private  Pane paneEntites;
    private VueJoueur vueJoueur;

    public ObservateurMouvement(Monde map, TilePane mapPane, Pane paneEntites, VueJoueur vueJoueur) {
        this.map = map;
        this.mapPane = mapPane;
        this.paneEntites = paneEntites;
        this.vueJoueur = vueJoueur;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        this.map.getBfs().lanceAlgo(map, mapPane.getPrefColumns(), mapPane.getPrefRows());
        if (observable == this.map.getJoueur().xProperty()) {
            if (oldValue.intValue() > 600 && newValue.intValue() > 600 &&
                    oldValue.intValue() <= 610 + (mapPane.getPrefColumns()*mapPane.getPrefTileWidth() - 1200) && newValue.intValue() <= 610 + (mapPane.getPrefColumns()*mapPane.getPrefTileWidth() - 1200)) {
                int deltaX = newValue.intValue() - oldValue.intValue();
                miseAJourDeplacement(deltaX, 0);
            }
        } else {
            if (oldValue.intValue() > 500 && newValue.intValue() > 500 &&
                    oldValue.intValue() <= 510 + (mapPane.getPrefRows()*mapPane.getPrefTileHeight() - 1000) && newValue.intValue() <= 510 + (mapPane.getPrefRows()*mapPane.getPrefTileHeight() - 1000)) {
                int deltaY = newValue.intValue() - oldValue.intValue();
                miseAJourDeplacement(0, deltaY);
            }
        }

    }


    private void miseAJourDeplacement(int deltaX, int deltaY) {

        if (deltaX != 0 || deltaY != 0) {
            scrollMap(deltaX, deltaY);
            vueJoueur.getVueBarreDeVie().mettreAJourPositionBarreDeVieJoueur(deltaX, deltaY, vueJoueur.getVueBarreDeVie());
        }

    }

    private void scrollMap(int deltaX, int deltaY) {
        this.mapPane.setTranslateX(mapPane.getTranslateX() - deltaX);
        this.mapPane.setTranslateY(mapPane.getTranslateY() - deltaY);
        this.paneEntites.setTranslateX(paneEntites.getTranslateX() - deltaX);
        this.paneEntites.setTranslateY(paneEntites.getTranslateY() - deltaY);
    }

}