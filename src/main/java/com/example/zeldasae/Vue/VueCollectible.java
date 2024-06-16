package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.collectibles.Collectible;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueCollectible {

    private Pane paneEntites;

    public VueCollectible(Pane mapPane) {
        this.paneEntites = mapPane;
    }

    public void creerCollectibleVue(Collectible c) {
        ImageView imageView = switchImageCollectible(c);
        this.paneEntites.getChildren().add(imageView);
    }

    public void supprimerCollectibleVue(Collectible c) {
        paneEntites.getChildren().remove(paneEntites.lookup("#" + c.getNom()));
    }

    public ImageView switchImageCollectible(Collectible c) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + c.getType() + ".png");
        ImageView imageView = new ImageView(img);
        imageView.setId(c.getNom());
        imageView.setTranslateX(c.getHitBox().getX());
        imageView.setTranslateY(c.getHitBox().getY());
        imageView.setFitWidth(c.getHitBox().getLarge());
        imageView.setFitHeight(c.getHitBox().getHaut());

        switch (c.getType()) {
            case "Fleche":
                imageView.setTranslateX(c.getHitBox().getX() - 15);
                imageView.setFitWidth(c.getHitBox().getLarge() + 30);
                break;
        }
        return imageView;
    }
}
