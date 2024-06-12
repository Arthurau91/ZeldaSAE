package com.example.zeldasae.controller;
import com.example.zeldasae.Vue.*;
import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Armure;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.armes.Arc;
import com.example.zeldasae.modele.armes.Epee;
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
    private GestionnaireCoffre gestionnaireCoffre;

    public KeyHandler(Monde map, VueInventaire vueInv, VueTerrain vueTerrain, VueArme vueArme, VueCollectible vueCollectible, GestionnaireCoffre gestionnaireCoffre) {
        this.map = map;
        this.vueInv = vueInv;
        this.vueArme = vueArme;
        this.pressedKeys = new HashSet<>();
        this.vueTerrain = vueTerrain;
        this.vueCollectible = vueCollectible;
        this.gestionnaireCoffre = gestionnaireCoffre;
    }

    //à retirer, sert uniquement pour les tests
    private Epee itemTest = new Epee( 150, 15, 0, 0);
//    private Epee itemTest2 = new Epee(100, 100, 0, 0);
    private Item itemTest3 = new Armure(500,"Armure 3", 6);
    private Item itemTest4 = new Armure(500,"Armure 4", 19);
    private Arc arcTest = new Arc(10, 10, 10, 40);

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
        if (pressedKeys.contains(F)) {
            for (int i = 0 ; i < this.gestionnaireCoffre.getVueCoffreList().size() ; i++) {
                if (this.gestionnaireCoffre.getVueCoffreList().get(i).getCoffre().isEstOuvert()) {
                    this.gestionnaireCoffre.getVueCoffreList().get(i).ajouterItem(itemTest);
                    this.gestionnaireCoffre.getVueCoffreList().get(i).ajouterItem(arcTest);
                }
            }
        }
        if (pressedKeys.contains(X)) {
            this.map.getJoueur().getInv().ajouterItem(itemTest);
        }

        if (pressedKeys.contains(SHIFT))
            vueTerrain.deplaceBloc();

        if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            switch (keyEvent.getCode()) {
                case X: //à retirer, sert uniquement pour les tests
                    Collectible collectibleTest = new Fruit(0, 10,  5, 30, 30, 50, 50, this.map.getJoueur());
                    this.map.getJoueur().getInv().ajouterItem(itemTest);
                    this.map.getJoueur().getInv().ajouterItem(itemTest3);
                    this.map.getJoueur().getInv().ajouterItem(itemTest4);
                    this.map.ajouterCollectible(collectibleTest);
                    break;
                case A: // switch arme
                    this.map.getJoueur().getInv().echangerArmes();
                    break;
                case P:  // test
                    this.map.getJoueur().getInv().changerArme(arcTest);
                    for(int i = 0; i < this.map.getJoueur().getInv().getListeItems().size(); i++) {
                        if (this.map.getJoueur().getInv().getListeItems().get(i) instanceof Collectible) { //TODO mettre méthode abstraite commune aux Items ?
                            System.out.println(((Collectible) this.map.getJoueur().getInv().getListeItems().get(i)).getQuantite() + " " + ((Collectible) this.map.getJoueur().getInv().getListeItems().get(i)).getType());
                        }
                    }
                    break;
                case LEFT, RIGHT, UP, DOWN: // tirer
                    if (this.map.getJoueur().getPeutDonnerCoupProperty() && this.map.getJoueur().getInv().getArmeActuelle() != null) {
                        this.map.getJoueur().attaquer(keyEvent, map);
                        this.vueArme.donnerCoup(this.map.getJoueur().getInv().getArmeActuelle().getX(), this.map.getJoueur().getInv().getArmeActuelle().getY(), keyEvent);
                        if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {
                            this.map.getJoueur().getInv().getArmeActuelle().getHitBox().pivote();
                        }
                    }
                    break;
                case I: // inventaires
                    if (this.map.coffreOuvert() == null)
                        this.vueInv.toggleAffichageInterface(keyEvent);
                    break;
                case E: // interagir
                    for (VueCoffre coffre : gestionnaireCoffre.getVueCoffreList()) {
                        if ((this.map.getJoueur().peutOuvrirUnCoffre(this.map, coffre.getCoffre(), 1)) || coffre.getCoffre().isEstOuvert()) {
                            coffre.toggleAffichageInterface(keyEvent);
                            break;
                        }
                    }
                    break;
            }

            this.map.getJoueur().setDirection(direction);
        }

    }
}