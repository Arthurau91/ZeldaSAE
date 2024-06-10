package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.Vue.VueBoss;
import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Boss extends Ennemi {

    private final int cooldownAttaqueDistance = 2;
    private boolean peutAttaquerDistance;

    public Boss(int x, int y, int width, int height, int column, int rows, BFS bfs) {
        super(x, y, width, height, column, rows, bfs);
        super.setVitesse(5);
        this.peutAttaquerDistance = true;
    }

    @Override
    public boolean deplacement(Monde m) {
        int x = (this.getX()/ 30) % (30 * this.getColumn());
        int y = (this.getY() / 30) % (30 * this.getRows());
        if (this.getBfs().distanceMouvement(new int[]{x, y}) < 15) {
            return super.deplacement(m);
        }
        if (this.peutAttaquerDistance) {
            attaquerDistance(m);
        }
        return false;
    }

    public void attaquerDistance(Monde m) {
        ProjectileEnnemi p = new ProjectileEnnemi(2, 5, 30, 30);
        p.setDirection("RIGHT");
        p.setPosMap(this.getX(), this.getY(), "RIGHT");
        m.ajouterProjectile(p);
        this.peutAttaquerDistance = false;

        PauseTransition pause = new PauseTransition(Duration.seconds(this.cooldownAttaqueDistance));
        pause.setOnFinished(event -> this.peutAttaquerDistance = true);
        pause.play();

    }
}
