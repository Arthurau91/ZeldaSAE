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

//    public void keyPressed(KeyEvent e) {
//        int code = e.;
//
//        if (code == KeyEvent.VK_Z){
//            upPressed = true;
//        }
//        if (code == KeyEvent.VK_S){
//            downPressed = true;
//        }
//        if (code == KeyEvent.VK_Q){
//            leftPressed = true;
//        }
//        if (code == KeyEvent.VK_D){
//            rightPressed = true;
//        }
//    }
//
//    public void keyReleased(KeyEvent e) {
//
//        int code = e.getKeyCode();
//
//        if (code == KeyEvent.VK_Z){
//            upPressed = false;
//        }
//        if (code == KeyEvent.VK_S){
//            downPressed = false;
//        }
//        if (code == KeyEvent.VK_Q){
//            leftPressed = false;
//        }
//        if (code == KeyEvent.VK_D){
//            rightPressed = false;
//        }
//
//    }

    @Override
    public void handle(KeyEvent keyEvent) {

        System.out.println("ici");
        switch (keyEvent.getText()) {
            case "z", "Z":
                map.getJoueur().deplacementZQSD('z');
                break;
            case "q", "Q":
                map.getJoueur().deplacementZQSD('q');
                break;
            case "s", "S":
                map.getJoueur().deplacementZQSD('s');
                break;
            case "d", "D":
                map.getJoueur().deplacementZQSD('d');
                break;
        }
    }


}