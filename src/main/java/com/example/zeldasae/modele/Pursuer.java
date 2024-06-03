package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;
import javafx.scene.layout.Pane;

public class Pursuer extends Ennemi{
    public Pursuer(int x, int y, int width, int height, int column, int rows, BFS bfs, Pane paneEntite) {
        super(x, y, width, height, column, rows, bfs, paneEntite);
    }
}
