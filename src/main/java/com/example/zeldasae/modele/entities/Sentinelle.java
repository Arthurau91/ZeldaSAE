package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;

public class Sentinelle extends Ennemi{

    public Sentinelle(int x, int y, int width, int height, int column, int rows, BFS bfs) {
        super(x, y, width, height, column, rows, bfs);
        super.setVitesse(0);
    }
}
