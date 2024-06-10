package com.example.zeldasae.modele.entities;

import com.example.zeldasae.modele.Inventaire;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Entite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;

public class Joueur extends Entite {

    private Inventaire inv;
    private BooleanProperty peutDonnerCoupProperty;

    public Joueur(int x, int y, int width, int height, int column, int rows) {
        super(x, y, "j1", width, height, column, rows);
        this.inv = new Inventaire();
        this.peutDonnerCoupProperty = new SimpleBooleanProperty(true);
    }

    public Inventaire getInv() {
        return this.inv;
    }

    public boolean getPeutDonnerCoupProperty() {
        return this.peutDonnerCoupProperty.get();
    }

    public void setPeutDonnerCoupProperty(boolean peutDonnerCoupProperty) {
        this.peutDonnerCoupProperty.set(peutDonnerCoupProperty);
    }

    public BooleanProperty peutDonnerCoupProperty() {
        return peutDonnerCoupProperty;
    }

    public void attaquer(KeyEvent keyEvent, Monde map) {
        this.setPeutDonnerCoupProperty(false);
        this.getInv().getArmeActuelle().attaquer(keyEvent, map);
    }

    @Override
    public boolean deplacement(Monde m) {
        boolean deplacement = super.deplacement(m);
        return deplacement;
    }
}
