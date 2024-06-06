package com.example.zeldasae.controller;
import com.example.zeldasae.Vue.VueArme;
import com.example.zeldasae.Vue.VueCollectible;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.modele.*;
import com.example.zeldasae.modele.armes.Arc;
import com.example.zeldasae.modele.collectibles.Fruit;
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
    private VueCollectible vueCollectible;
    private final Set<KeyCode> pressedKeys;

    public KeyHandler(Monde map, VueInventaire vueInv, VueArme vueArme, VueCollectible vueCollectible) {
        this.map = map;
        this.vueInv = vueInv;
        this.vueArme = vueArme;
        this.pressedKeys = new HashSet<>();
        this.vueCollectible = vueCollectible;
    }

    //à retirer, sert uniquement pour les tests
    private Item itemTest = new Arme("Arme 1" ,1, 3,0.5, 150, 15, 0, 0);
    private Item itemTest2 = new Arme("Arme 2",2, 8, 2, 100, 100, 0, 0);
    private Item itemTest3 = new Armure(500,"Armure 3", 6);
    private Item itemTest4 = new Armure(500,"Armure 4", 19);
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
                    Collectible collectibleTest = new Fruit(0, 10,  5, 10, 10, 50, 50, this.map.getJoueur());
                    this.map.getJoueur().getInv().ajouterItem(itemTest);
                    this.map.getJoueur().getInv().ajouterItem(itemTest2);
                    this.map.getJoueur().getInv().ajouterItem(itemTest3);
                    this.map.getJoueur().getInv().ajouterItem(itemTest4);
//                    this.map.getJoueur().getInv().ajouterItem(collectibleTest);
                    this.map.ajouterCollectible(collectibleTest);
                    break;
                case A:
                    System.out.println(this.map.getJoueur().getPv());
                    this.map.getJoueur().getInv().changerArme(arcTest);
                    for(int i = 0; i < this.map.getJoueur().getInv().getListeItems().size(); i++) {
                        if (this.map.getJoueur().getInv().getListeItems().get(i) instanceof Collectible) {
                            System.out.println(((Collectible) this.map.getJoueur().getInv().getListeItems().get(i)).getQuantite() + " " + ((Collectible) this.map.getJoueur().getInv().getListeItems().get(i)).getType());
                        }
                    }
                    break;
                case LEFT, RIGHT, UP, DOWN:
                    if (this.vueArme.isPeutDonnerCoup()) {
                        if(this.map.getJoueur().getInv().getArmeActuelle() instanceof Arc) {
                            Projectile p = ((Arc) this.map.getJoueur().getInv().getArmeActuelle()).creerProjectile(keyEvent);
                            if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {
                                p.getHitBox().pivote();
                            }
                            this.map.ajouterProjectile(p);
                        }
                        else {
                            this.map.getJoueur().getInv().getArmeActuelle().setPosMap(this.map.getJoueur().getX(), this.map.getJoueur().getY(), keyEvent);
                            this.vueArme.donnerCoup(this.map.getJoueur().getInv().getArmeActuelle().getX(), this.map.getJoueur().getInv().getArmeActuelle().getY());
                            this.map.getJoueur().getInv().getArmeActuelle().checkCoupTouche(this.map.getListeEnnemis());
                            if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {
                                this.map.getJoueur().getInv().getArmeActuelle().getHitBox().pivote();
                            }
                        }
                    }
                    break;
            }
        }

        this.map.getJoueur().setDirection(direction);
    }

}