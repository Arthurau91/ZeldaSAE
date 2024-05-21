package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        Image img = new Image("file:src/main/resources/com/example/zeldasae/img/test.png");
        ImageView imageView = new ImageView(img);
        imageView.setTranslateX(this.xItem);
        imageView.setTranslateY(this.yItem);
        setxItem(getxItem() + img.getWidth());
        this.boxInv.getChildren().add(imageView);

        if (this.xItem > this.map.getPrefWidth() - 15) {
            setxItem(0);
            setyItem(this.yItem + img.getHeight());
        }
    }



}
