package com.example.zeldasae.modele;

import javafx.beans.property.SimpleIntegerProperty;

public class Collectible extends Item{

    public static int compteur = 0;
    private int quantite;
    private int quantite_max;
    private String type;     //ici, type = "coeur" ou "fleche", c'est le type d'item ramassable
    private HitBox hitBox;


    public Collectible(int quantite, int quantite_max, String type, int posSlotItems, int large, int haut, int x, int y) {
        super(type + compteur, posSlotItems);
        this.quantite = quantite;
        this.quantite_max = quantite_max;
        this.type = type;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        compteur++;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void ajouter(int n) {
        this.quantite = this.quantite + n;
    }

    public String getType() {
        return type;
    }

    public HitBox getHitBox() {
        return hitBox;
    }
}
