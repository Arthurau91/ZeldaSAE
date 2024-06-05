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
    private VueCoffre vueCoffre;
    private final Set<KeyCode> pressedKeys;


    public KeyHandler(Monde map, VueInventaire vueInv, VueCoffre vueCoffre) {
        this.map = map;
        this.vueInv = vueInv;
        this.pressedKeys = new HashSet<>();
        this.vueCoffre = vueCoffre;
    }
    private Item itemTest = new Arme(0,0, "Arme 1" ,500, 3); //à retirer, sert uniquement pour les tests
    private Item itemTest2 = new Arme(0,0, "Arme 2",2, 5);
    private Item itemTest3 = new Armure(0,0, 500,"Armure 3", 6);
    private Item itemTest4 = new Armure(0,0, 500,"Armure 4", 19);


    @Override
    public void handle(KeyEvent keyEvent) {

        System.out.println("X : " + this.map.getJoueur().getX());
        System.out.println("Y : " + this.map.getJoueur().getY());

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
            case E:
                if (!this.vueCoffre.getCoffre().isEstOuvert())
                    this.vueInv.toggleAffichageInterface(keyEvent);
                break;
            case X: //à retirer, sert uniquement pour les tests
                this.map.getJoueur().getInv().ajouterItem(itemTest);
                this.map.getJoueur().getInv().ajouterItem(itemTest2);
                this.map.getJoueur().getInv().ajouterItem(itemTest3);
                this.map.getJoueur().getInv().ajouterItem(itemTest4);
                break;
            case C: //à retirer, sert uniquement pour les tests
                System.out.println("Arme : " + this.map.getJoueur().getInv().getArmeActuelle().getNom() + " Armure : " + this.map.getJoueur().getInv().getArmureActuelle().getNom());
                break;
            case I:
                if ((this.map.getJoueur().peutOuvrirUnCoffre(this.map, 1)) || this.vueCoffre.getCoffre().isEstOuvert())
                    this.vueCoffre.toggleAffichageInterface(keyEvent);
                break;
            case F: // à retirer sert pour les tests
                this.vueCoffre.getCoffre().ajouterItem(itemTest);
                break;

        }



        this.map.getJoueur().setDirection(direction);
    }

}