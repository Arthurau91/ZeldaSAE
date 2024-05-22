package com.example.zeldasae.modele;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarreDeVie extends Pane {
    private Rectangle barreApresPerte;
    private Rectangle barreAvantPerte;

    public BarreDeVie(double largeur, double hauteur) {
        this.barreApresPerte = new Rectangle(largeur, hauteur);
        this.barreApresPerte.setFill(Color.GRAY);

        this.barreAvantPerte = new Rectangle(largeur, hauteur);
        this.barreAvantPerte.setFill(Color.GREEN);

        getChildren().addAll(this.barreApresPerte, this.barreAvantPerte);
    }


    public void setPourcentageVie(double pourcentage) {
        if (pourcentage < 0) {
            pourcentage = 0;
        } else if (pourcentage > 100) {
            pourcentage = 100;
        }
        this.barreAvantPerte.setWidth(this.barreApresPerte.getWidth() * (pourcentage / 100));

        if (this.barreAvantPerte.getWidth() < 25) {
            this.barreAvantPerte.setFill(Color.RED);
        }
    }


}