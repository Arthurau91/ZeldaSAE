package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.Vue.VueBoss;
import javafx.scene.layout.Pane;

public class Boss extends Ennemi{
    public Boss(int x, int y, int width, int height, int column, int rows, BFS bfs, Pane paneEntite) {
        super(x, y, width, height, column, rows, bfs, paneEntite);
        super.setVueEntite(new VueBoss(this, paneEntite));
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
