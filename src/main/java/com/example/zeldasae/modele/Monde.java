package com.example.zeldasae.modele;

import java.util.ArrayList;
import java.util.Arrays;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    private ArrayList<Ennemi> listeEnnemis;


    /**
     * Constructeur de la classe Monde
     */

    public Monde(Joueur joueur) {

        this.joueur = joueur;
        this.terrain = new Terrain();
        this.listeEnnemis = new ArrayList<>();
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void addEnnemi(Ennemi ennemi) {
        this.listeEnnemis.add(ennemi);
    }

    public void deplacementEnnemi(){
        for (Ennemi ennemi : this.listeEnnemis) {
            ennemi.deplacement(this);
        }
    }
}
