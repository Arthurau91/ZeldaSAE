package com.example.zeldasae.controller;
import com.example.zeldasae.modele.Armure;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Monde;
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

    public KeyHandler(Monde map, VueInventaire vueInv) {
        this.map = map;
        this.vueInv = vueInv;
        this.pressedKeys = new HashSet<>();
    }
    private Item itemTest = new Armure(0,0, 500,"Item", 1); //à retirer, sert uniquement pour les tests
    private Item itemTest2 = new Armure(0,0, 500,"Item", 5);
    private Item itemTest3 = new Armure(0,0, 500,"Item", 6);
    private Item itemTest4 = new Armure(0,0, 500,"Item", 19);


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
            case E:
//                System.out.println("e");
                this.vueInv.toggleAffichageInventaire();
                break;
            case X: //à retirer, sert uniquement pour les tests
                this.map.getJoueur().getInv().ajouterItem(itemTest);
                this.map.getJoueur().getInv().ajouterItem(itemTest2);
                this.map.getJoueur().getInv().ajouterItem(itemTest3);
                this.map.getJoueur().getInv().ajouterItem(itemTest4);
                break;
            case C: //à retirer, sert uniquement pour les tests
                itemTest.setPosSlotItems(itemTest.getPosSlotItems()+1);
                break;
            case V: //à retirer, sert uniquement pour les tests
                itemTest.setPosSlotItems(itemTest.getPosSlotItems()-1);
                break;
        }

        this.map.getJoueur().setDirection(direction);
    }

}