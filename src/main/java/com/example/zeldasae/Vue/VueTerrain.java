package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Monde;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VueTerrain {

    private Monde map;
    private TilePane mapPane;

    public VueTerrain(Monde map, TilePane mapPane) {
        this.map = map;
        this.mapPane = mapPane;
        afficherMap();
    }


    public void afficherMap() {

        ArrayList<Integer> m = loadMap("src/main/resources/com/example/zeldasae/assets/map.json");
        this.map.setMap(m);

        for (int x = 0 ; x < m.size() ; x++) {
            ImageView imageView = new ImageView();
            switch (m.get(x)) {
                case 232:
                    Image image = new Image("file:src/main/resources/com/example/zeldasae/assets/grass.jpg");
                    imageView.setImage(image);
                    break;
            }

            this.mapPane.getChildren().add(imageView);

        }

    }

    public ArrayList<Integer> loadMap(String filename) {
        BufferedReader lecteurAvecBuffer = null;
        ArrayList<Integer> elementsMap = new ArrayList<>();

        try {
            lecteurAvecBuffer = new BufferedReader(new FileReader(filename));
            List<String> elements = new ArrayList<>();

            for (int i = 0; i < 6; i++) {
                lecteurAvecBuffer.readLine();
            }

            String ligne;
            int cmpt = 0;

            while ((ligne = lecteurAvecBuffer.readLine()) != null && cmpt < 40) {

                String[] elementsLigne = ligne.split(",");

                for (String element : elementsLigne) {
                    elements.add(element.trim());
                }
                cmpt++;
            }

            String[] tableauElements = elements.toArray(new String[0]);

            for (String element : tableauElements) {
//                System.out.println(element);
                elementsMap.add(Integer.parseInt(element));
            }

        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture : Fichier non trouvé");
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } finally {
            if (lecteurAvecBuffer != null) {
                try {
                    lecteurAvecBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return elementsMap;


    }

}
