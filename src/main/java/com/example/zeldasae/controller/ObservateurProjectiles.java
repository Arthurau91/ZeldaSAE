package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueArmes.VueArme;
import com.example.zeldasae.Vue.VueProjectile;
import com.example.zeldasae.modele.Projectile;
import com.example.zeldasae.modele.ProjectileJoueur;
import javafx.collections.ListChangeListener;

public class ObservateurProjectiles implements ListChangeListener<Projectile> {

    private VueProjectile vueProjectile;

    public ObservateurProjectiles(VueProjectile vueProjectile) {
        this.vueProjectile = vueProjectile;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while (change.next()) {
            for(Projectile p : change.getAddedSubList()) {
                if (p instanceof ProjectileJoueur){
                    this.vueProjectile.creerProjectileJoueurVue(p);
                }
                else {
                    this.vueProjectile.creerProjectileEnnemiVue(p);
                }
            }
            for (int i = 0; i < change.getRemoved().size(); i++) {
                this.vueProjectile.supprimerProjectileVue(change.getRemoved().get(i));
            }
        }
    }
}
