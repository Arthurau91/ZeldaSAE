package com.example.zeldasae.modele;

import com.example.zeldasae.modele.collectibles.Fleche;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Joueur extends Entite{

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

    public boolean getPeutDonnerCoupProperty(Monde m) {
        return getInv().getArmeActuelle() != null && this.peutDonnerCoupProperty.get() && getInv().getArmeActuelle().peutAttaquer(m);
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

    @Override
    public void perdreVie(int degats) {
        if (getInv().getArmureActuelle() != null)
            degats = degats - getInv().getArmureActuelle().getResistance();

        if (degats < 0)
            degats = 0;
        setPv(this.getPv() - degats);
        if (this.getPv() <= 0) {
            setPv(0);
        }
    }

}
