package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {

    private IntegerProperty xProperty = new SimpleIntegerProperty();
    private IntegerProperty yProperty = new SimpleIntegerProperty();
    private String id;

    public Joueur(int x, int y) {
        this.xProperty.setValue(x);
        this.yProperty.setValue(y);
//        this.id = "j1";
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



    public IntegerProperty yProperty() {
        return yProperty;
    }

    public String getId() {
        return id;
    }
}
