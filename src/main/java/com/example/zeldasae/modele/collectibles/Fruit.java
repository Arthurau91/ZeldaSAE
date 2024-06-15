package com.example.zeldasae.modele.collectibles;

import com.example.zeldasae.modele.Collectible;
import com.example.zeldasae.modele.entities.Joueur;

public class Fruit extends Collectible {

    private Joueur joueur;

    public Fruit(int quantite, int quantite_max, int large, int haut, int x, int y, Joueur joueur) {
        super(quantite, quantite_max, "Fruit", 5, large, haut, x, y, true);
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