package com.example.zeldasae.modele;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import org.controlsfx.tools.Utils;

public class Monde {

    private int[][] map;

    public Monde() {
        this.map = new int[30][30];
    }


    public int[][] getMap() {
        return map;
    }
}
