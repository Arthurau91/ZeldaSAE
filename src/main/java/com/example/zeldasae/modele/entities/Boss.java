package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;

public class Boss extends Ennemi{
    public Boss(int x, int y, int width, int height, int column, int rows, BFS bfs, Monde monde) {
        super(x, y, width, height, column, rows, bfs, monde);
        super.setVitesse(5);
    }

    @Override
    public boolean deplacement(Monde m) {
        int x = (this.getX()/ 30) % (30 * this.getColumn());
        int y = (this.getY() / 30) % (30 * this.getRows());
        if (this.getBfs().distanceMouvement(new int[]{x, y}) < 15)
            return super.deplacement(m);
        return false;
    }
}
