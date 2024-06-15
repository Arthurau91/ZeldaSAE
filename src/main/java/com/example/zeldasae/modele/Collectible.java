package com.example.zeldasae.modele;

import com.example.zeldasae.modele.entities.Joueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Collectible extends Item{

    public static int compteur = 0;
    private IntegerProperty quantiteProperty;
    private int quantite_max;
    private String type;     //ici, type = "coeur" ou "fleche", c'est le type d'item ramassable
    private HitBox hitBox;
    private boolean utilisable;

    public Collectible(int quantite, int quantite_max, String type, int posSlotItems, int large, int haut, int x, int y, boolean isUtilisable) {
        super(type + compteur, posSlotItems);
        this.quantiteProperty = new SimpleIntegerProperty(quantite);
        this.quantite_max = quantite_max;
        this.type = type;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        this.utilisable = isUtilisable;
        compteur++;
    }

    public int getQuantite() {
        return this.quantiteProperty.getValue();
    }

    public void ajouter(int n) {
        if(quantiteProperty.getValue() + n <= quantite_max)
        this.quantiteProperty.set(this.quantiteProperty.getValue() + n);
    }

    public void retirer(int n) {
        this.quantiteProperty.set(this.quantiteProperty.getValue() - n);
    }

    public IntegerProperty quantiteProperty() {
        return quantiteProperty;
    }

    public String getType() {
        return type;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public boolean isUtilisable() {
        return utilisable;
    }

    public abstract void utiliserItem(Joueur j);
}
