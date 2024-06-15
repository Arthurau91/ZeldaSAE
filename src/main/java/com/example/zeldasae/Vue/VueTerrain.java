package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Collectible;
import com.example.zeldasae.modele.Monde;

import com.example.zeldasae.modele.collectibles.Fruit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.Random;

public class VueTerrain {

    private Monde map;
    private TilePane mapPane;
    private Image[] tiles;
    private Image[] fogs;
    private ArrayList<Integer> m2;

    public VueTerrain(Monde map, TilePane mapPane ,ArrayList<Integer> m1, ArrayList<Integer> m2) {
        this.map = map;
        this.mapPane = mapPane;
        this.m2 = m2;
        map.setMap(m1);
        map.getTerrain().setColumns(m1.size()/map.getTerrain().getRows());

        Image tileset = new Image("file:src/main/resources/com/example/zeldasae/assets/tiles.png", 1680, 690, false, false);
        Image fog = new Image("file:src/main/resources/com/example/zeldasae/assets/fog.png");

        int tileWidth = 30;
        int tileHeight = 30;
        int colonne = (int) (tileset.getWidth() / tileWidth);
        int ligne = (int) (tileset.getHeight() / tileHeight);
        tiles = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                tiles[y * colonne + x] = new WritableImage(tileset.getPixelReader(), (x * tileWidth), (y * tileHeight), tileWidth, tileHeight);
            }
        }

        colonne = (int) (fog.getWidth() / tileWidth);
        ligne = (int) (fog.getHeight() / tileHeight);
        fogs = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                fogs[y * colonne + x] = new WritableImage(fog.getPixelReader(), (x * tileWidth), (y * tileHeight), tileWidth, tileHeight);
            }
        }
        afficherMap();
    }

    public void afficherMap() {
        ArrayList<Integer> m1 = map.getTerrain().getMap();
        this.map.setMap(m1);
        Random random = new Random();

        for (int i = 0; i < m1.size(); i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = null;
            StackPane stackPane;

            int tileIndex = m1.get(i);
            int tileIndex2 = m2.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length)
                imageView.setImage(tiles[tileIndex - 1]);
            if (tileIndex2 >= 1 && tileIndex2 <= tiles.length) {
                imageView2 = new ImageView();
                imageView2.setImage(tiles[tileIndex2 - 1]);
            }
            if (tileIndex2 != 0) {
                if (tileIndex == 1205){
                    stackPane = new StackPane(imageView2, imageView, new ImageView(fogs[random.nextInt(fogs.length)]));
                }
                else stackPane = new StackPane(imageView2, imageView);
            }
            else stackPane = new StackPane(imageView);
            stackPane.setId(""+i);
            this.mapPane.getChildren().add(stackPane);
        }
    }

    public void deplaceBloc(){
        ArrayList<Integer> m1 = map.getTerrain().getMap();
        int[] coos = map.cooBloc(this.map.getJoueur().getX(), this.map.getJoueur().getY());
        int coobloc = coos[0];
        int coovide = coos[1];
        if (this.map.getTerrain().poussable(coobloc)){
            if (this.map.getTerrain().vide(coovide) && ((this.map.getJoueur().getX()/30)-2 >= 0 && (this.map.getJoueur().getX()/30)+2 < this.mapPane.getPrefColumns())){
                StackPane vide = new StackPane(new ImageView(tiles[m2.get(coobloc)-1]));
                StackPane bloc = new StackPane(new ImageView(tiles[m2.get(coovide)-1]), new ImageView(tiles[m1.get(coobloc)-1]));
                mapPane.getChildren().set(coobloc, vide);
                mapPane.getChildren().set(coovide, bloc);
                this.map.getTerrain().setCoo(coovide, m1.get(coobloc));
                this.map.getTerrain().setCoo(coobloc, 0);
            }
        }
    }

    public void detruitBloc(){
        int[] coos = map.cooBloc(this.map.getJoueur().getX(), this.map.getJoueur().getY());
        int cooBloc = coos[0];
        int cooBloc2 = coos[1];
        if (this.map.getTerrain().destructible(cooBloc)){
            changeBloc(cooBloc);
        }
        else if (this.map.getTerrain().destructible(cooBloc2)){
            changeBloc(cooBloc2);
        }
    }

    private void changeBloc(int cooBloc) {
        if (this.map.getTerrain().isBuisson(cooBloc)) {
            Collectible fruit = new Fruit(0, 10, 30, 30, (cooBloc % 100) * 30, (cooBloc / 100) * 30, this.map.getJoueur());
            this.map.ajouterCollectible(fruit);
        }
        StackPane vide = new StackPane(new ImageView(tiles[m2.get(cooBloc)-1]));
        mapPane.getChildren().set(cooBloc, vide);
        this.map.getTerrain().setCoo(cooBloc, 0);
    }
}