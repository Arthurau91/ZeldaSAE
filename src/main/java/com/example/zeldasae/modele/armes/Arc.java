package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.collectibles.Fleche;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Arc extends Arme {

    public Arc() {
        super("Arc", 2, 4, 0.8, 0, 0, 0, 0);
    }

    public Projectile creerProjectile(KeyEvent keyEvent, Monde map) {
        Projectile fleche = new ProjectileJoueur(this.getDegats(), 25, 20, 10, keyEvent, "Fleche", true);
        fleche.setPosMap(map.getJoueur().getHitBox().getX(), map.getJoueur().getHitBox().getY(), keyEvent.getCode().toString());
        return fleche;
    }

    @Override
    public boolean peutAttaquer(Monde map) {
        if (map.getJoueur().getInv().getCollectible(new Fleche(0, 0)) != null)
            return map.getJoueur().getInv().getCollectible(new Fleche(0, 0)).getQuantite() > 0;
        else
            return false;
    }

    @Override
    public void attaquer(KeyEvent keyEvent, Monde map) {
        map.getJoueur().getInv().getCollectible(new Fleche(0, 0)).retirer(1);

        Projectile p = creerProjectile(keyEvent, map);
        map.addProjectile(p);

        map.getJoueur().setPeutDonnerCoup(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(map.getJoueur().getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> map.getJoueur().setPeutDonnerCoup(true));
        pause.play();
    }


}
