package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Collectible;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueCollectible {

    private Pane paneEntites;
    private Monde map;

    public VueCollectible(Pane mapPane, Monde map) {
        this.paneEntites = mapPane;
        this.map = map;
    }

    public void creerCollectibleVue(Collectible c) {
        Rectangle r = new Rectangle(10, 10, Color.PINK);
        r.setId(c.getNom());
        r.setTranslateX(c.getHitBox().getX());
        r.setTranslateY(c.getHitBox().getY());
        this.paneEntites.getChildren().add(r);
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
