package com.example.zeldasae.Vue.VueArmes;

import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.scene.layout.Pane;

public class VueEpee extends VueArme {

    public VueEpee(Joueur joueur, Pane paneEntites, Monde map, Pane mapPane, VueTerrain vueTerrain) {
        super(joueur, paneEntites, map, mapPane, vueTerrain);
    }

}
