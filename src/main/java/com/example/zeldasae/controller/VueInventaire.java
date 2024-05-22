package com.example.zeldasae.controller;

import javafx.scene.layout.Pane;

public class VueInventaire {

    private Pane boxInventaire;
    private boolean afficheInventaire;

    public VueInventaire(Pane boxInv) {
        this.boxInventaire = boxInv;
        afficheInventaire = false;
    }

    public void toggleAffichageInventaire() {
        if (getAfficheInventaire()) {
            this.boxInventaire.setVisible(false);
            setAfficheInventaire(false);
        }
        else {
            this.boxInventaire.setVisible(true);
            setAfficheInventaire(true);
        }
    }

    public boolean getAfficheInventaire() {
        return this.afficheInventaire;
    }

    public void setAfficheInventaire(boolean b) {
        this.afficheInventaire = b;
    }



}
