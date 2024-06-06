package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class Coffre {

    private ObservableList<Item> listeItem;
    private boolean estOuvert;
    private int x;
    private int y;
    private int id;

    public Coffre(int x, int y, int id) {
        this.listeItem = FXCollections.observableArrayList();
        this.estOuvert = false;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public List<Item> getListeItem() {
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


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "" + getId();
    }
}