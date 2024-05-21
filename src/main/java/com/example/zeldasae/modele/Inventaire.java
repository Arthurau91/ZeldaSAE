package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {

    private ObservableList<Item> listeItems;
    private Armure armureActuelle;
    private Arme armeActuelle;

    public Inventaire() {
        listeItems = FXCollections.observableArrayList();
    }

    public void ajouterItem(Item i) {
        this.listeItems.add(i);
    }

    public void changerArme(Arme a) {
        this.armeActuelle = a;
    }

    public void changerArmure(Armure a) {
        this.armureActuelle = a;
    }

    public ObservableList<Item> getListeItems() {
        return this.listeItems;
    }


}
