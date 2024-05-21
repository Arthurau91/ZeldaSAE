package com.example.zeldasae.modele;

public abstract class Arme extends Item{

    private int degats;

    public Arme(int quantite, int quantite_max, String nom, int degats, int posSlotItems) {
        super(1, 1, nom, posSlotItems);
        this.degats = degats;
    }
}
