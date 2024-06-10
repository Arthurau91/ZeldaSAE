package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;

public class Pursuer extends Ennemi {
    public Pursuer(int x, int y, int width, int height, int column, int rows, BFS bfs) {
        super(x, y, width, height, column, rows, bfs);
    }
}
