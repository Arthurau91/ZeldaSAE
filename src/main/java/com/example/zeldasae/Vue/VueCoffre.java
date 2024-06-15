package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerCoffre;
import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class VueCoffre extends VueInterface{

    public final int nbItemLigne = 5;
    public final int nbItemColonne = 10;
    private Coffre coffre;
    private VueInventaire vueInventaire;
    private ClickHandlerCoffre clickHandlerCoffre;
    private Pane paneCoffre;

    public VueCoffre(Pane paneCoffre, Joueur joueur, Coffre coffre, VueInventaire vueInventaire) {
        super(paneCoffre, joueur);
        this.coffre = coffre;
        this.vueInventaire = vueInventaire;
        this.paneCoffre = paneCoffre;
        this.clickHandlerCoffre = new ClickHandlerCoffre(this.paneCoffre, this.coffre, vueInventaire);
        this.paneCoffre.toFront();
    }

    public void toggleAffichageInterface(KeyEvent keyEvent) {
        if (!this.afficheInterface && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            affichageInterface(true);
        } else if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
            affichageInterface(false);
        }
    }

    public void affichageInterface(boolean b) {
        this.paneCoffre.setVisible(b);
        this.vueInventaire.paneInterface.setVisible(b);
        setAfficheInterface(b);
        this.vueInventaire.setAfficheInterface(b);
        this.coffre.setEstOuvert(b);
    }

    public Coffre getCoffre() {
        return coffre;
    }

    public void ajouterItem(Item i) {
        Image img = switchImageItem(i);
        ImageView imageView = new ImageView(img);

        int x, y;
        x = (i.getPosSlotItems() - 1) % this.nbItemLigne * ((int) this.paneCoffre.getWidth() / this.nbItemLigne);
        y = (i.getPosSlotItems() - 1) / this.nbItemLigne * ((int) this.paneCoffre.getHeight() / this.nbItemColonne) + 100;
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setId("" + i.getPosSlotItems());
        imageView.setOnMouseClicked(this.clickHandlerCoffre);
        System.out.println("passe");
        this.coffre.ajouterItem(i);
        this.paneCoffre.getChildren().add(imageView);}

    public void retirerItem(Item i) {
        System.out.println("retirer");
        ImageView imageViewToRemove = (ImageView) this.paneCoffre.lookup("#" + i.getPosSlotItems());
        if (imageViewToRemove != null) {
            this.paneCoffre.getChildren().remove(imageViewToRemove);
            this.coffre.retirerItem(i);
        }
    }

    public Image switchImageItem(Item i) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + i.getNom() + ".png");
        return img;
    }
}