package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueBoss;
import com.example.zeldasae.Vue.VueEntite;
import com.example.zeldasae.Vue.VueSentinelle;
import com.example.zeldasae.Vue.VueSkeleton;
import com.example.zeldasae.modele.entities.Boss;
import com.example.zeldasae.modele.entities.Ennemi;
import com.example.zeldasae.modele.entities.Sentinelle;
import com.example.zeldasae.modele.entities.Skeleton;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurEnnemis implements ListChangeListener<Ennemi> {

    private Pane paneEntites;
    private IntegerProperty temps;

    public ObservateurEnnemis(Pane paneEntites, IntegerProperty temps){
        this.paneEntites = paneEntites;
        this.temps = temps;
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
                ennemi.pvProperty().addListener(new ObservateurVie(ennemi, vueEntite));
            }
        }
    }
}
