package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurItems implements ListChangeListener<Item> {

    private VueInventaire vueInv;
    private Pane map;
    public ObservateurItems(VueInventaire vueInv, Pane map) {
        this.vueInv = vueInv;
        this.map = map;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item i : change.getAddedSubList()) {
                this.vueInv.ajouterItem(i);
            }
//            for (Item i : change.getRemoved()) {
//                méthode pour retirer un item côté vue (si besoin)
//            }
        }
    }




}
