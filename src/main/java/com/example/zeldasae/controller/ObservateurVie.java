package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueBarreDeVie;
import com.example.zeldasae.modele.Entite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class ObservateurVie implements ChangeListener<Number> {

    private Entite entite;

    public ObservateurVie(Entite entite) {
        this.entite = entite;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double pourcentage = ((double) newValue.intValue() / entite.getPvMax()) * 100;
        VueBarreDeVie vueBarreDeVie = entite.getVueBarreDeVie();
        if (vueBarreDeVie != null) {
            vueBarreDeVie.setPourcentageVie(pourcentage);
            mettreAJourBarreDeVie(vueBarreDeVie);
        }
    }

    public void mettreAJourBarreDeVie(VueBarreDeVie barreDeVie) {
        double pourcentageVie = barreDeVie.getPourcentageVie();
        if (pourcentageVie < 25) {
            barreDeVie.setFill(Color.RED);
        } else if (pourcentageVie < 50) {
            barreDeVie.setFill(Color.ORANGE);
        } else {
            barreDeVie.setFill(Color.GREEN);
        }
    }
}