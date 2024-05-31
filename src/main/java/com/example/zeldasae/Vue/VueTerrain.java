package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Monde;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.TilePane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VueTerrain {

    private Monde map;
    private TilePane mapPane;
    private Image tileset;
    private Image[] tiles;

    public VueTerrain(Monde map, TilePane mapPane) {
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
                tiles[y * colonne + x] = new WritableImage(tileset.getPixelReader(), x * tileWidth, y * tileHeight, tileWidth, tileHeight);
            }
        }
        afficherMap();
    }

    public void afficherMap() {
        ArrayList<Integer> m = loadMap("src/main/resources/com/example/zeldasae/assets/map.json");
        this.map.setMap(m);

        for (int i = 0; i < m.size(); i++) {
            ImageView imageView = new ImageView();
            int tileIndex = m.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length) { // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex - 1]);
            }
            this.mapPane.getChildren().add(imageView);
        }
    }

    public ArrayList<Integer> loadMap(String filename) {
        ArrayList<Integer> elementsMap = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray layersArray = (JSONArray) jsonObject.get("layers");
            JSONObject firstLayer = (JSONObject) layersArray.get(0);
            JSONArray dataArray = (JSONArray) firstLayer.get("data");

            for (Object data : dataArray) {
                elementsMap.add(Math.toIntExact((Long) data));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return elementsMap;
    }



}