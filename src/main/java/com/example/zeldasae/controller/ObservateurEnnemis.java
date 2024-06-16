package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.*;
import com.example.zeldasae.modele.entities.*;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurEnnemis implements ListChangeListener<Ennemi> {

    private Pane paneEntites;
    private IntegerProperty temps;
    private VueTerrain vueTerrain;

    public ObservateurEnnemis(Pane paneEntites, IntegerProperty temps, VueTerrain vueTerrain){
        this.paneEntites = paneEntites;
        this.temps = temps;
        this.vueTerrain = vueTerrain;
    }

    @Override
    public void onChanged(Change<? extends Ennemi> change) {
        while (change.next()){
            for (Ennemi ennemi : change.getAddedSubList()){
                VueEntite vueEntite = null;
                if (ennemi instanceof Boss)
                    vueEntite = new VueBoss((Boss) ennemi, paneEntites, temps);
                else if (ennemi instanceof Sentinelle)
                    vueEntite = new VueSentinelle((Sentinelle) ennemi, paneEntites, temps);
                else if (ennemi instanceof Skeleton)
                    vueEntite = new VueSkeleton((Skeleton) ennemi, paneEntites, temps);
                else if (ennemi instanceof Kami)
                    vueEntite = new VueKami((Kami) ennemi, paneEntites, temps, vueTerrain);
                ennemi.pvProperty().addListener(new ObservateurVie(ennemi, vueEntite));
            }
        }
    }
}
