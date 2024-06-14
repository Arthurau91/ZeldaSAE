package com.example.zeldasae.modele;

import javafx.scene.input.KeyEvent;

public class ProjectileJoueur extends Projectile {

    public ProjectileJoueur(int degats, int vitesse, int large, int haut, KeyEvent keyEvent, String type, boolean retireEnnemiTouche) {
        super(degats, vitesse, large, haut, keyEvent, type, retireEnnemiTouche);
    }

    @Override
    public void checkCoupTouche(Monde map) {
        for (Ennemi e : map.getListeEnnemis()) {
                if (e.getHitBox().estDedansHitbox(super.getHitBox()) && !super.isObstacleTouche() && this.isRetireEnnemiTouche()) {
                    super.setObstacleTouche(true);
                    e.perdreVie(getDegats());
//                    System.out.println("Pv de l'ennemi : " + e.getPv());
                }
                else if(e.getHitBox().estDedansHitbox(super.getHitBox()) && !super.isObstacleTouche()) {
                    map.getJoueur().getInv().getArmeActuelle().infligerDegats(e);
            }
        }
    }
}