package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public Item getItemParID(int posItem) {
        for (Item i : listeItems) {
            if (i.getPosSlotItems() == posItem) {
                return i;
            }
        }
        return null;
    }

    public Arme getArmeActuelle() {
        return this.armeActuelle;
    }

    public Armure getArmureActuelle() {
        return this.armureActuelle;
    }

}
