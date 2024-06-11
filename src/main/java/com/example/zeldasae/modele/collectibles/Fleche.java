package com.example.zeldasae.modele.collectibles;

import com.example.zeldasae.modele.Collectible;

public class Fleche extends Collectible {

    public Fleche (int quantite, int quantite_max, int large, int haut, int x, int y) {
        super(quantite, quantite_max, "Fleche", 10, large, haut, x, y, false);
    }

    @Override
    public void utiliserCollectible() {
        this.retirer(1);
    }
}