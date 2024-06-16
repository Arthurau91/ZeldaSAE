package com.example.zeldasae.modele.armures;

import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.entities.Joueur;

public abstract class Armure extends Item {

    private int resistance;

    public Armure(int resistance, String nom, int posSlotItems) {
        super(nom, posSlotItems);
        this.resistance = resistance;
    }

    @Override
    public void utiliserItem(Joueur j) {
        j.getInv().changerArmure(this);
    }

    public int getResistance() {
        return resistance;
    }
}
