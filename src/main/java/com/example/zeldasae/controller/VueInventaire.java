package com.example.zeldasae.controller;

import com.example.zeldasae.modele.Inventaire;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VueInventaire {

    public final int nbItemLigne = 5;
    public final int nbItemColonne = 10;

    private Pane boxInventaire;
    private boolean afficheInventaire;
    private Joueur joueur;
    private ClickHandlerInventaire clickHandler;

    public VueInventaire(Pane boxInv, Joueur joueur) {
        this.boxInventaire = boxInv;
        this.afficheInventaire = false;
        this.joueur = joueur;
        this.clickHandler = new ClickHandlerInventaire(boxInv, this.joueur);
    }

    public void toggleAffichageInventaire(KeyEvent keyEvent) {
        if (!this.afficheInventaire && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.boxInventaire.setVisible(true);
            setAfficheInventaire(true);
        }
        else if(keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.boxInventaire.setVisible(false);
            setAfficheInventaire(false);
        }
    }

    public void setAfficheInventaire(boolean b) {
        this.afficheInventaire = b;
    }

    public void ajouterItem(Item i) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/test.png");
        ImageView imageView = new ImageView(img);

        int x, y;
        x = (i.getPosSlotItems() - 1)%this.nbItemLigne * ((int) this.boxInventaire.getWidth()/this.nbItemLigne);
        y = (i.getPosSlotItems() - 1)/this.nbItemLigne * ((int) this.boxInventaire.getHeight()/this.nbItemColonne);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setId("" + i.getPosSlotItems());
        imageView.setOnMouseClicked(this.clickHandler);
        this.boxInventaire.getChildren().add(imageView);
    }


}
