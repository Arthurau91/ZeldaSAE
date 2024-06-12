package com.example.zeldasae.Vue;

import com.example.zeldasae.controller.ClickHandlerInventaire;
import com.example.zeldasae.modele.Collectible;
import com.example.zeldasae.modele.Item;
import com.example.zeldasae.modele.Joueur;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VueInventaire extends VueInterface{

        public final int nbItemLigne = 5;
        public final int nbItemColonne = 8;
        private boolean afficheInventaire;
        private ClickHandlerInventaire clickHandler;


        public VueInventaire(Pane boxInv, Joueur joueur) {
            super(boxInv, joueur);
            this.clickHandler = new ClickHandlerInventaire(paneInterface, joueur);
            creerLabelInventaire();
        }

        public void toggleAffichageInventaire(KeyEvent keyEvent) {
            if (!this.afficheInventaire && keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
                this.paneInterface.setVisible(true);
                setAfficheInventaire(true);
            } else if (keyEvent.getEventType() != KeyEvent.KEY_RELEASED) {
                this.paneInterface.setVisible(false);
                setAfficheInventaire(false);
            }
        }

        public void setAfficheInventaire(boolean b) {
            this.afficheInventaire = b;
        }

        public void ajouterItemVue(Item i) {
            Image img = switchImageItem(i);
            ImageView imageView = new ImageView(img);

            int x, y;
            x = (i.getPosSlotItems() - 1) % this.nbItemLigne * ((int) this.paneInterface.getWidth() / this.nbItemLigne);
            y = (i.getPosSlotItems() - 1) / this.nbItemLigne * ((int) this.paneInterface.getHeight() / this.nbItemColonne) + 100;
            imageView.setTranslateX(x);
            imageView.setTranslateY(y);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setId("" + i.getPosSlotItems());
            imageView.setOnMouseClicked(this.clickHandler);
//            this.paneInterface.getChildren().add(imageView);

            if (i instanceof Collectible) {
                Label l = new Label();
                l.setTextFill(Color.WHITE);
                ((Collectible) i).quantiteProperty().addListener((obs, ol, nouv) -> l.setText(nouv.toString()));
                l.setTranslateX(x + 50);
                l.setTranslateY(y + 50);
                paneInterface.getChildren().add(l);
            }
            this.paneInterface.getChildren().add(imageView);
        }

        public Image switchImageItem(Item i) {
            if (i instanceof Collectible) {
                return new Image("file:src/main/resources/com/example/zeldasae/assets/" + ((Collectible) i).getType() + ".png");
            } else {
                return new Image("file:src/main/resources/com/example/zeldasae/assets/" + i.getNom() + ".png");
            }
        }

        public void creerLabelInventaire() {
            Label l = new Label("Inventaire");
            l.setTextFill(Color.WHITE);
            l.setFont(Font.font("Calibri", 69));
            l.setLayoutX(300);
            this.paneInterface.getChildren().add(l);
        }

    }
