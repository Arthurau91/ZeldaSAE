package com.example.zeldasae.modele;

import com.example.zeldasae.controller.Controller;
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

    private boolean deplacementPossible(double x, double y) {
        return x >= 0 && x < Controller.WIDTH && y >= 0 && y < Controller.HEIGHT;
    }




    public void deplacementZQSD(char touche) {
        switch (touche) {
            case 'z':
                if (deplacementPossible(getX(), getY() - 30)) {
                    setY(getY() - 30);
                    System.out.println("Déplacement en Q effectué");
                } else {
                    System.out.println("Déplacement bloqué !");
                }
                break;
            case 'q':
                if (deplacementPossible(getX() - 30, getY())) {
                    setX(getX() - 30);
                    System.out.println("Déplacement en Q effectué");
                } else {
                    System.out.println("Déplacement bloqué !");
                }

                break;
            case 's':
                if (deplacementPossible(getX(), getY() + 30)) {
                    setY(getY() + 30);
                    System.out.println("Déplacement en S effectué");
                } else {
                    System.out.println("Déplacement bloqué !");
                }
                break;
            case 'd':
                if (deplacementPossible(getX() + 30, getY())) {
                    setX(getX() + 30);
                    System.out.println("Déplacement en D effectué");
                } else {
                    System.out.println("Déplacement bloqué !");
                }
                break;
        }
    }
}
