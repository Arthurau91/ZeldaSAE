package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Coffre {

    private ObservableList<Item> listeItem;
    private boolean estOuvert;
    private int x;
    private int y;

    public Coffre(int x, int y) {
        this.listeItem = FXCollections.observableArrayList();
        this.estOuvert = false;
        this.x = x;
        this.y = y;
    }

    public ObservableList<Item> getListeItem() {
        return listeItem;
    }

    public void ajouterItem(Item item) {
        if (listeItem.size() < 8) {
            listeItem.add(item);
        }
    }

    public void retirerItem(Item item) {
        listeItem.remove(item);
    }

    public void setEstOuvert(boolean estOuvert) {
        this.estOuvert = estOuvert;
    }

    public boolean isEstOuvert() {
        return estOuvert;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}