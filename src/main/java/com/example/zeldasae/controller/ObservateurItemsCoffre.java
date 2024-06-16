package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;

public class ObservateurItemsCoffre implements ListChangeListener<Item> {

    private VueCoffre coffre;

    public ObservateurItemsCoffre(VueCoffre coffre) {

        this.coffre = coffre;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item i : change.getAddedSubList()) {
                this.coffre.ajouterItem(i);
            }
            for (Item i : change.getRemoved()) {
                this.coffre.retirerItem(i);
            }
        }

    }

}