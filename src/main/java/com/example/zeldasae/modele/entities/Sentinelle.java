package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;

public class Sentinelle extends Ennemi{

    public Sentinelle(int x, int y, int width, int height, int column, int rows, BFS bfs, Monde monde) {
        super(x, y, width, height, column, rows, bfs, monde);
        super.setVitesse(0);
    }
}
