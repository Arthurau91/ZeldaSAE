package com.example.zeldasae.controller;
import com.example.zeldasae.controller.Controller;
import com.example.zeldasae.modele.Monde;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class KeyHandler implements EventHandler<KeyEvent> {

    private Monde map;

    public KeyHandler(Monde map) {
        this.map = map;
    }


    @Override
    public void handle(KeyEvent keyEvent) {

        switch (keyEvent.getText()) {
            case "z", "Z":
                System.out.println("z");
                map.getJoueur().deplacementZQSD('z');
                break;
            case "q", "Q":
                System.out.println("q");
                map.getJoueur().deplacementZQSD('q');
                break;
            case "s", "S":
                System.out.println("s");
                map.getJoueur().deplacementZQSD('s');
                break;
            case "d", "D":
                System.out.println("d");
                map.getJoueur().deplacementZQSD('d');
                break;
        }
    }


}