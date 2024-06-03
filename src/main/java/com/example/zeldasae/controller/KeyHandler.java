package com.example.zeldasae.controller;
import com.example.zeldasae.Vue.VueArme;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.armes.Arc;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;
import static javafx.scene.input.KeyCode.*;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;
    private VueInventaire vueInv;
    private VueArme vueArme;
    private final Set<KeyCode> pressedKeys;

    public KeyHandler(Monde map, VueInventaire vueInv, VueArme vueArme) {
        this.map = map;
        this.vueInv = vueInv;
        this.vueArme = vueArme;
        this.pressedKeys = new HashSet<>();
    }

    //à retirer, sert uniquement pour les tests
    private Item itemTest = new Arme("Arme 1" ,1, 3,0.5, 150, 15, 0, 0);
    private Item itemTest2 = new Arme("Arme 2",2, 5, 2, 100, 100, 0, 0);
    private Item itemTest3 = new Armure(500,"Armure 3", 6);
    private Item itemTest4 = new Armure(500,"Armure 4", 19);
    private Collectible collectibleTest = new Collectible(0, 10, "CollectibleTest", 5);
    private Arc arcTest = new Arc(10, 10, 10, 40);

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

        if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
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
                    this.map.getJoueur().getInv().ajouterItem(collectibleTest);
                    break;
                case A:
                        collectibleTest.ajouter(1);
                        System.out.println("quantite de CollectibleTest : " + collectibleTest.getQuantite());
                        this.map.getJoueur().getInv().changerArme(arcTest);
                    break;
                case LEFT, RIGHT, UP, DOWN:
                    if (this.vueArme.isPeutDonnerCoup()) {
                        this.map.getJoueur().getInv().getArmeActuelle().setPosMap(this.map.getJoueur().getX(), this.map.getJoueur().getY(), keyEvent);
                        this.vueArme.donnerCoup(this.map.getJoueur().getInv().getArmeActuelle().getX(), this.map.getJoueur().getInv().getArmeActuelle().getY());
                        this.map.getJoueur().getInv().getArmeActuelle().checkCoupTouche(this.map.getListeEnnemis());
                        if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {
                            this.map.getJoueur().getInv().getArmeActuelle().getHitBox().pivote();
                        }
                    }
                    break;
                case P:
                    if(this.vueArme.isPeutDonnerCoup() && this.map.getJoueur().getInv().getArmeActuelle() instanceof Arc) {
                        Projectile p = ((Arc) this.map.getJoueur().getInv().getArmeActuelle()).creerProjectile();
                        this.vueArme.creerProjectile(p);
                        this.vueArme.gererDelaiProjectile(p);
                    }
            }
        }

        this.map.getJoueur().setDirection(direction);
    }

}