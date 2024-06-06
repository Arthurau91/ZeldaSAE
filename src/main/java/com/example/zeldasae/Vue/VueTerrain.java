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
    private Image[] tiles;
    private ArrayList<Integer> m2;

    public VueTerrain(Monde map, TilePane mapPane ,ArrayList<Integer> m1, ArrayList<Integer> m2) {
        this.map = map;
        this.mapPane = mapPane;
        this.m2 = m2;

        Image tileset = new Image("file:src/main/resources/com/example/zeldasae/assets/tiles.png");

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
        afficherMap();
    }

    public void afficherMap() {
        ArrayList<Integer> m1 = map.getTerrain().getMap();
        this.map.setMap(m1);

        for (int i = 0; i < m1.size(); i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = null;
            imageView.setId(""+i);

            int tileIndex = m1.get(i);
            int tileIndex2 = m2.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length) // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex - 1]);
            if (tileIndex2 >= 1 && tileIndex2 <= tiles.length) {
                imageView2 = new ImageView();
                imageView2.setId(""+i);
                imageView2.setImage(tiles[tileIndex2 - 1]);
            }
            if (tileIndex2 != 0)
                this.mapPane.getChildren().add(new StackPane(imageView2, imageView));
            else this.mapPane.getChildren().add(new StackPane(imageView));
        }
    }

    public void deplaceBloc(){

        ArrayList<Integer> m1 = map.getTerrain().getMap();
        int coo = (this.map.getJoueur().getX()/30) + ((this.map.getJoueur().getY()/30)*this.mapPane.getPrefRows());
        int coobloc = 0;
        int coovide = 0;
        String directionImage = this.map.getJoueur().getVueEntite().getDirectionImage();
        switch (directionImage){
            case "up" : coobloc = coo - 100;
                coovide = coobloc - 100;
                        break;
            case "down" : coobloc = coo + 100;
                coovide = coobloc + 100;
                break;
            case "right" : coobloc = coo + 1;
                coovide = coobloc + 1;
                break;
            case "left" : coobloc = coo - 1;
                coovide = coobloc - 1;
                break;
        }
        if (this.map.getTerrain().poussable(coobloc)){
            if (this.map.getTerrain().vide(coovide)){
                StackPane vide = new StackPane(new ImageView(tiles[m2.get(coovide)-1]));
                StackPane bloc = new StackPane(new ImageView(tiles[m2.get(coobloc)-1]), new ImageView(tiles[m1.get(coobloc)-1]));
                mapPane.getChildren().set(coobloc, vide);
                mapPane.getChildren().set(coovide, bloc);
                this.map.getTerrain().setCoo(coovide, m1.get(coobloc));
                this.map.getTerrain().setCoo(coobloc, 0);
            }
        }
    }
}