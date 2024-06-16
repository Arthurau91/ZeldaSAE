package com.example.zeldasae.modele;

import java.util.ArrayList;

public class Terrain {

    private ArrayList<Integer> map;
    private int rows;
    private int columns;

    public Terrain(int rows, int columns){
        this.map = new ArrayList<>();
        this.rows = rows;
        this.columns = columns;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }
    public void setMap(ArrayList<Integer> map) {
        this.map = map;
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    public int changeCoo(int x, int y){
        return (x/30) + ((y/30)*rows);
    }

    public boolean poussable(int coo) {
        if (coo < map.size() && coo >= 0) {
            int[] casesPoussables = {1118};
            for (int casePoussable : casesPoussables) {
                if (this.map.get(coo) == casePoussable)
                    return true;
            }
        }
        return false;
    }

    public boolean vide(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesVides = {0, 1205, 686, 687, 631, 632, 688, 630, 683, 625, 245, 246, 247, 601, 604};
            for (int caseVide : casesVides) {
                if (this.map.get(coo) == caseVide)
                    return true;
            }
        }
        return false;
    }

    public boolean isBrouillard(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesBrouillard = {1205};
            for (int caseBrouillard : casesBrouillard) {
                if (this.map.get(coo) == caseBrouillard)
                    return true;
            }
        }
        return false;
    }

    public boolean destructible(int coo){
        if (coo < map.size() && coo >=0) {
            int[] casesDestructibles = {514, 1079};
            for (int caseDestructible : casesDestructibles) {
                if (this.map.get(coo) == caseDestructible)
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

}