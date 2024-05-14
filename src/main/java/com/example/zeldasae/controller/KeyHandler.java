package com.example.zeldasae.controller;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;

    public KeyHandler(Monde map) {
        this.map = map;
    }


    @Override
    public void handle(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case Z:
//                System.out.println("z");
                map.getJoueur().deplacement('z', this.map);
                break;
            case Q:
//                System.out.println("q");
                map.getJoueur().deplacement('q', this.map);
                break;
            case S:
//                System.out.println("s");
                map.getJoueur().deplacement('s', this.map);
                break;
            case D:
//                System.out.println("d");
                map.getJoueur().deplacement('d', this.map);
                break;
        }
    }


}