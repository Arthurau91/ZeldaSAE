package com.example.zeldasae.modele;

public class Joueur extends Entite{

    private Inventaire inv;

    public Joueur(int x, int y, int width, int height, int column, int rows) {
        super(x, y, "j1", width, height, column, rows);
        this.inv = new Inventaire();
    }

    public Inventaire getInv() {
        return this.inv;
    }
}
