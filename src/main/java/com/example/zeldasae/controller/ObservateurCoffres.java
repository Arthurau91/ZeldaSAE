package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.Vue.VueCoffre;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurCoffres implements ListChangeListener<Coffre> {

    private Pane paneCoffres;
    private VueCoffre vueCoffre;

    public ObservateurCoffres(Pane paneCoffres, VueCoffre vueCoffre) {
        this.paneCoffres = paneCoffres;
        this.vueCoffre = vueCoffre;
    }

    @Override
    public void onChanged(Change<? extends Coffre> change) {
        while (change.next()) {
            for (Coffre c : change.getAddedSubList()) {
                VueCoffre vueCoffre = new VueCoffre(paneCoffres, this.vueCoffre.getJoueur(), c, this.vueCoffre.getVueInventaire());
                paneCoffres.getChildren().add(vueCoffre.getPaneInterface());
            }
        }
    }
}