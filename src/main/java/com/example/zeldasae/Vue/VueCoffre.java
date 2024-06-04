package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class VueCoffre {

    private Pane boxCoffre;
    private Joueur joueur;
    private boolean afficheCoffre;
    private ClickHandlerInventaire clickHandler;
    private ListView<Item> listView;

    public VueCoffre(Pane boxCoffre, Joueur joueur, Coffre coffre) {
        this.boxCoffre = boxCoffre;
        this.boxCoffre.setStyle("-fx-background-color: black;");
        this.boxCoffre.setVisible(false);
        this.afficheCoffre = false;
        this.joueur = joueur;
        this.clickHandler = new ClickHandlerInventaire(boxCoffre, this.joueur);

        listView = new ListView<>(coffre.getListeItem());
        boxCoffre.getChildren().add(listView);
    }

    public void toggleAffichageCoffre(KeyEvent keyEvent) {
        if (!this.afficheCoffre && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.boxCoffre.setVisible(true);
            setAfficheCoffre(true);
        } else if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            this.boxCoffre.setVisible(false);
            setAfficheCoffre(false);
        }
    }

    public void setAfficheCoffre(boolean b) {
        this.afficheCoffre = b;
    }
}