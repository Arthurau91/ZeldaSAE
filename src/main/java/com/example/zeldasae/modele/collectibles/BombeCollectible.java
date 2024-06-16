package com.example.zeldasae.modele.collectibles;

import com.example.zeldasae.modele.armes.Bombe;
import com.example.zeldasae.modele.entities.Joueur;

public class BombeCollectible extends Collectible {

    public BombeCollectible (int x, int y) {
        super(0, 5, "Bombe", 15, 30, 30, x, y);
    }

    @Override
    public void utiliserItem(Joueur j) {
        Bombe b = new Bombe();
        j.getInv().changerArme(b);
    }
}
