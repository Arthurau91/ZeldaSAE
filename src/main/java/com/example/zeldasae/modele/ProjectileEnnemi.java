package com.example.zeldasae.modele;

public class ProjectileEnnemi extends Projectile {

    public ProjectileEnnemi(int degats, int vitesse, int large, int haut, String type) {
        super(degats, vitesse, large, haut, type);
    }

    @Override
    public void checkCoupTouche(Monde map) {
        if (this.getHitBox().estDedansHitbox(map.getJoueur().getHitBox()) && !super.isObstacleTouche()) {
            super.setObstacleTouche(true);
            map.getJoueur().perdreVie(getDegats());
            System.out.println("Pv du joueur : " + map.getJoueur().getPv());
        }
    }


}
