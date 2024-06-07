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

//        Rectangle r = new Rectangle(10, 10, Color.PINK);
//        r.setId(c.getNom());
//        r.setTranslateX(c.getHitBox().getX());
//        r.setTranslateY(c.getHitBox().getY());
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

    //    Image img = switchImageItem(i);
//    ImageView imageView = new ImageView(img);
//
//    int x, y;
//    x = (i.getPosSlotItems() - 1)%this.nbItemLigne * ((int) this.boxInventaire.getWidth()/this.nbItemLigne);
//    y = (i.getPosSlotItems() - 1)/this.nbItemLigne * ((int) this.boxInventaire.getHeight()/this.nbItemColonne) + 100;
//        imageView.setTranslateX(x);
//        imageView.setTranslateY(y);
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        imageView.setId("" + i.getPosSlotItems());
//        imageView.setOnMouseClicked(this.clickHandler);
//        this.boxInventaire.getChildren().add(imageView);
//
//    return new Image("file:src/main/resources/com/example/zeldasae/assets/" + i.getNom() + ".png");

}
