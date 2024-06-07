package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerCoffre;
import com.example.zeldasae.modele.Coffre;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
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

    public VueCoffre(Pane boxCoffre, Joueur joueur, Coffre coffre, VueInventaire vueInventaire) {
        super(boxCoffre, joueur);
        this.coffre = coffre;
        this.vueInventaire = vueInventaire;
        this.clickHandlerCoffre = new ClickHandlerCoffre(boxCoffre, this.coffre, vueInventaire);

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

    public void ajouterItem(Item i) {
        Image img = switchImageItem(i);
        ImageView imageView = new ImageView(img);

        int x, y;
        x = (i.getPosSlotItems() - 1)%this.nbItemLigne * ((int) this.paneInterface.getWidth()/this.nbItemLigne);
        y = (i.getPosSlotItems() - 1)/this.nbItemLigne * ((int) this.paneInterface.getHeight()/this.nbItemColonne) + 100;
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setId("" + i.getPosSlotItems());
        imageView.setOnMouseClicked(this.clickHandlerCoffre);
        this.coffre.ajouterItem(i);
        this.paneInterface.getChildren().add(imageView);
    }

    public void retirerItem(Item i) {
        ImageView imageViewToRemove = (ImageView) this.paneInterface.lookup("#" + i.getPosSlotItems());
        if (imageViewToRemove != null) {
            this.paneInterface.getChildren().remove(imageViewToRemove);
            this.coffre.retirerItem(i);
        } else {
            System.out.println("Item non trouvé : " + i.getPosSlotItems());
        }
    }

    public Image switchImageItem(Item i) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + i.getNom() + ".png");
        return img;
    }

    public VueInventaire getVueInventaire() {
        return vueInventaire;
    }
}