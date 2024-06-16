package com.example.zeldasae.modele.collectibles;

import com.example.zeldasae.modele.entities.Joueur;

public class Fruit extends Collectible {

    private Joueur joueur;

    public Fruit(int x, int y, Joueur joueur) {
        super(0, 10, "Fruit", 5, 30, 30, x, y);
        this.joueur = joueur;
    }
    @Override
    public void utiliserItem(Joueur j) {
        if (super.getQuantite() > 0 && joueur.getPv() + 1 <= joueur.getPvMax()) {
            super.retirer(1);
            this.joueur.ajouterVie(1);
        }
    }
}