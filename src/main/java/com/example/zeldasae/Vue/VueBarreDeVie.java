package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Ennemi;
import com.example.zeldasae.modele.Entite;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueBarreDeVie extends Pane {
    private Rectangle barreVieTotale;
    private Rectangle barreVieActuelle;
    private Pane paneEntites;

    public VueBarreDeVie(double largeur, double hauteur, Pane paneEntites) {
        this.barreVieTotale = new Rectangle(largeur, hauteur);
        this.barreVieTotale.setFill(Color.GRAY);
        this.barreVieActuelle = new Rectangle(largeur, hauteur);
        this.barreVieActuelle.setFill(Color.GREEN);
        this.paneEntites = paneEntites;
        ajouterBarreDeVieEnnemiAuPane(this);
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

    public void supprimerBarreDeVie() {
        getChildren().removeAll(this.barreVieTotale, this.barreVieActuelle);
    }

    private void ajouterBarreDeVieEnnemiAuPane(VueBarreDeVie ennemi) {
        if (!paneEntites.getChildren().contains(ennemi)) {
            paneEntites.getChildren().add(this);
        }
    }

    public void mettreAJourPositionBarreDeVieJoueur(int deltaX, int deltaY, VueBarreDeVie vueBarreDeVie) {
        vueBarreDeVie.setTranslateX(vueBarreDeVie.getTranslateX() + deltaX);
        vueBarreDeVie.setTranslateY(vueBarreDeVie.getTranslateY() + deltaY);
    }


}