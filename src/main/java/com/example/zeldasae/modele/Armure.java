package com.example.zeldasae.modele;

public class Armure extends Item {

    private int resistance;

    public Armure(int resistance, String nom, int posSlotItems) {
        super(nom, posSlotItems);
        this.resistance = resistance;
    }

    @Override
    public void utiliserItem(Joueur j) {
        j.getInv().changerArmure(this);
    }
}
