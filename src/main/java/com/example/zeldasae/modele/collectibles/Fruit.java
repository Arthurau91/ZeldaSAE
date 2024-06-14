package com.example.zeldasae.modele.collectibles;

import com.example.zeldasae.modele.Collectible;
import com.example.zeldasae.modele.entities.Joueur;

public class Fruit extends Collectible {

    private Joueur joueur;

    public Fruit(int quantite, int quantite_max, int posSlotItems, int large, int haut, int x, int y, Joueur joueur) {
        super(quantite, quantite_max, "Fruit", posSlotItems, large, haut, x, y);
        this.joueur = joueur;
    }

    public void utiliserCollectible() {
        if (super.getQuantite() > 0 && joueur.getPv() + 1 <= joueur.getPvMax()) {
            super.retirer(1);
            this.joueur.ajouterVie(1);
        }
    }
}