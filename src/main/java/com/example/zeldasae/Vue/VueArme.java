package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectile;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static javafx.scene.input.KeyCode.*;


public class VueArme {

    private Joueur joueur;
    private Pane paneEntites;
    private Monde map;
    private Pane mapPane;

    public VueArme (Joueur joueur, Pane paneEntites, Monde map, Pane mapPane) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
        this.map = map;
        this.mapPane = mapPane;
    }

    public void donnerCoup(int x, int y, KeyEvent keyEvent) {
        ImageView imageView = switchImageCoup(x, y, keyEvent);
        paneEntites.getChildren().add(imageView);

        PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation() - 0.3));
        pause.setOnFinished(event -> paneEntites.getChildren().remove(imageView));
        pause.play();
    }

    public void creerProjectileJoueurVue(Projectile p) {
        ImageView image = getImageViewProjectile(p);
        image.setId(p.getNom());
        image.translateXProperty().bind(p.getHitBox().xProperty());
        image.translateYProperty().bind(p.getHitBox().yProperty());
        this.paneEntites.getChildren().add(image);
    }

    public void creerProjectileEnnemiVue(Projectile p) {
        ImageView image = getImageViewProjectile(p);
        image.setId(p.getNom());
        image.translateXProperty().bind(p.getHitBox().xProperty());
        image.translateYProperty().bind(p.getHitBox().yProperty());
        this.paneEntites.getChildren().add(image);
    }

    private static ImageView getImageViewProjectile(Projectile p) {
        ImageView image;
        if (p.getHitBox().getLarge() > p.getHitBox().getHaut())
            image = new ImageView(new Image("file:src/main/resources/com/example/zeldasae/assets/projectileMagique.png", p.getHitBox().getLarge(), p.getHitBox().getHaut(), false, false));
        else image = new ImageView(new Image("file:src/main/resources/com/example/zeldasae/assets/projectileMagique.png", p.getHitBox().getHaut(), p.getHitBox().getLarge(), false, false));

        if (p.getDirection().equals("RIGHT"))
            image.setRotate(180);
        if (p.getDirection().equals("UP"))
            image.setRotate(90);
        if (p.getDirection().equals("DOWN"))
            image.setRotate(270);
        image.setId(p.getNom());
        return image;
    }

    public void supprimerProjectileVue(Projectile p) {
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + p.getNom()));
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
