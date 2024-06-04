package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public abstract class Ennemi extends Entite{

    private BFS bfs;

    public Ennemi(int x, int y, int width, int height, int column, int rows ,BFS bfs, Pane paneEntite) {
        super(x, y, width, height, column, rows, paneEntite);
        this.bfs = bfs;
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
                    donneDirection(x, y, pdeplacement);
                    deplacement = super.deplacement(m);
                }
            }
            if(!deplacement && width2 >= 0){
                x = ((this.getX()+width2-1)/30)%(30*this.getColumn());
                y = ((this.getY()+this.getHeight()-1)/30)%(30*this.getRows());
                src = new int[]{x, y};
                pdeplacement = bfs.prochainMouvement(src);
                if (pdeplacement != null) {
                    donneDirection(x, y, pdeplacement);
                    deplacement = super.deplacement(m);
                }
            }
            if (width <= this.getWidth())
                width += 30;
            if (width2 >= 0)
                width2 -= 30;
        }while (width <= this.getWidth() && width2 >= 0);

        this.getVueEntite().changeImage();
        System.out.println(this.getDirection());
        return deplacement;
    }

    private void donneDirection(int x, int y, int[] pdeplacement) {
        String direction = "";
        if (pdeplacement[0] > x)
            direction += "right";
        if (pdeplacement[0] < x)
            direction += "left";
        if (pdeplacement[1] > y)
            direction += "down";
        if (pdeplacement[1] < y)
            direction += "up";
        this.setDirection(direction);
    }

    public boolean checkColisionEntite(Monde m, int x, int y) {
        if (m.getJoueur().getHitBox().contient(x, y)){
            this.attaqueEntite(m.getJoueur());
            return true;
        }
        return super.checkColisionEntite(m, x, y);
    }
}
