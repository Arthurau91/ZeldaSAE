package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> listeItems;
    private Armure armureActuelle;
    private Arme armeActuelle;
    private Arme armeSecondaire;

    public Inventaire() {
        listeItems = FXCollections.observableArrayList();
    }

    public void ajouterItem(Item i) {
        this.listeItems.add(i);
    }

    public void changerArme(Arme a) {
        if (this.armeActuelle != null) {
            this.armeSecondaire = armeActuelle;
        }
        this.armeActuelle = a;
    }

    public void changerArmure(Armure a) {
        this.armureActuelle = a;
    }

    public void echangerArmes() {
        if (armeActuelle != null && armeSecondaire != null) {
            Arme pivot = armeActuelle;
            armeActuelle = armeSecondaire;
            armeSecondaire = pivot;
        }
    }

    public ObservableList<Item> getListeItems() {
        return this.listeItems;
    }

    public Item getItemParID(int posItem) {
        for (Item i : listeItems) {
            if (i.getPosSlotItems() == posItem) {
                return i;
            }
        }
        return null;
    }

    public Arme getArmeActuelle() {
        return this.armeActuelle;
    }

    public Armure getArmureActuelle() {
        return this.armureActuelle;
    }

    public void ajouterCollectible(Collectible c) {
        Collectible c1;
        boolean trouve = false;
        for(int i = 0; i < this.listeItems.size(); i++) {
            if (this.listeItems.get(i) instanceof Collectible) {
                c1 = (Collectible) this.listeItems.get(i);
                if(c1.getType().equals(c.getType())) {
                    c1.ajouter(1);
                    trouve = true;
                    System.out.println("ajout quantite");
                }
            }
        }
        if (!trouve) {
            this.listeItems.add(c);
            c.ajouter(1);
            System.out.println("ajout liste");
        }
    }
}
