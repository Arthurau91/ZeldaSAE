package com.example.zeldasae.modele;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

//A REMETTRE ABSTRACT
public class Arme extends Item{

    private int degats;
    private HitBox hitBox;

    //POUR PLUS TARD : DONNER UNE HITBOX SPECIALE A LA CREATION D'UNE ARME AU LIEU DE METTRE int large, int haut, int x, int y
    public Arme(int quantite, int quantite_max, String nom, int degats, int posSlotItems, int large, int haut, int x, int y) {
        super(1, 1, nom, posSlotItems);
        this.degats = degats;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    public int getX() {
        return this.getHitBox().getX();
    }

    public int getY() {
        return this.getHitBox().getY();
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setPosMap(int x, int y) {
        this.hitBox.setX(x+30);
        this.hitBox.setY(y);
    }

    public void checkCoupTouche(ArrayList<Ennemi> ennemis) {
       for (Ennemi e : ennemis) {
           if (e.getHitBox().estDedansHitbox(this.hitBox)) {
               e.perdreVie(this.degats);
               System.out.println("Pv de l'ennemi : " + e.getPv());
           }
       }
    }

}
