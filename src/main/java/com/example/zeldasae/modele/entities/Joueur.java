package com.example.zeldasae.modele.entities;

import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Inventaire;
import com.example.zeldasae.modele.Monde;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Joueur extends Entite {

    private Inventaire inv;
    private boolean peutDonnerCoupProperty;
    private boolean peutPrendreCoupProperty;

    public Joueur(int x, int y, int column, int rows) {
        super(x, y, "j1", 30, 30, column, rows, 10);
        this.inv = new Inventaire();
        this.peutDonnerCoupProperty = true;
        this.peutPrendreCoupProperty = true;
    }

    public Inventaire getInv() {
        return this.inv;
    }

    public boolean PeutDonnerCoup() {
        return this.peutDonnerCoupProperty;
    }
    public void setPeutDonnerCoup(boolean peutDonnerCoupProperty) {
        this.peutDonnerCoupProperty = peutDonnerCoupProperty;
    }

    public boolean PeutPrendreCoup(){
        return peutPrendreCoupProperty;
    }
    public void setPeutPrendreCoup(boolean peutPrendreCoupProperty) {
        this.peutPrendreCoupProperty = peutPrendreCoupProperty;
    }

    @Override
    public void perdreVie(int degats) {
        int resistance = 0;
        if (getInv().getArmureActuelle() != null)
            resistance = getInv().getArmureActuelle().getResistance();
        if (PeutPrendreCoup()) {
            super.perdreVie(degats - resistance);
            setPeutPrendreCoup(false);

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> setPeutPrendreCoup(true));
            pause.play();
        }
    }

    public void attaquer(KeyEvent keyEvent, Monde map) {
        this.setPeutDonnerCoup(false);
        this.getInv().getArmeActuelle().attaquer(keyEvent, map);
    }

    @Override
    public boolean deplacement(Monde m) {
        boolean deplacement = super.deplacement(m);
        if (m.getTerrain().isBrouillard(m.getTerrain().changeCoo(getX(), getY())) && !inv.possedeCharme()) {
            if (this.getPv()/2 != 0)
                this.perdreVie(this.getPv() / 2);
            else this.perdreVie(this.getPv());
            this.setVitesse(1);
        }
        else
            this.setVitesse(10);
        return deplacement;
    }

    public boolean peutOuvrirUnCoffre(Monde monde, Coffre coffre, int tailleBloc) {
        for (Coffre c : monde.getCoffres()) {
            if (coffre.getId() == c.getId() && this.getHitBox().coffreProximite(c, tailleBloc)) {
                return true;
            }
        }
        return false;
    }

    public boolean peutAttaquerArme(Monde map) {
        if (getInv().getArmeActuelle() != null)
            return getInv().getArmeActuelle().peutAttaquer(map);
        else
            return false;
    }
}
