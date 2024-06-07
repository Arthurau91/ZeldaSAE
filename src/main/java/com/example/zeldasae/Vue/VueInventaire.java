package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class VueInventaire extends VueInterface{

    public final int nbItemLigne = 5;
    public final int nbItemColonne = 10;
    private ClickHandlerInventaire clickHandler;


    public VueInventaire(Pane boxInv, Joueur joueur) {
        super(boxInv, joueur);
        this.clickHandler = new ClickHandlerInventaire(boxInv, this.joueur);
    }


    public void ajouterItem(Item i) {
        System.out.println("ajout");
        Image img = switchImageItem(i);
        ImageView imageView = new ImageView(img);

        int x, y;
        x = (i.getPosSlotItems() - 1)%this.nbItemLigne * ((int) this.paneInterface.getWidth()/this.nbItemLigne);
        y = (i.getPosSlotItems() - 1)/this.nbItemLigne * ((int) this.paneInterface.getHeight()/this.nbItemColonne) + 100;
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setId("" + i.getPosSlotItems());
        imageView.setOnMouseClicked(this.clickHandler);
        this.paneInterface.getChildren().add(imageView);
    }

    public Image switchImageItem(Item i) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + i.getNom() + ".png");
        return img;
    }
}
