package com.example.zeldasae.controller;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Arme;
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
    private VueTerrain vueTerrain;
    private VueInventaire vueInv;
    private final Set<KeyCode> pressedKeys;

    public KeyHandler(Monde map, VueInventaire vueInv, VueTerrain vueTerrain) {
        this.map = map;
        this.vueInv = vueInv;
        this.pressedKeys = new HashSet<>();
        this.vueTerrain = vueTerrain;
    }
    private Item itemTest = new Arme(0,0, "Arme 1" ,500, 3); //à retirer, sert uniquement pour les tests
    private Item itemTest2 = new Arme(0,0, "Arme 2",2, 5);
    private Item itemTest3 = new Armure(0,0, 500,"Armure 3", 6);
    private Item itemTest4 = new Armure(0,0, 500,"Armure 4", 19);


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
        this.map.getJoueur().setDirection(direction);

        if (pressedKeys.contains(SPACE))
            vueTerrain.deplaceBloc();

        switch (keyEvent.getCode()) {
            case E:
//                System.out.println("e");
                this.vueInv.toggleAffichageInventaire(keyEvent);
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

        }
    }

}