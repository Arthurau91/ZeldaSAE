package com.example.zeldasae.modele;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import org.controlsfx.tools.Utils;

public class Monde {

    private int[][] map;
    private Joueur joueur;

    public Monde(Joueur j) {
        this.map = new int[30][30];
        this.joueur = j;
    }

    public int[][] getMap() {
        return map;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }


}
