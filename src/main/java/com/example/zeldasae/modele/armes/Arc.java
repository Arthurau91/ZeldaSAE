package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.*;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Arc extends Arme {

    public Arc() {
        super("Arc", 2, 2, 0.8, 0, 0, 0, 0);
    }

    public Projectile creerProjectile(KeyEvent keyEvent, Monde map) {
        Projectile fleche = new ProjectileJoueur(this.getDegats(), 15, 20, 10, keyEvent, "Fleche", true);
        fleche.setPosMap(map.getJoueur().getHitBox().getX(), map.getJoueur().getHitBox().getY(), keyEvent.getCode().toString());
        return fleche;
    }

    @Override
    public boolean peutAttaquer(Monde map) {
        return map.getJoueur().getInv().getFleche() != null && map.getJoueur().getInv().getFleche().getQuantite() > 0;
    }

    @Override
    public void attaquer(KeyEvent keyEvent, Monde map) {
        map.getJoueur().getInv().getFleche().utiliserItem(map.getJoueur());

        Projectile p = creerProjectile(keyEvent, map);
        map.ajouterProjectile(p);

        map.getJoueur().setPeutDonnerCoupProperty(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(map.getJoueur().getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> map.getJoueur().setPeutDonnerCoupProperty(true));
        pause.play();
    }


}
