package com.example.zeldasae.modele;

import javafx.scene.layout.Pane;

public class Joueur extends Entite{

    private Inventaire inv;

    public Joueur(int x, int y, int width, int height, int column, int rows, Pane paneEntite) {
        super(x, y, "j1", width, height, column, rows, paneEntite);
        this.inv = new Inventaire();
    }

    public Inventaire getInv() {
        return this.inv;
    }

    @Override
    public boolean deplacement(Monde m) {
        boolean deplacement = super.deplacement(m);
        this.getVueEntite().changeImage();
        return deplacement;
    }

    public boolean peutOuvrirUnCoffre(Monde monde, int tailleBloc) {
        for (Coffre coffre : monde.getCoffres()) {
            if (this.getHitBox().coffreProximite(coffre, tailleBloc)) {
                return true;
            }
        }
        return false;
    }
}
