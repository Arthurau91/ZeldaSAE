package com.example.zeldasae.modele;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarreDeVie extends Pane {
    private Rectangle barreVieTotale;
    private Rectangle barreVieActuelle;

    public BarreDeVie(double largeur, double hauteur) {
        this.barreVieTotale = new Rectangle(largeur, hauteur);
        this.barreVieTotale.setFill(Color.GRAY);
        this.barreVieActuelle = new Rectangle(largeur, hauteur);
        this.barreVieActuelle.setFill(Color.GREEN);
        getChildren().addAll(this.barreVieTotale, this.barreVieActuelle);
    }

    public void setPourcentageVie(double pourcentage) {
        if (pourcentage < 0) {
            pourcentage = 0;
        } else if (pourcentage > 100) {
            pourcentage = 100;
        }

        this.barreVieActuelle.setWidth(this.barreVieTotale.getWidth() * (pourcentage / 100));
    }

    public double getPourcentageVie() {
        return (this.barreVieActuelle.getWidth() / this.barreVieTotale.getWidth()) * 100;
    }

    public void setFill(Color color) {
        this.barreVieActuelle.setFill(color);
    }
}