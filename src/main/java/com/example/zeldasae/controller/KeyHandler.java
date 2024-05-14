package com.example.zeldasae.controller;
import com.example.zeldasae.controller.Controller;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.util.Objects;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;
    private TilePane t;

    public KeyHandler(Monde map, TilePane t) {
        this.map = map;
        this.t = t;
    }


    @Override
    public void handle(KeyEvent keyEvent) {

        switch (keyEvent.getText()) {
            case "z", "Z":
                System.out.println("z");
                map.getJoueur().deplacementZQSD('z', this.t, this.map);
                break;
            case "q", "Q":
                System.out.println("q");
                map.getJoueur().deplacementZQSD('q', this.t, this.map);
                break;
            case "s", "S":
                System.out.println("s");
                map.getJoueur().deplacementZQSD('s', this.t, this.map);
                break;
            case "d", "D":
                System.out.println("d");
                map.getJoueur().deplacementZQSD('d', this.t, this.map);
                break;
        }
    }


}