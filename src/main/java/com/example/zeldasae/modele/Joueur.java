package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private String id;

    public Joueur(int x, int y) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = "j1";
    }

    public int getX() {
        return this.xProperty.getValue();
    }

    public void setX(int n) {
        this.xProperty.setValue(n);
    }

    public IntegerProperty xProperty() {
        return xProperty;
    }


    public int getY() {
        return yProperty.getValue();
    }

    public void setY(int y) {
        this.yProperty.setValue(y);
    }


    public IntegerProperty yProperty() {
        return yProperty;
    }

    public String getId() {
        return id;
    }

    public Joueur getJoueur() {
        return this;
    }




    public void deplacementZQSD(char touche) {
        switch (touche) {
            case 'z':
                this.setY(this.getY()-30);
                System.out.println("Déplacement en Z effectué");
                break;
            case 'q':
                this.setX(this.getX()-30);
                System.out.println("Déplacement en Q effectué");
                break;
            case 's':
                this.setY(this.getY()+30);
                System.out.println("Déplacement en S effectué");
                break;
            case 'd':
                this.setX(this.getX()+30);
                System.out.println("Déplacement en D effectué");
                break;
        }
    }
}
