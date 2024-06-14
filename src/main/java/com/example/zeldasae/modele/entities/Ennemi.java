package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Terrain;

public abstract class Ennemi extends Entite {

    private BFS bfs;

    public Ennemi(int x, int y, int width, int height, int column, int rows ,BFS bfs, Monde monde) {
        super(x, y, width, height, column, rows, monde);
        this.bfs = bfs;
    }

    public BFS getBfs() {
        return bfs;
    }

    /**
     * Méthode qui gère le déplacement d'un Ennemi sur la map en se reposant sur la méthode déplacement() d'Entite
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          déplacement() d'Entite
     */
    public boolean deplacement(Monde m) {
        int width = 0;
        int width2 = this.getWidth();
        int x;
        int y;
        int[] src;
        int[] pdeplacement;

        boolean deplacement = false;

        do {
            if (!deplacement && width <= this.getWidth()) {
                x = ((this.getX() + width )/ 30) % (30 * this.getColumn());
                y = (this.getY() / 30) % (30 * this.getRows());
                src = new int[]{x, y};
                pdeplacement = bfs.prochainMouvement(src);
                if (pdeplacement != null) {
                    metDirection(x, y, pdeplacement, m.getTerrain());
                    deplacement = super.deplacement(m);
                }
            }
            if(!deplacement && width2 >= 0){
                x = ((this.getX()+width2-1)/30)%(30*this.getColumn());
                y = ((this.getY()+this.getHeight()-1)/30)%(30*this.getRows());
                src = new int[]{x, y};
                pdeplacement = bfs.prochainMouvement(src);
                if (pdeplacement != null) {
                    metDirection(x, y, pdeplacement, m.getTerrain());
                    deplacement = super.deplacement(m);
                }
            }
            if (width <= this.getWidth())
                width += 30;
            if (width2 >= 0)
                width2 -= 30;
        }while (width <= this.getWidth() && width2 >= 0);

        this.setDirection(this.getDeplacement());
        if (isJoueurDirection(m))
            attaqueEntite(m.getJoueur());
        return deplacement;
    }

    private void metDirection(int x, int y, int[] pdeplacement, Terrain terrain) {
        String direction = "";
        if (pdeplacement[0] > x && !terrain.isBrouillard(terrain.changeCoo(getX()+getWidth(), getY())))
            direction += "right";
        if (pdeplacement[0] < x && !terrain.isBrouillard(terrain.changeCoo(getX(), getY())))
            direction += "left";
        if (pdeplacement[1] > y && !terrain.isBrouillard(terrain.changeCoo(getX(), getY()+getHeight())))
            direction += "down";
        if (pdeplacement[1] < y && !terrain.isBrouillard(terrain.changeCoo(getX(), getY())))
            direction += "up";
        this.setDeplacement(direction);
    }

    private boolean isJoueurDirection(Monde m){

        if(isJoueurUp(m, 1))
            return true;
        if(isJoueurDown(m, 1))
            return true;
        if(isJoueurRight(m, 1))
            return true;
        if(isJoueurLeft(m, 1))
            return true;

        return false;
    }

    public boolean checkColisionEntite(Monde m, int x, int y) {
        if (m.getJoueur().getHitBox().contient(x, y)){
            return true;
        }
        return super.checkColisionEntite(m, x, y);
    }

    private boolean isJoueurUp(Monde m, int decalages){
        for (int i = 0; i <= super.getWidth(); i++){
            if (m.getJoueur().getHitBox().contient(getX() + i, getY() - decalages))
                return true;
        }
        return false;
    }
    private boolean isJoueurDown(Monde m, int decalages){
        for (int i = 0; i <= super.getWidth(); i++){
            if (m.getJoueur().getHitBox().contient(getX() + i, getY() + super.getHeight() + decalages))
                return true;
        }
        return false;
    }
    private boolean isJoueurRight(Monde m, int decalages){
        for (int i = 0; i <= super.getHeight(); i++){
            if (m.getJoueur().getHitBox().contient(getX() + super.getWidth() + decalages, getY() + i))
                return true;
        }
        return false;
    }
    private boolean isJoueurLeft(Monde m, int decalages){
        for (int i = 0; i <= super.getHeight(); i++){
            if (m.getJoueur().getHitBox().contient(getX() - decalages, getY() + i))
                return true;
        }
        return false;
    }
}
