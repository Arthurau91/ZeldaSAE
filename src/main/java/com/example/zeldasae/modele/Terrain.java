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

    public int getRows() {
        return rows;
    }

    public boolean testCoo(int coo){
        if (coo < map.size() && coo >=0)
            return vide(coo);
        return false;
    }

    private boolean vide(int coo){
        int[] casesVides = {57, 1152};
        for (int i = 0; i < casesVides.length; i++){
            if (this.map.get(coo) == casesVides[i])
                return true;
        }
        return false;
    }

    public void setMap(ArrayList<Integer> map) {
        this.map = map;
    }
}

