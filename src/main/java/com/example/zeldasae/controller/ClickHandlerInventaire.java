package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Armure;
import com.example.zeldasae.modele.Inventaire;
import com.example.zeldasae.modele.Joueur;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.awt.*;

public class ClickHandlerInventaire implements EventHandler<MouseEvent> {

    private Pane boxInv;
    private Joueur j;

    public ClickHandlerInventaire(Pane boxInv, Joueur j) {
        this.boxInv = boxInv;
        this.j = j;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView v) {
            clicChangementArme(v.getId());
        }

    }

    public void clicChangementArme(String id) {
        System.out.println(j.getInv().getItemParID(Integer.parseInt(id)).getNom());
        if (j.getInv().getItemParID(Integer.parseInt(id)) instanceof Arme a) {
            this.j.getInv().changerArme(a);
        }
        else if (j.getInv().getItemParID(Integer.parseInt(id)) instanceof Armure armure) {
            this.j.getInv().changerArmure(armure);
        }
    }
}
