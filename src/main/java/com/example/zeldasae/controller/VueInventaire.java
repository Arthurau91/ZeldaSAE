package com.example.zeldasae.controller;

import javafx.scene.layout.Pane;

public class VueInventaire {

    private Pane boxInventaire;
    private boolean afficheInventaire;

    public VueInventaire(Pane h) {
        this.boxInventaire = h;
        afficheInventaire = false;
    }

    public void toggleAffichageInventaire() {
        if (getAfficheInventaire()) {
            boxInventaire.setVisible(false);
            setAfficheInventaire(false);
        }
        else {
            boxInventaire.setVisible(true);
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
