package com.example.zeldasae.modele.entities;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.ProjectileEnnemi;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Sentinelle extends Ennemi{

    private boolean peutAttaquer;

    public Sentinelle(int x, int y, int column, int rows, BFS bfs) {
        super(x, y, 30, 30, column, rows, 5, bfs);
        super.setVitesse(0);
        peutAttaquer = true;
    }

    @Override
    public boolean deplacement(Monde m) {
        int x = (this.getX()/ 30) % (30 * this.getColumn());
        int y = (this.getY() / 30) % (30 * this.getRows());
        if (this.getBfs().distanceMouvement(new int[]{x, y}) < 15 && peutAttaquer)
            attaqueDistance(m);
        return super.deplacement(m);
    }

    private void attaqueDistance(Monde m){
        ProjectileEnnemi p = new ProjectileEnnemi(2, 20, 30, 30, "Fleche");

        String direction = changeDirection();
        p.setDirection(direction);
        p.setPosMap(this.getX(), this.getY(), direction);
        m.addProjectile(p);
        this.peutAttaquer = false;
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> this.peutAttaquer = true);
        pause.play();

    }

    private String changeDirection(){
        if (this.getDirection().equals("up"))
            return "UP";
        if (this.getDirection().equals("down"))
            return "DOWN";
        if (this.getDirection().equals("right"))
            return "RIGHT";
        if (this.getDirection().equals("left"))
            return "LEFT";
        return "";
    }
}
