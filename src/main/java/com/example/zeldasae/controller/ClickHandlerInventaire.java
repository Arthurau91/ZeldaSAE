package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
            Item i = j.getInv().getItemParID(Integer.parseInt(v.getId()));
            i.utiliserItem(j);
        }
    }

}
