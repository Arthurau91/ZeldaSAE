package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Inventaire;
import com.example.zeldasae.modele.Item;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class ObservateurItems implements ListChangeListener<Item> {

    private VueInventaire vueInv;
    private VueCoffre coffre;
    private Pane map;

    public ObservateurItems(VueInventaire vueInv, Pane map, VueCoffre coffre) {
        this.vueInv = vueInv;
        this.map = map;
        this.coffre = coffre;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item i : change.getAddedSubList()) {
                if (this.vueInv != null)
                    this.vueInv.ajouterItem(i);

            }

            for (Item i : change.getRemoved()) {
                if (this.vueInv == null) {
                    this.coffre.retirerItem(i);
                }


            }

        }

    }


}


