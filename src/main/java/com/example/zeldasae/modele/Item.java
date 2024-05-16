package com.example.zeldasae.modele;

public abstract class Item {

    int quantite;
    int quantite_max;
    String nom;

    public Item(int quantite, int quantite_max, String nom){
        this.quantite = quantite;
        this.quantite_max = quantite_max;
        this.nom = nom;
    }


}
