package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Coffre {

    private ObservableList<Item> listeItem;

    public Coffre() {
        this.listeItem = FXCollections.observableArrayList();
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
}