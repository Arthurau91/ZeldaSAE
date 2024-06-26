package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueBarreDeVie;
import com.example.zeldasae.modele.entities.Entite;
import com.example.zeldasae.Vue.VuesEntites.VueEntite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class ObservateurVie implements ChangeListener<Number> {

    private Entite entite;
    private VueEntite vueEntite;

    public ObservateurVie(Entite entite, VueEntite vueEntite) {
        this.entite = entite;
        this.vueEntite = vueEntite;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double pourcentage = ((double) newValue.intValue() / entite.getPvMax()) * 100;
        VueBarreDeVie vueBarreDeVie = vueEntite.getVueBarreDeVie();
        vueBarreDeVie.setPourcentageVie(pourcentage);
        if (vueBarreDeVie.getPourcentageVie() != 0) {
            mettreAJourBarreDeVie(vueBarreDeVie);
        } else {
            vueEntite.supprimerImageEntite();
            vueBarreDeVie.supprimerBarreDeVie();
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