package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Collectible;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueCollectible {

    private Pane paneEntites;
    private Monde map;

    public VueCollectible(Pane mapPane, Monde map) {
        this.paneEntites = mapPane;
        this.map = map;
    }

    public void creerCollectibleVue(Collectible c) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + c.getType() + ".png");
        ImageView imageView = new ImageView(img);
        imageView.setId(c.getNom());
        imageView.setTranslateX(c.getHitBox().getX());
        imageView.setTranslateY(c.getHitBox().getY());
        imageView.setFitWidth(c.getHitBox().getLarge());
        imageView.setFitHeight(c.getHitBox().getHaut());
        this.paneEntites.getChildren().add(imageView);
    }

    public void supprimerCollectibleVue(Collectible c) {
        paneEntites.getChildren().remove(paneEntites.lookup("#" + c.getNom()));
    }

    public void checkCollectiblesRamasses() {
        for(int i = 0; i < map.getListeCollectibles().size(); i++) {
            if (map.getListeCollectibles().get(i).getHitBox().estDedansHitbox(map.getJoueur().getHitBox())) {
                map.getJoueur().getInv().ajouterCollectible(map.getListeCollectibles().get(i));
                map.getListeCollectibles().remove(i);
                i--;
            }
        }
    }
}
