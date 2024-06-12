package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;

public class Kami extends Ennemi {
    private static final int DISTANCE = 100;

    public Kami(int x, int y, int width, int height, int column, int rows, BFS bfs, Monde monde) {
        super(x, y, width, height, column, rows, bfs, monde);
        super.setVitesse(8);

    }

    @Override
    public boolean deplacement(Monde m) {

        int x = this.getX();
        int y = this.getY();

        int joueurX = this.monde.getJoueur().getX();
        int joueurY = this.monde.getJoueur().getY();

        double distanceAuJoueur = Math.sqrt(Math.pow(x - joueurX, 2) + Math.pow(y - joueurY, 2));

        if (distanceAuJoueur < DISTANCE) {
            exploser();
            return false;
        } else {

            return super.deplacement(m);
        }
    }

    private void exploser() {
        System.out.println("Kami explose !");
        this.setPv(-1);
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }
}