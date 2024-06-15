package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;

public class Sentinelle extends Ennemi{

    public Sentinelle(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, bfs);
        super.setVitesse(0);
    }
}
