package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueCollectible;
import com.example.zeldasae.modele.Collectible;
import javafx.collections.ListChangeListener;

public class ObservateurCollectibles implements ListChangeListener<Collectible> {

    private VueCollectible vueCollectible;

    public ObservateurCollectibles(VueCollectible vueCollectible) {
        this.vueCollectible = vueCollectible;
    }

    @Override
    public void onChanged(Change<? extends Collectible> change) {
        while (change.next()) {
            for (Collectible c : change.getAddedSubList()) {
                vueCollectible.creerCollectibleVue(c);
            }
            for (Collectible c : change.getRemoved()) {
                vueCollectible.supprimerCollectibleVue(c);
            }
        }
    }
}
