package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueArme;
import com.example.zeldasae.modele.Projectile;
import javafx.collections.ListChangeListener;

public class ObservateurProjectiles implements ListChangeListener<Projectile> {

    private VueArme vueArme;

    public ObservateurProjectiles(VueArme vueArme) {
        this.vueArme = vueArme;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while (change.next()) {
            for(Projectile p : change.getAddedSubList()) {
                this.vueArme.creerProjectile(p);
            }
            for (int i = 0; i < change.getRemoved().size(); i++) {
                this.vueArme.supprimerProjectile(change.getRemoved().get(i));
            }
        }
    }
}
