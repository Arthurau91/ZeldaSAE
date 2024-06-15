package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;

public class Sentinelle extends Ennemi{

    public Sentinelle(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 5, bfs);
        super.setVitesse(0);
    }
}
