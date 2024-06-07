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
            Image img = new Image("file:src/main/resources/com/example/zeldasae/assets/" + joueur.getInv().getArmeActuelle().getNom() + "Attaque.png");
            ImageView imageView = new ImageView(img);
            imageView.setTranslateX(x);
            imageView.setTranslateY(y);
            imageView.setFitWidth(joueur.getInv().getArmeActuelle().getHitBox().getLarge());
            imageView.setFitHeight(joueur.getInv().getArmeActuelle().getHitBox().getHaut());

            if(keyEvent.getCode() == UP || keyEvent.getCode() == DOWN) {

            }

            paneEntites.getChildren().add(imageView);

            PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation()));
            pause.setOnFinished(event -> paneEntites.getChildren().remove(imageView));
            pause.play();
    }

    public void creerProjectileVue(Projectile p) {
        p.getHitBox().setX(joueur.getX()); //DES QUE T'UTILISERAS LES TOUCHES DE DEPLACEMENT POUR L'ARC, FAIS CA DANS Projectile.setPosMap()
        p.getHitBox().setY(joueur.getY());
        Rectangle r = new Rectangle(p.getHitBox().getLarge(), p.getHitBox().getHaut(), Color.RED);
        r.setId(p.getNom());
        r.translateXProperty().bind(p.getHitBox().xProperty());
        r.translateYProperty().bind(p.getHitBox().yProperty());
        this.paneEntites.getChildren().add(r);
        this.joueur.setPeutDonnerCoupProperty(false);

        PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> this.joueur.setPeutDonnerCoupProperty(true));
        pause.play();
    }

    public void supprimerProjectileVue(Projectile p) {
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + p.getNom()));
    }

}
