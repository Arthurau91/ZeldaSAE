package com.example.zeldasae.modele;

public class Collectible extends Item{

    private int quantite;
    private int quantite_max;

    public Collectible(int quantite, int quantite_max, String nom, int posSlotItems) {
        super(nom, posSlotItems);
        this.quantite = quantite;
        this.quantite_max = quantite_max;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void ajouter(int n) {
        this.quantite = this.quantite + n;
    }

}
