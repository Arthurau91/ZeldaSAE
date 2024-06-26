package com.example.zeldasae.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

public abstract class Projectile {

    private String nom;
    public static int compteur = 0;
    private int degats;
    private int vitesse;
    private HitBox hitBox;
    private String direction;
    private boolean obstacleTouche;
    private boolean retireEnnemiTouche;
    private String type;

    public Projectile(int degats, int vitesse, int large, int haut, KeyEvent keyEvent, String type, boolean retireEnnemiTouche) {
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.vitesse = vitesse;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        this.direction = keyEvent.getCode().toString();
        this.obstacleTouche = false;
        this.type = type;
        this.retireEnnemiTouche = retireEnnemiTouche;
        compteur++;
    }

    public Projectile(int degats, int vitesse, int large, int haut, String type) {
        this.nom = "Proj" + compteur;
        this.degats = degats;
        this.vitesse = vitesse;
        this.hitBox = new HitBox(large, haut, new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        this.obstacleTouche = false;
        this.type = type;
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
    public boolean isRetireEnnemiTouche() {
        return retireEnnemiTouche;
    }
    public String getType() {
        return type;
    }

    public void setPosMap(int x, int y, String keyEvent) {
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

    public void seDeplace(Monde map) {
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

    public void inverserDirection() {
        switch (direction) {
            case "LEFT":
                this.direction = "RIGHT";
                break;
            case "RIGHT":
                this.direction = "LEFT";
                break;
            case "UP":
                this.direction = "DOWN";
                break;
            case "DOWN":
                this.direction = "UP";
                break;
        }
    }

    public boolean aRetirer(Terrain terrain) {
        return isObstacleTouche() || !dansMap(terrain);
    }
}