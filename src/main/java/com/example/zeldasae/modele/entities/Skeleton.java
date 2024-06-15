package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;

public class Skeleton extends Ennemi {

    public Skeleton(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 7, bfs);
    }


}