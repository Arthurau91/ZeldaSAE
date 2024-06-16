package com.example.zeldasae.Vue.VueArmes;

import com.example.zeldasae.Vue.VueTerrain;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class VueArme {

    private Joueur joueur;
    private Pane paneEntites;
    private Monde map;
    private Pane mapPane;
    protected VueTerrain vueTerrain;

    public VueArme (Joueur joueur, Pane paneEntites, Monde map, Pane mapPane, VueTerrain vueTerrain) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
        this.map = map;
        this.mapPane = mapPane;
        this.vueTerrain = vueTerrain;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Pane getPaneEntites() {
        return paneEntites;
    }

    public void donnerCoup(int x, int y, KeyEvent keyEvent) {
        ImageView imageView = switchImageCoup(x, y, keyEvent);
        paneEntites.getChildren().add(imageView);

        PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation() - 0.3));
        pause.setOnFinished(event -> {
            paneEntites.getChildren().remove(imageView);
            String directionBloc = "";
            switch (keyEvent.getCode()) {
                case LEFT:
                    directionBloc = "left";
                    break;
                case RIGHT:
                    directionBloc = "right";
                    break;
                case UP:
                    directionBloc = "up";
                    break;
                case DOWN:
                    directionBloc = "down";
                    break;
            }
            vueTerrain.detruitBloc(map.getJoueur().getX(), map.getJoueur().getY(), directionBloc);
        });
        pause.play();

//        Rectangle hitboxTest = new Rectangle(joueur.getInv().getArmeActuelle().getHitBox().getLarge(), joueur.getInv().getArmeActuelle().getHitBox().getHaut(), Color.RED);
//        hitboxTest.setTranslateX(x);
//        hitboxTest.setTranslateY(y);
//        paneEntites.getChildren().add(hitboxTest);
//
//        PauseTransition pauseHitbox = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation() - 0.3));
//        pauseHitbox.setOnFinished(event -> paneEntites.getChildren().remove(hitboxTest));
//        pauseHitbox.play();
    }

    public ImageView switchImageCoup(int x, int y, KeyEvent keyEvent) {
        Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + joueur.getInv().getArmeActuelle().getNom() + "Attaque.png");
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(joueur.getInv().getArmeActuelle().getHitBox().getLarge());
        imageView.setFitHeight(joueur.getInv().getArmeActuelle().getHitBox().getHaut());

        switch (keyEvent.getCode()) {
            case RIGHT:
                imageView.setTranslateX(x);
                imageView.setTranslateY(y + 3);
                imageView.setRotate(35);
                break;
            case LEFT:
                imageView.setTranslateX(x);
                imageView.setTranslateY(y + 3);
                imageView.setRotate(215);
                break;
            case UP:
                imageView.setTranslateX(x - 10);
                imageView.setTranslateY(y);
                imageView.setRotate(-40);
                break;
            case DOWN:
                imageView.setTranslateX(x - 10);
                imageView.setTranslateY(y);
                imageView.setRotate(140);
                break;
        }

        return imageView;
    }

}