package com.example.zeldasae.modele;

public abstract class Arme extends Item{

    private int degats;

    public Arme(int quantite, int quantite_max, String nom, int degats) {
        super(1, 1, nom);
        this.degats = degats;
    }
}
