package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Monde;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class VueTerrain {

    private Monde map;
    private TilePane mapPane;

    public VueTerrain(Monde map, TilePane mapPane) {
        this.map = map;
        this.mapPane = mapPane;
    }


    public void afficherMap() {

        ArrayList<Integer> m = this.map.getMap();

        for (int x = 0 ; x < m.size() ; x++) {
            ImageView imageView = new ImageView();
            switch (m.get(x)) {
                case 0:
                    Image image = new Image("file:src/main/resources/com/example/zeldasae/img/grass.jpg");
                    imageView.setImage(image);
                    break;
            }

            this.mapPane.getChildren().add(imageView);

        }

    }


}
