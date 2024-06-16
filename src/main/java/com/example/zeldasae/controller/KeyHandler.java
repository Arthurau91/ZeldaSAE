package com.example.zeldasae.controller;

import com.example.zeldasae.Vue.VueArmes.CreateurVueArme;
import com.example.zeldasae.Vue.VueArmes.VueArme;
import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.Vue.VueInventaire;
import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Charme;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Arc;
import com.example.zeldasae.modele.armes.Boomerang;
import com.example.zeldasae.modele.armes.Epee;
import com.example.zeldasae.modele.armes.Hache;
import com.example.zeldasae.modele.armures.ArmureChevalier;
import com.example.zeldasae.modele.armures.ArmureFragile;
import com.example.zeldasae.modele.collectibles.BombeCollectible;
import com.example.zeldasae.modele.collectibles.Collectible;
import com.example.zeldasae.modele.collectibles.Fleche;
import com.example.zeldasae.modele.collectibles.Fruit;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;
    private VueTerrain vueTerrain;
    private VueInventaire vueInv;
    private Set<KeyCode> pressedKeys;
    private CreateurVueArme createurVueArme;
    private List<VueCoffre> vueCoffres;

    public KeyHandler(Monde map, VueInventaire vueInv, VueTerrain vueTerrain, CreateurVueArme createurVueArme, List<VueCoffre> vueCoffres) {
        this.map = map;
        this.vueInv = vueInv;
        this.createurVueArme = createurVueArme;
        this.pressedKeys = new HashSet<>();
        this.vueTerrain = vueTerrain;
        this.vueCoffres = vueCoffres;
    }

    Collectible fleche = new Fleche(0, 0);
    BombeCollectible bombeCollectible = new BombeCollectible(100, 100);

    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            this.pressedKeys.add(keyEvent.getCode());
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            this.pressedKeys.remove(keyEvent.getCode());
            if (keyEvent.getCode() == Z)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("up", ""));
            if (keyEvent.getCode() == S)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("down", ""));
            if (keyEvent.getCode() == Q)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("left", ""));
            if (keyEvent.getCode() == D)
                map.getJoueur().setDirection(map.getJoueur().getDeplacement().replace("right", ""));
        }

        String direction = "";

        if (pressedKeys.contains(Z)) {
            direction += "up";
            map.getJoueur().addDirection("up");
        }
        if (pressedKeys.contains(S)) {
            direction += "down";
            map.getJoueur().addDirection("down");
        }
        if (pressedKeys.contains(Q)) {
            direction += "left";
            map.getJoueur().addDirection("left");
        }
        if (pressedKeys.contains(D)) {
            direction += "right";
            map.getJoueur().addDirection("right");
        }
        this.map.getJoueur().setDeplacement(direction);
        if (pressedKeys.contains(F)) {
            for (int i = 0; i < this.vueCoffres.size(); i++) {
                if (this.vueCoffres.get(i).getCoffre().isEstOuvert()) {
                    System.out.println(this.vueCoffres.get(i).getCoffre().getListeItem().size());
                }
            }
        }

        if (pressedKeys.contains(SHIFT))
            vueTerrain.deplaceBloc();

        if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            switch (keyEvent.getCode()) {
                case X: // à retirer, sert uniquement pour les tests
                    this.map.addCollectible(fleche);
                    this.map.addCollectible(bombeCollectible);
                    break;
                case A: // switch arme
                    this.map.getJoueur().getInv().echangerArmes();
                    break;
                case LEFT, RIGHT, UP, DOWN: // tirer
                    if (this.map.getJoueur().PeutDonnerCoup() && map.getJoueur().peutAttaquerArme(map)) {
                        this.map.getJoueur().attaquer(keyEvent, map);
                        VueArme vueArme = createurVueArme.creerVueArme();
                        if (vueArme != null)
                            vueArme.donnerCoup(this.map.getJoueur().getInv().getArmeActuelle().getHitBox().getX(), this.map.getJoueur().getInv().getArmeActuelle().getHitBox().getY(), keyEvent);
                        if (keyEvent.getCode() == UP || keyEvent.getCode() == DOWN)
                            this.map.getJoueur().getInv().getArmeActuelle().getHitBox().pivote();
                    }
                    break;
                case TAB: // inventaires
                    if (this.map.coffreOuvert() == null)
                        this.vueInv.toggleAffichageInterface(keyEvent);
                    break;
                case E: // interagir
                    for (VueCoffre coffre : vueCoffres) {
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