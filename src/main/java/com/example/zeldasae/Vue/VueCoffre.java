package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class VueCoffre extends VueInterface{

    private ListView<Item> listView;
    private Coffre coffre;

    public VueCoffre(Pane boxCoffre, Joueur joueur, Coffre coffre) {
        super(boxCoffre, joueur);
        this.coffre = coffre;
        listView = new ListView<>(coffre.getListeItem());
        boxCoffre.getChildren().add(listView);
    }

}