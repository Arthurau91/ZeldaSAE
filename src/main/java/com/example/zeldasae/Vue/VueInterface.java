package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class VueInterface {

    protected Pane paneInterface;
    private Joueur joueur;
    private boolean afficheInterface;
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
            setAfficheCoffre(true);
        } else if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.paneInterface.setVisible(false);
            setAfficheCoffre(false);
        }
    }

    public void setAfficheCoffre(boolean b) {
        this.afficheInterface = b;
    }
}
