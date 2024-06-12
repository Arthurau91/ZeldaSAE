package com.example.zeldasae.controller;
import com.example.zeldasae.Vue.VueArme;
import com.example.zeldasae.Vue.VueCollectible;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Armure;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.armes.Arc;
import com.example.zeldasae.modele.armes.Epee;
import com.example.zeldasae.modele.collectibles.Fleche;
import com.example.zeldasae.modele.collectibles.Fruit;
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
    private Set<KeyCode> pressedKeys;
    private VueArme vueArme;
    private VueCollectible vueCollectible;

    public KeyHandler(Monde map, VueInventaire vueInv, VueTerrain vueTerrain, VueArme vueArme, VueCollectible vueCollectible) {
        this.map = map;
        this.vueInv = vueInv;
        this.vueArme = vueArme;
        this.pressedKeys = new HashSet<>();
        this.vueTerrain = vueTerrain;
        this.vueCollectible = vueCollectible;
    }

    //à retirer, sert uniquement pour les tests
    private Epee itemTest = new Epee(0, 0);
    private Item itemTest3 = new Armure(500,"Armure 3", 6);
    private Item itemTest4 = new Armure(500,"Armure 4", 19);
    private Arc arcTest = new Arc(10, 10, 10, 40);
    Collectible fleche = new Fleche(0, 30, 30, 30, 0, 0);

    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            this.pressedKeys.add(keyEvent.getCode());
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            this.pressedKeys.remove(keyEvent.getCode());
            if (keyEvent.getCode() == Z)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("up",""));
            if (keyEvent.getCode() == S)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("down",""));
            if (keyEvent.getCode() == Q)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("left",""));
            if (keyEvent.getCode() == D)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("right",""));
        }

        String direction = "";

        if (pressedKeys.contains(Z)) {
            direction += "up";
            map.getJoueur().addDirectionImage("up");
        }
        if (pressedKeys.contains(S)) {
            direction += "down";
            map.getJoueur().addDirectionImage("down");
        }
        if (pressedKeys.contains(Q)) {
            direction += "left";
            map.getJoueur().addDirectionImage("left");
        }
        if (pressedKeys.contains(D)) {
            direction += "right";
            map.getJoueur().addDirectionImage("right");
        }
        this.map.getJoueur().setDeplacement(direction);

        if (pressedKeys.contains(SHIFT))
            vueTerrain.deplaceBloc();

        if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            switch (keyEvent.getCode()) {
                case E:
//                System.out.println("e");
                    this.vueInv.toggleAffichageInventaire();
                    break;
                case X: //à retirer, sert uniquement pour les tests
                    Collectible fruitTest = new Fruit(0, 10, 30, 30, 50, 50, this.map.getJoueur());
                    this.map.getJoueur().getInv().ajouterItem(itemTest);
                    this.map.getJoueur().getInv().ajouterItem(itemTest3);
                    this.map.getJoueur().getInv().ajouterItem(itemTest4);
                    this.map.ajouterCollectible(fruitTest);
                    this.map.ajouterCollectible(fleche);
                    break;
                case A:
                    this.map.getJoueur().getInv().echangerArmes();
                    System.out.println("Arme : " + this.map.getJoueur().getInv().getArmeActuelle().getNom() + " Armure : " + this.map.getJoueur().getInv().getArmureActuelle().getNom());
                    break;
                case P: //à retirer, sert uniquement pour les tests
                    this.map.getJoueur().getInv().changerArme(arcTest);
                    break;
                case TAB: //à retirer, sert uniquement pour les tests
                    if(this.map.getJoueur().getInv().getFleche() != null) {
                        this.map.getJoueur().getInv().getFleche().ajouter(1);
                    }
                    else {
                        this.map.getJoueur().getInv().ajouterItem(fleche);
                    }
                    break;
                case LEFT, RIGHT, UP, DOWN:
                    if (this.map.getJoueur().getPeutDonnerCoupProperty(map)) {
                        this.map.getJoueur().attaquer(keyEvent, map);
                        this.vueArme.donnerCoup(this.map.getJoueur().getInv().getArmeActuelle().getX(), this.map.getJoueur().getInv().getArmeActuelle().getY(), keyEvent);
                        if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {
                            this.map.getJoueur().getInv().getArmeActuelle().getHitBox().pivote();
                        }
                    }
                    break;
            }
        }
    }

}