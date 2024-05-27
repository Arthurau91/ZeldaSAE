package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
        this.boxInventaire.setStyle("-fx-background-color: black;");
        this.boxInventaire.setVisible(false);
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
        Image img = switchImageItem(i);
        ImageView imageView = new ImageView(img);

        int x, y;
        x = (i.getPosSlotItems() - 1)%this.nbItemLigne * ((int) this.boxInventaire.getWidth()/this.nbItemLigne);
        y = (i.getPosSlotItems() - 1)/this.nbItemLigne * ((int) this.boxInventaire.getHeight()/this.nbItemColonne) + 100;
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setId("" + i.getPosSlotItems());
        imageView.setOnMouseClicked(this.clickHandler);
        this.boxInventaire.getChildren().add(imageView);
    }

    public Image switchImageItem(Item i) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + i.getNom() + ".png");
        return img;
    }

}