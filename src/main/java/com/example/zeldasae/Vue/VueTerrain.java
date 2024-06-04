package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Monde;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class VueTerrain {

    private Monde map;
    private TilePane mapPane;
    private Image tileset;
    private Image[] tiles;

    public VueTerrain(Monde map, TilePane mapPane ,ArrayList<Integer> m) {
        this.map = map;
        this.mapPane = mapPane;

        this.tileset = new Image("file:src/main/resources/com/example/zeldasae/assets/tiles.png");

        int tileWidth = 32;
        int tileHeight = 32;
        int colonne = (int) (tileset.getWidth() / tileWidth);
        int ligne = (int) (tileset.getHeight() / tileHeight);
        tiles = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                tiles[y * colonne + x] = new WritableImage(tileset.getPixelReader(), (x * tileWidth)+1, (y * tileHeight)+1, tileWidth-2, tileHeight-2);
            }
        }
        afficherMap(m);
    }

    public void afficherMap(ArrayList<Integer> m) {
        this.map.setMap(m);

        for (int i = 0; i < m.size(); i++) {
            ImageView imageView = new ImageView();
            imageView.setId(""+i);
            int tileIndex = m.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length) { // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex - 1]);
            }
            this.mapPane.getChildren().add(imageView);
        }
    }
}