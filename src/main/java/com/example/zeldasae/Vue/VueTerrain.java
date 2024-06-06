package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Monde;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class VueTerrain {

    private Monde map;
    private TilePane mapPane;
    private Image tileset;
    private Image[] tiles;

    public VueTerrain(Monde map, TilePane mapPane ,ArrayList<Integer> m1, ArrayList<Integer> m2) {
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
        afficherMap(m1, m2);
    }

    public void afficherMap(ArrayList<Integer> m1, ArrayList<Integer> m2) {
        this.map.setMap(m1);

        for (int i = 0; i < m1.size(); i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = null;
            imageView.setId(""+i);

            int tileIndex = m1.get(i);
//            int tileIndex2 = m2.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length) // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex - 1]);
//            if (tileIndex2 >= 1 && tileIndex2 <= tiles.length) {
//                imageView2 = new ImageView();
//                imageView2.setId(""+i);
//                imageView2.setImage(tiles[tileIndex2 - 1]);
//            }
            if (imageView2 != null)
                this.mapPane.getChildren().add(new StackPane(imageView, imageView2));
            else this.mapPane.getChildren().add(new StackPane(imageView));
        }
    }
}