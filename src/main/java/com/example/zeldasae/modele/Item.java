package com.example.zeldasae.modele;

public abstract class Item {

    private int quantite;
    private int quantite_max;
    private String nom;
    private int posSlotItems;

    public Item(int quantite, int quantite_max, String nom, int posSlotItems){
        this.quantite = quantite;
        this.quantite_max = quantite_max;
        this.nom = nom;
        this.posSlotItems = posSlotItems;
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
}
