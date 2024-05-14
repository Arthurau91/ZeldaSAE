package com.example.zeldasae.controller;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;

    public KeyHandler(Monde map) {
        this.map = map;
    }


    @Override
    public void handle(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case Z:
                System.out.println("z");
                map.getJoueur().deplacementZQSD('z');
                break;
            case Q:
                System.out.println("q");
                map.getJoueur().deplacementZQSD('q');
                break;
            case S:
                System.out.println("s");
                map.getJoueur().deplacementZQSD('s');
                break;
            case D:
                System.out.println("d");
                map.getJoueur().deplacementZQSD('d');
                break;
        }
    }


}