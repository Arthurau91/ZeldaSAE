package com.example.zeldasae.modele;

import java.util.ArrayList;
import java.util.Arrays;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;


    /**
     * Constructeur de la classe Monde
     */

    public Monde(Joueur joueur) {

        this.joueur = joueur;
        this.terrain = new Terrain();
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }


}
