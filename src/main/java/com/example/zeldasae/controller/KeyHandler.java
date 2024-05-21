package com.example.zeldasae.controller;
import com.example.zeldasae.modele.Armure;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;
    private TilePane t;
    private VueInventaire v;
    private Item itemTest = new Armure(0,0, 500,"Item", 1); //à retirer, sert uniquement pour les tests
    private Item itemTest2 = new Armure(0,0, 500,"Item", 5);
    private Item itemTest3 = new Armure(0,0, 500,"Item", 6);
    private Item itemTest4 = new Armure(0,0, 500,"Item", 19);

    public KeyHandler(Monde map, TilePane t, VueInventaire v) {
        this.map = map;
        this.t = t;
        this.v = v;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case Z:
                System.out.println("z");
                map.getJoueur().deplacementZQSD('z', this.t, this.map);
                break;
            case Q:
                System.out.println("q");
                map.getJoueur().deplacementZQSD('q', this.t, this.map);
                break;
            case S:
                System.out.println("s");
                map.getJoueur().deplacementZQSD('s', this.t, this.map);
                break;
            case D:
                System.out.println("d");
                map.getJoueur().deplacementZQSD('d', this.t, this.map);
                break;
            case E:
                System.out.println("e");
                this.v.toggleAffichageInventaire();
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
    }

}