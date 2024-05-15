package com.example.zeldasae.modele;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Item> listeItems;
    private Armure armureActuelle;
    private Arme armeActuelle;

    public Inventaire() {
        listeItems = new ArrayList<>();
    }

    private void ajouterItem(Item i) {
        this.listeItems.add(i);
    }

    private void changerArme(Arme a) {
        this.armeActuelle = a;
    }

    private void changerArmure(Armure a) {
        this.armureActuelle = a;
    }

    

}
