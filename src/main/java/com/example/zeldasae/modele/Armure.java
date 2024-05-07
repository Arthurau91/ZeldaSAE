package com.example.zeldasae.modele;

public class Armure extends Item {

    private int resistance;

    public Armure(int quantite, int quantite_max, int resistance, String nom) {
        super(quantite, quantite_max, nom);
        this.resistance = resistance;
    }
}
