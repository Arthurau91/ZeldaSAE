package com.example.zeldasae.modele;

import java.util.ArrayList;

public class Terrain {

    private ArrayList<Integer> map;
    private int rows;

    public Terrain(int rows){
        this.map = new ArrayList<>();
        this.rows = rows;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }

    public int changeCoo(int x, int y){
        return (x/30) + ((y/30)*rows);
    }

    public boolean poussable(int coo) {
        if (coo < map.size() && coo >= 0) {
            int[] casesPoussables = {1118};
            for (int i = 0; i < casesPoussables.length; i++) {
                if (this.map.get(coo) == casesPoussables[i])
                    return true;
            }
        }
        return false;
    }

    public int getRows() {
        return rows;
    }

    public boolean vide(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesVides = {0, 1205, 686, 687, 631, 632, 688, 630, 683, 625, 245, 246, 247, 601, 604};
            for (int i = 0; i < casesVides.length; i++) {
                if (this.map.get(coo) == casesVides[i])
                    return true;
            }
        }
        return false;
    }

    public boolean isBrouillard(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesVides = {1205};
            for (int i = 0; i < casesVides.length; i++) {
                if (this.map.get(coo) == casesVides[i])
                    return true;
            }
        }
        return false;
    }

    public boolean destructible(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesDestructibles = {514, 1079};
            for (int i = 0; i < casesDestructibles.length; i++) {
                if (this.map.get(coo) == casesDestructibles[i])
                    return true;
            }
        }
        return false;
    }

    public boolean isBuisson(int coo){
        return this.map.get(coo) == 1079;
    }

    public boolean isCactus(int coo){
        if (coo < map.size() && coo >=0)
            return this.map.get(coo) == 403;
        return false;
    }

    public void setCoo(int coo, int valeur){
        if (coo < map.size() && coo >=0)
            this.map.set(coo, valeur);

    }

    public void setMap(ArrayList<Integer> map) {
        this.map = map;
    }

}