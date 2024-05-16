package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObservateurItems implements ListChangeListener<Item> {

    public HBox boxInv;

    public ObservateurItems(HBox boxInv) {
        this.boxInv = boxInv;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item i : change.getAddedSubList()) {
                ajouterItem(i);
            }
            for (Item i : change.getRemoved()) {
                //méthode pour retirer un item côté vue
            }
        }
    }

    public void ajouterItem(Item i) {
        Rectangle r = new Rectangle(15, 15, Color.rgb((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255)));
        boxInv.getChildren().add(r);
        System.out.println("ça ajoute");
        //r.setId()
    }
}
