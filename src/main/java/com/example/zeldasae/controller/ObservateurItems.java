package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObservateurItems implements ListChangeListener<Item> {

    private Pane boxInv;
    private Pane map;
    private double xItem;
    private double yItem;

    public ObservateurItems(Pane boxInv, Pane map) {
        this.boxInv = boxInv;
        this.map = map;
        this.xItem = 0;
        this.yItem = 0;
    }

    public double getxItem() {
        return this.xItem;
    }

    public void setxItem(double n) {
        this.xItem = n;
    }

    public double getyItem() {
        return this.yItem;
    }

    public void setyItem(double n) {
        this.yItem = n;
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
        Rectangle r = new Rectangle(15, 15, Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        r.setTranslateX(this.xItem);
        setxItem(getxItem() + 15);
        r.setTranslateY(this.yItem);
        this.boxInv.getChildren().add(r);

        if (this.xItem > this.map.getPrefWidth() - 15) {
            setxItem(0);
            setyItem(this.yItem + 30);
        }
    }



}
