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
            return this.map.get(coo) == 232;
        return false;
    }

    public void setMap(ArrayList<Integer> map) {
        this.map = map;
    }
}
