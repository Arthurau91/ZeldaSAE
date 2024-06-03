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
    private int PrefColumns;
    private int PrefRows;


    public LoadJSON(String file){
        this.map = loadMap(file);
    }

    public ArrayList<Integer> loadMap(String filename) {
        ArrayList<Integer> elementsMap = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray layersArray = (JSONArray) jsonObject.get("layers");
            JSONObject firstLayer = (JSONObject) layersArray.get(0);
            JSONArray dataArray = (JSONArray) firstLayer.get("data");
            this.PrefColumns = (int) (long) firstLayer.get("height");
            this.PrefRows = (int) (long) firstLayer.get("width");

            for (Object data : dataArray) {
                elementsMap.add(Math.toIntExact((Long) data));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return elementsMap;
    }

    public ArrayList<Integer> getMap() {
        return map;
    }
    public int getPrefColumns() {
        return PrefColumns;
    }
    public int getPrefRows() {
        return PrefRows;
    }
}
