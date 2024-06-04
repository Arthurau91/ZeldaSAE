package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Projectile {

    private String nom;
    public static int compteur = 0;
    private int degats;
    private int vitesse;
    private HitBox hitBox;

    public Projectile(int degats, int vitesse, int large, int haut) {
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.vitesse = vitesse;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        compteur++;
    }

    public HitBox getHitBox() {
        return this.hitBox;
    }

    public String getNom() {
        return this.nom;
    }

    public void deplacerProjectile(ArrayList<Ennemi> ennemis) {
        this.getHitBox().setX(this.getHitBox().getX() + 15);
        checkCoupTouche(ennemis);
    }

    public void checkCoupTouche(ArrayList<Ennemi> ennemis) {
        for (Ennemi e : ennemis) {
            if (e.getHitBox().estDedansHitbox(this.hitBox)) {
                e.perdreVie(this.degats);
                System.out.println("Pv de l'ennemi : " + e.getPv());
            }
        }
    }

    public boolean dansMap(double largeur) {
        if(this.hitBox.getX() < largeur - this.getHitBox().getLarge()) {
            return true;
        }
        return false;
    }
}
