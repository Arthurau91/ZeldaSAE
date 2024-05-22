package com.example.zeldasae.controller;

import com.example.zeldasae.modele.BarreDeVie;
import javafx.collections.ListChangeListener;
import javafx.scene.paint.Color;

public class ObservateurVie implements ListChangeListener<BarreDeVie> {

    @Override
    public void onChanged(Change<? extends BarreDeVie> change) {
        while (change.next()) {
            System.out.println("non");
            if (change.wasUpdated() || change.wasAdded() || change.wasRemoved()) {
                System.out.println("oui");
                for (BarreDeVie barreDeVie : change.getList()) {
                    mettreAJourBarreDeVie(barreDeVie);
                }
            }
        }
    }



    public void mettreAJourBarreDeVie(BarreDeVie barreDeVie) {
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