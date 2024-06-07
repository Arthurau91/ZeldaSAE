package com.example.zeldasae.modele;

public abstract class Item {

    private String nom;
    private int posSlotItems;
    private int quantite;

    public Item(String nom, int posSlotItems){
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
