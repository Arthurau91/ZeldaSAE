package com.example.zeldasae.modele;

import com.example.zeldasae.controller.LoadJSON;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public abstract class Projectile {

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

    public Projectile(int degats, int vitesse, int large, int haut) {
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.vitesse = vitesse;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        this.obstacleTouche = false;
        compteur++;
    }

    public int getDegats() {
        return degats;
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

    public void setObstacleTouche(boolean obstacleTouche) {
        this.obstacleTouche = obstacleTouche;
    }

    public void setPosMap(int x, int y, String keyEvent) {
        System.out.println(keyEvent);
        switch (keyEvent) {
            case "LEFT":
                this.hitBox.setX(x-this.hitBox.getLarge());
                this.hitBox.setY(y);
                break;
            case "RIGHT":
                this.hitBox.setX(x+30);
                this.hitBox.setY(y);
                break;
            case "UP":
                this.hitBox.pivote();
                this.hitBox.setX(x+10);
                this.hitBox.setY(y-this.hitBox.getHaut());
                break;
            case "DOWN":
                this.hitBox.pivote();
                this.hitBox.setX(x+10);
                this.hitBox.setY(y+30);
                break;
        }
    }

    public void deplacerProjectile(Monde map) {
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
        checkCoupTouche(map);
    }

    public abstract void checkCoupTouche(Monde map);

    public boolean dansMap(Terrain terrain) {
        return terrain.vide((this.getHitBox().getX() / 30) + (this.getHitBox().getY() / 30 * terrain.getRows()));
    }
}