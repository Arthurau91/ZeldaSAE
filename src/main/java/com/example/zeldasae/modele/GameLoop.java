package com.example.zeldasae.modele;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameLoop extends AnimationTimer {

    private long derniereFrame;
    private long temps;
    private Monde monde;
    private IntegerProperty FPS;

    public GameLoop(Monde monde){
        this.monde = monde;
        this.FPS = new SimpleIntegerProperty();
    }

    public IntegerProperty FPSProperty() {
        return FPS;
    }

    @Override
    public void handle(long l) {
        this.temps = l - this.derniereFrame;
        this.derniereFrame = l;
//        System.out.println(this.temps);
//        System.out.println(getFrameRateHertz());
        this.FPS.setValue(getFrameRateHertz());
        this.monde.deplacementEnnemi();
    }

    public double getFrameRateHertz() {
        double frameRate = 1d / this.temps;
        return frameRate * 1e9;
    }
}
