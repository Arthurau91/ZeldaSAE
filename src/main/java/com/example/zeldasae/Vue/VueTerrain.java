package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Monde;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public VueTerrain(Monde map, TilePane mapPane) {
        this.map = map;
        this.mapPane = mapPane;
        afficherMap();
    }

    public void afficherMap() {
        ArrayList<Integer> m = loadMap("src/main/resources/com/example/zeldasae/assets/map2.json");
        this.map.setMap(m);

        for (int x = 0; x < m.size(); x++) {
            ImageView imageView = new ImageView();
            Image image;
            switch (m.get(x)) {
                case 100:
                    image = new Image("file:src/main/resources/com/example/zeldasae/assets/mur2.png");
                    imageView.setImage(image);
                    break;
                case 26:
                    image = new Image("file:src/main/resources/com/example/zeldasae/assets/sol.png");
                    imageView.setImage(image);
                    break;
                case 1:
                    image = new Image("file:src/main/resources/com/example/zeldasae/assets/grass.jpg");
                    imageView.setImage(image);
                    break;
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