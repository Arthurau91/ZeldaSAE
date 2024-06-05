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
    public boolean testCoo(int coo){
        if (coo < map.size() && coo >=0)
            return vide(coo);
        return false;
    }

    private boolean vide(int coo){
        int[] casesVides = {114};
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
