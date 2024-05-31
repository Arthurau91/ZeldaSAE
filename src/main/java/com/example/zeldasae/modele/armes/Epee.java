package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import javafx.beans.property.IntegerProperty;

public class Epee extends Arme {

    public Epee(int large, int haut, int x, int y) {
        super(0, 1, "Epee", 5, 1, 0.5, large, haut, x, y);
    }
}