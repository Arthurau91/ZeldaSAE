package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;
    private VueInventaire vueInv;
    private final Set<KeyCode> pressedKeys;
    private GestionnaireCoffre gestionnaireCoffre;


    public KeyHandler(Monde map, VueInventaire vueInv, GestionnaireCoffre gestionnaireCoffre) {
        this.map = map;
        this.vueInv = vueInv;
        this.pressedKeys = new HashSet<>();
        this.gestionnaireCoffre = gestionnaireCoffre;
    }
    private Item itemTest = new Arme(0,0, "Arme 1" ,500, 3); //à retirer, sert uniquement pour les tests


    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            this.pressedKeys.add(keyEvent.getCode());
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED)
            this.pressedKeys.remove(keyEvent.getCode());

        String direction = "";

        if (pressedKeys.contains(Z))
            direction += "up";
        if (pressedKeys.contains(S))
            direction += "down";
        if (pressedKeys.contains(Q))
            direction += "left";
        if (pressedKeys.contains(D))
            direction += "right";

        switch (keyEvent.getCode()) {
            case I: // inventaires
                if (this.map.coffreOuvert() == null)
                    this.vueInv.toggleAffichageInterface(keyEvent);
                break;
            case E: // interagir
                for (VueCoffre coffre : gestionnaireCoffre.getVueCoffreList()) {
                    if ((this.map.getJoueur().peutOuvrirUnCoffre(this.map, 1)) || coffre.getCoffre().isEstOuvert()) {
                        coffre.toggleAffichageInterface(keyEvent);
                        break;
                    }
                }
                break;
            case F: // à retirer sert pour les tests
                for (VueCoffre coffre : gestionnaireCoffre.getVueCoffreList())
                    coffre.getCoffre().ajouterItem(itemTest);
                break;

        }

        this.map.getJoueur().setDirection(direction);
    }

}