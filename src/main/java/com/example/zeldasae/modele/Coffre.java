package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Coffre {

    private ObservableList<Item> listeItem;
    private boolean estOuvert;

    public Coffre() {
        this.listeItem = FXCollections.observableArrayList();
        this.estOuvert = false;
    }

    public ObservableList<Item> getListeItem() {
        return listeItem;
    }

    public void ajouterItem(Item item) {
        listeItem.add(item);
    }

    public void retirerItem(Item item) {
        listeItem.remove(item);
    }

    public void setEstOuvert(boolean b) {
        this.estOuvert = b;
    }

    public boolean getEstOuvert() {
        return this.estOuvert;
    }


}