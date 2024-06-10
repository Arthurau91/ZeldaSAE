package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectile;
import com.example.zeldasae.modele.ProjectileJoueur;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;

public class Arc extends Arme {

    public Arc(int large, int haut, int x, int y) {
        super("Arc,", 2, 2, 0.8, large, haut, x, y);
    }

    public Projectile creerProjectile(KeyEvent keyEvent, Monde map) {
        Projectile fleche = new ProjectileJoueur(this.getDegats(), 15, 20, 10, keyEvent);
        fleche.setPosMap(map.getJoueur().getX(), map.getJoueur().getY(), keyEvent.toString());
        return fleche;
    }

    @Override
    public void attaquer(KeyEvent keyEvent, Monde map) {
        Projectile p = creerProjectile(keyEvent, map);
        map.ajouterProjectile(p);
    }
}
