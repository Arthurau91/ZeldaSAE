package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.KeyEvent;

public class Epee extends Arme {

    public Epee(int large, int haut, int x, int y) {
        super("Epee", 3, 2, 0.5, large, haut, x, y);
    }

}