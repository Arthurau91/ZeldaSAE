package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.*;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.Map;

public class ClickHandlerCoffre implements EventHandler<MouseEvent>{

    private Pane boxInv;
    private Coffre coffre;
    private VueInventaire vueInventaire;

    public ClickHandlerCoffre(Pane boxInv, Coffre coffre, VueInventaire vueInventaire) {
        this.boxInv = boxInv;
        this.coffre = coffre;
        this.vueInventaire = vueInventaire;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView v) {
            this.vueInventaire.ajouterItemVue(coffre.getItemParID(Integer.parseInt(v.getId())));
            this.vueInventaire.getJoueur().getInv().ajouterItem(coffre.getItemParID(Integer.parseInt(v.getId())));
            this.coffre.retirerItem(coffre.getItemParID(Integer.parseInt(v.getId())));
        }

    }

}
