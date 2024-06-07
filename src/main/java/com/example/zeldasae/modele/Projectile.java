package com.example.zeldasae.modele;

import com.example.zeldasae.controller.LoadJSON;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Projectile {

    private String nom;
    public static int compteur = 0;
    private int degats;
    private int vitesse;
    private HitBox hitBox;
    private String direction;
    private boolean obstacleTouche;

    public Projectile(int degats, int vitesse, int large, int haut, KeyEvent keyEvent) {
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.vitesse = vitesse;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        this.direction = keyEvent.getCode().toString();
        this.obstacleTouche = false;
        compteur++;
    }

    public HitBox getHitBox() {
        return this.hitBox;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isObstacleTouche() {
        return this.obstacleTouche;
    }

    public void deplacerProjectile(ArrayList<Ennemi> ennemis) {
        switch (this.direction) {
            case "UP":
                getHitBox().setY(getHitBox().getY() - this.vitesse);
                break;
            case "DOWN":
                getHitBox().setY(getHitBox().getY() + this.vitesse);
                break;
            case "LEFT":
                getHitBox().setX(getHitBox().getX() - this.vitesse);
                break;
            case "RIGHT":
                getHitBox().setX(getHitBox().getX() + this.vitesse);
                break;
        }
        checkCoupTouche(ennemis);
    }

    public void checkCoupTouche(ArrayList<Ennemi> ennemis) {
        for (Ennemi e : ennemis) {
            if (e.getHitBox().estDedansHitbox(this.hitBox) && !this.obstacleTouche) {
                this.obstacleTouche = true;
                e.perdreVie(this.degats);
                System.out.println("Pv de l'ennemi : " + e.getPv());
            }
        }
    }

    public boolean dansMap(Terrain terrain) {
        return terrain.vide((this.getHitBox().getX() / 30) + (this.getHitBox().getY() / 30 * terrain.getRows()));
    }
}