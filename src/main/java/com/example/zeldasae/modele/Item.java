package com.example.zeldasae.modele;

import com.example.zeldasae.modele.entities.Joueur;

public abstract class Item {

    private String nom;
    private int posSlotItems;

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

    public abstract void utiliserItem(Joueur j);

}
