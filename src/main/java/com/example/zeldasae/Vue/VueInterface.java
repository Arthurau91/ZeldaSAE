package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class VueInterface {

    protected Pane paneInterface;
    protected Joueur joueur;
    protected boolean afficheInterface;
    protected ClickHandlerInventaire clickHandler;


    public VueInterface(Pane pane, Joueur joueur) {
        this.paneInterface = pane;
        this.paneInterface.setStyle("-fx-background-color: black;");
        this.paneInterface.setVisible(false);
        this.joueur = joueur;
        this.afficheInterface = false;
        this.clickHandler = new ClickHandlerInventaire(pane, this.joueur);
    }
    public void toggleAffichageInterface(KeyEvent keyEvent) {
        if (!this.afficheInterface && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.paneInterface.setVisible(true);
            setAfficheInterface(true);
        } else if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.paneInterface.setVisible(false);
            setAfficheInterface(false);
        }
    }

    public void setAfficheInterface(boolean b) {
        this.afficheInterface = b;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Pane getPaneInterface() {
        return paneInterface;
    }
}
