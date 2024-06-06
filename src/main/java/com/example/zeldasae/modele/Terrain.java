package com.example.zeldasae.modele;

import java.util.ArrayList;

public class Terrain {

    private ArrayList<Integer> map;

    public Terrain(){
        this.map = new ArrayList<>();
    }

    public ArrayList<Integer> getMap() {
        return map;
    }

    public boolean poussable(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesPoussables = {1118};
            for (int i = 0; i < casesPoussables.length; i++) {
                if (this.map.get(coo) == casesPoussables[i])
                    return true;
            }
        }
        return false;
    }

    public boolean vide(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesVides = {0};
            for (int i = 0; i < casesVides.length; i++) {
                if (this.map.get(coo) == casesVides[i])
                    return true;
            }
        }
        return false;
    }

    public void setCoo(int coo, int valeur){
        if (coo < map.size() && coo >=0) {
            this.map.set(coo, valeur);
        }
    }

    public void setMap(ArrayList<Integer> map) {
        this.map = map;
    }
}
