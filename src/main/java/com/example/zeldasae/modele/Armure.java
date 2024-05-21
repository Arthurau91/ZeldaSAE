package com.example.zeldasae.modele;

public class Armure extends Item {

    private int resistance;

    public Armure(int quantite, int quantite_max, int resistance, String nom, int posSlotItems) {
        super(quantite, quantite_max, nom, posSlotItems);
        this.resistance = resistance;
    }
}
