package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObservateurItems implements ListChangeListener<Item> {

    public final int nbItemLigne = 5;
    public final int nbItemColonne = 10;

    private Pane boxInv;
    private Pane map;
    public ObservateurItems(Pane boxInv, Pane map) {
        this.boxInv = boxInv;
        this.map = map;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item i : change.getAddedSubList()) {
                ajouterItem(i);
            }
//            for (Item i : change.getRemoved()) {
//                méthode pour retirer un item côté vue (si besoin)
//            }
        }
    }

    public void ajouterItem(Item i) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/img/test.png");
        ImageView imageView = new ImageView(img);

        int x, y;
        x = (i.getPosSlotItems() - 1)%this.nbItemLigne * ((int) boxInv.getWidth()/this.nbItemLigne);
        y = (i.getPosSlotItems() - 1)/this.nbItemLigne * ((int) boxInv.getHeight()/this.nbItemColonne);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        this.boxInv.getChildren().add(imageView);
    }



}
