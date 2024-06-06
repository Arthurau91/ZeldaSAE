package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Projectile;
import javafx.scene.input.KeyEvent;

public class Arc extends Arme {

    public Arc(int large, int haut, int x, int y) {
        super("Arc,", 2, 2, 1, large, haut, x, y);
    }

    public Projectile creerProjectile(KeyEvent keyEvent) {
        Projectile fleche = new Projectile(this.getDegats(), 15, 20, 10, keyEvent);
        return fleche;
    }
}
