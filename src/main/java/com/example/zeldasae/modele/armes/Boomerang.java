package com.example.zeldasae.modele.armes;

import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.entities.Ennemi;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class Boomerang extends Arme {

    private ArrayList<Ennemi> ennemisTouchesAller;

    public Boomerang() {
        super("Boomerang", 2, 6, 2.5, 0,0,0,0);
        this.ennemisTouchesAller = new ArrayList<>();
    }

    public Projectile creerProjectile(KeyEvent keyEvent, Monde map) {
        Projectile boomerang = new ProjectileJoueur(getDegats(), 30, 20, 20, keyEvent, "Boomerang", false);
        boomerang.setPosMap(map.getJoueur().getHitBox().getX(), map.getJoueur().getHitBox().getY(), keyEvent.getCode().toString());
        return boomerang;
    }

    @Override
    public void attaquer(KeyEvent keyEvent, Monde map) {
        Projectile p = creerProjectile(keyEvent, map);
        map.addProjectile(p);

        map.getJoueur().setPeutDonnerCoup(false);

        PauseTransition pause1 = new PauseTransition(Duration.seconds(map.getJoueur().getInv().getArmeActuelle().getDelaiRecuperation()));
        pause1.setOnFinished(event -> supprimerBoomerang(map, p));
        pause1.play();

        PauseTransition pause2 = new PauseTransition(Duration.seconds(1.25));
        pause2.setOnFinished(event -> inverserBoomerang(p));
        pause2.play();
    }

    public void inverserBoomerang(Projectile p) {
        p.inverserDirection();
        this.ennemisTouchesAller.clear();
    }

    public void supprimerBoomerang(Monde map, Projectile p) {
        map.getJoueur().setPeutDonnerCoup(true);
        p.setObstacleTouche(true);
        this.ennemisTouchesAller.clear();
    }

    public void infligerDegats(Ennemi e) {
        if (!dansEnnemisTouchesAller(e)) {
            e.perdreVie(getDegats());
            this.ennemisTouchesAller.add(e);
        }
    }

    public boolean dansEnnemisTouchesAller(Ennemi e) {
        for (Ennemi ennemi : this.ennemisTouchesAller) {
            if (ennemi == e) {
                return true;
            }
        }
        return false;
    }
}