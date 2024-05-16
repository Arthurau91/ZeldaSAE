package com.example.zeldasae.modele;

import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ennemi extends Entite{

    public Ennemi(int x, int y, String id, int width, int height, int column) {
        super(x, y, id, width, height, column);
    }

    /**
     * Méthode qui gère le déplacement d'un Ennemi sur la map en se reposant sur la méthode déplacement() d'Entite
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          déplacement() d'Entite
     */
    public void deplacement(Monde m) {
        ArrayList<Character> directions = new ArrayList<>(Arrays.asList('z','q','s','d'));
        char direction;
        Random random = new Random();
        do {
            direction = directions.get(random.nextInt(4));
        }while (!super.deplacement(direction,m));
    }
}
