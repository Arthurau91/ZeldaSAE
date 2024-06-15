package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import javafx.scene.input.KeyEvent;

public class Hache extends Arme {

    public Hache() {
        super("Hache", 2, 3, 1, 100, 100, 0, 0);
    }

    @Override
    public void setPosMap(int x, int y, KeyEvent keyEvent) {
        this.getHitBox().setX(x-30);
        this.getHitBox().setY(y-30);
    }
}
