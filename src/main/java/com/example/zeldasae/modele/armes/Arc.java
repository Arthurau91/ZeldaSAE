package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Projectile;

public class Arc extends Arme {

    public Arc(int large, int haut, int x, int y) {
        super("Arc,", 2, 2, 1, large, haut, x, y);
    }

    public Projectile creerProjectile() {
        Projectile fleche = new Projectile(this.getDegats(), 5, 20, 10);
        return fleche;
    }
}
