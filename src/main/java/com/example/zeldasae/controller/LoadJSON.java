package com.example.zeldasae.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadJSON {

    private ArrayList<Integer> map;
    private ArrayList<Integer> map2;
    private int PrefColumns;
    private int PrefRows;

    public LoadJSON(String file){
        loadMap(file);
    }

    public void loadMap(String filename) {
        ArrayList<Integer> elementsMapSol = new ArrayList<>();
        ArrayList<Integer> elementsMapColision = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray layersArray = (JSONArray) jsonObject.get("layers");
            JSONObject firstLayer = (JSONObject) layersArray.get(0);
            JSONObject secondlayer = (JSONObject) layersArray.get(1);
            JSONArray dataArray = (JSONArray) firstLayer.get("data");
            JSONArray dataArray2 = (JSONArray) secondlayer.get("data");
            this.PrefColumns = (int) (long) firstLayer.get("height");
            this.PrefRows = (int) (long) firstLayer.get("width");

            for (Object data : dataArray) {
                elementsMapSol.add(Math.toIntExact((Long) data));
            }
            for (Object data : dataArray2) {
                elementsMapColision.add(Math.toIntExact((Long) data));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        this.map = elementsMapColision;
        this.map2 = elementsMapSol;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }
    public ArrayList<Integer> getMap2() {
        return map2;
    }
    public int getPrefColumns() {
        return PrefColumns;
    }
    public int getPrefRows() {
        return PrefRows;
    }
}
