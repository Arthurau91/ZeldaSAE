package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;

public class HitBox {

    private int large;
    private int haut;
    private IntegerProperty x;
    private IntegerProperty y;
    private static final int[] dLigne = {0, 1, 0, 0};
    private static final int[] dColonne = {0, 0, 0, 1};
    private static final int[] decalage = {-1, 1, -1, 1};

    public HitBox(int l, int h, IntegerProperty x, IntegerProperty y){
        this.large = l;
        this.haut = h;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y.getValue();
    }
    public int getX() {
        return x.getValue();
    }

    public boolean checkColision(String direction, int rows, Terrain terrain){
        String[] dString = {"left","right","up","down"};
        for (int i = 0; i < dString.length; i++){
            int nx = getX() + (dLigne[i]*large);
            int ny = getY() + (dColonne[i]*haut);
            if (i < 2 && direction.contains(dString[i])){
                nx += decalage[i];
                for (int j = 0; j < large; j++){
                    if(!terrain.vide((nx / 30) + ((ny+j)/ 30 * rows)))
                        return false;
                }
            }
            else if (i > 1 && direction.contains(dString[i])){
                ny += decalage[i];
                for (int j = 0; j < haut; j++){
                    if(!terrain.vide(((nx+j) / 30) + (ny/ 30 * rows)))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean checkBord(String direction, int column, int rows, int vitesse){
        int X = (getX()/large)%(large*column);
        int Y = (getY()/haut)%(haut*rows);
        int nx;
        int ny;
        if (direction.equals("up")){
            if (getY()-vitesse < 0)
                return false;
        }
        if (direction.equals("down")){
            ny = ((getY()-vitesse)/haut)%(haut*rows);
            if (Y == rows-1 && Y != ny)
                return false;
        }
        if (direction.equals("left")){
            if (getX()-vitesse < 0)
                return false;
        }
        if (direction.equals("right")){
            nx = ((getX()-vitesse)/this.large)%(large*column);
            if (X == column-1 && X != nx)
                return false;
        }

        return true;
    }

    public boolean contient(int x, int y){
        return getX() < x && getX()+large > x && getY() < y && getY()+haut > y;
    }
}
