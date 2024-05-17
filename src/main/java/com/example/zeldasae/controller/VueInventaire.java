package com.example.zeldasae.controller;

import com.example.zeldasae.Main;
import com.example.zeldasae.modele.Item;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueInventaire {

    private Pane boxInventaire;
    private boolean afficheInventaire;

    public VueInventaire(Pane h) {
        this.boxInventaire = h;
        afficheInventaire = false;
    }

    public void testBoxInventaire() {
//        Rectangle r = new Rectangle(15, 15, Color.HOTPINK);
//        r.setTranslateX(50);
//        r.setTranslateY(50);
//        boxInventaire.getChildren().add(r);
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
