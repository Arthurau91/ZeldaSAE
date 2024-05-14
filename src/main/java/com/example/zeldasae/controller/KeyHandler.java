package com.example.zeldasae.controller;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;
    private TilePane t;

    public KeyHandler(Monde map, TilePane t) {
        this.map = map;
        this.t = t;
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
        }
    }


}