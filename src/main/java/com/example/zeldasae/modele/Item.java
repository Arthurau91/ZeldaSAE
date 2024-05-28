package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Item {

    private IntegerProperty quantite;
    private int quantite_max;
    private String nom;
    private int posSlotItems;

    public Item(int quantite, int quantite_max, String nom, int posSlotItems){
        this.quantite = new SimpleIntegerProperty(quantite);
        this.quantite_max = quantite_max;
        this.nom = nom;
        this.posSlotItems = posSlotItems;
    }

    public int getQuantite() {
        return this.quantite.getValue();
    }

    public void setQuantite(int n) {
        quantite.setValue(n);
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public int getPosSlotItems() {
        return this.posSlotItems;
    }

    public String getNom() {
        return this.nom;
    }

    public void setPosSlotItems(int posSlotItems) {
        this.posSlotItems = posSlotItems;
    }

    public void ajouter(int qteAjout) {
        if (this.quantite.getValue() + qteAjout <= this.quantite_max) {
            this.quantite.setValue(this.quantite.get() + qteAjout);
        }
    }

}
