package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;

public class ObservateurItems implements ListChangeListener<Item> {

    private VueInventaire vueInv;

    public ObservateurItems(VueInventaire vueInv) {
        this.vueInv = vueInv;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item i : change.getAddedSubList()) {
                this.vueInv.ajouterItemVue(i);
            }
        }

    }

}