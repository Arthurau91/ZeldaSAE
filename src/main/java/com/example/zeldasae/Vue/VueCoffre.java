package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class VueCoffre extends VueInterface{

    private ListView<Item> listView;
    private Coffre coffre;
    private VueInventaire vueInventaire;

    public VueCoffre(Pane boxCoffre, Joueur joueur, Coffre coffre, VueInventaire vueInventaire) {
        super(boxCoffre, joueur);
        this.coffre = coffre;
        this.vueInventaire = vueInventaire;

        listView = new ListView<>();
        listView.setPrefSize(300, 300);

        boxCoffre.getChildren().add(listView);

    }

    public void toggleAffichageInterface(KeyEvent keyEvent) {
        if (!this.afficheInterface && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            affichageInterface(true);
        } else if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            affichageInterface(false);
        }
    }

    public void affichageInterface(boolean b) {
        this.paneInterface.setVisible(b);
        this.vueInventaire.paneInterface.setVisible(b);
        setAfficheInterface(b);
        this.vueInventaire.setAfficheInterface(b);
        this.coffre.setEstOuvert(b);
    }

    public Coffre getCoffre() {
        return coffre;
    }

    public VueInventaire getVueInventaire() {
        return vueInventaire;
    }
}