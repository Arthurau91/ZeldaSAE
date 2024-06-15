package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Epee;

public class Skeleton extends Ennemi {

    public Skeleton(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, bfs);
    }


}