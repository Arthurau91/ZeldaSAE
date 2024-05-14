package com.example.zeldasae.modele;

import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ennemi extends Entite{

    public Ennemi(int x, int y, String id) {
        super(x, y, id);
    }

    public boolean deplacement(TilePane t, Monde m) {
        ArrayList<Character> directions = new ArrayList<>(Arrays.asList('z','q','s','d'));
        char direction;
        Random random = new Random();
        do {
            direction = directions.get(random.nextInt(4));
        }while (!super.deplacement(direction,t,m));
        return true;
    }
}
