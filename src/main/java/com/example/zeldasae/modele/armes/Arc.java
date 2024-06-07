package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectile;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;

public class Arc extends Arme {

    public Arc(int large, int haut, int x, int y) {
        super("Arc,", 2, 2, 1, large, haut, x, y);
    }

    public Projectile creerProjectile(KeyEvent keyEvent) {
        Projectile fleche = new Projectile(this.getDegats(), 15, 20, 10, keyEvent);
        if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {
            fleche.getHitBox().pivote(); //faire avec un observateur
        }
        return fleche;
    }

    @Override
    public void attaquer(KeyEvent keyEvent, Monde map) {
        Projectile p = creerProjectile(keyEvent);
        map.ajouterProjectile(p);
    }
}
