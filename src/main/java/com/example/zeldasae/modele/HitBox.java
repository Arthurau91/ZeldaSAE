package com.example.zeldasae.modele;

public class HitBox {

    private int large;
    private int haut;
    private static final int[] dLigne = {0, 1, 0, 0};
    private static final int[] dColonne = {0, 0, 0, 1};
    private static final int[] decalage = {-1, 1, -1, 1};

    public HitBox(int l, int h){
        this.large = l;
        this.haut = h;
    }

    public boolean checkColision(int xj, int yj, String direction, int rows, Terrain terrain){
        String[] dString = {"left","right","up","down"};
        for (int i = 0; i < dString.length; i++){
            int nx = xj + (dLigne[i]*large);
            int ny = yj + (dColonne[i]*haut);
            if (i < 2 && direction.contains(dString[i])){
                nx += decalage[i];
                for (int j = 0; j < large; j++){
                    if(!terrain.testCoo((nx / large) + ((ny+j)/ haut * rows)))
                        return false;
                }
            }
            else if (i > 1 && direction.contains(dString[i])){
                ny += decalage[i];
                for (int j = 0; j < haut; j++){
                    if(!terrain.testCoo(((nx+j) / large) + (ny/ haut * rows)))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean checkBord(String direction, int X, int Y, int column, int rows, int vitesse){
        int x = (X/large)%(large*column);
        int y = (Y/haut)%(haut*rows);
        int nx;
        int ny;
        if (direction.equals("up")){
            if (Y-vitesse < 0) {
                return false;
            }
        }
        if (direction.equals("down")){
            ny = ((Y-vitesse)/haut)%(haut*rows);
            if (y == rows-1 && y != ny)
                return false;
        }
        if (direction.equals("left")){
            if (X-vitesse < 0)
                return false;
        }
        if (direction.equals("right")){
            nx = ((X-vitesse)/this.large)%(large*column);
            if (x == column-1 && x != nx)
                return false;
        }

        return true;
    }
}
