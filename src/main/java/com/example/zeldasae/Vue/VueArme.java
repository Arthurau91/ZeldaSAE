package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectile;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class VueArme {

    private Joueur joueur;
    private Pane paneEntites;
    private Monde map;
    private Pane mapPane;
    private boolean peutDonnerCoup;

    public VueArme (Joueur joueur, Pane paneEntites, Monde map, Pane mapPane) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
        this.map = map;
        this.mapPane = mapPane;
        this.peutDonnerCoup = true;
    }

    public boolean isPeutDonnerCoup() {
        return this.peutDonnerCoup;
    }

    public void donnerCoup(int x, int y) {
        Image img = new Image ("file:src/main/resources/com/example/zeldasae/assets/" + joueur.getInv().getArmeActuelle().getNom() + "Attaque.png");
        ImageView imageView = new ImageView(img);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);

        imageView.setFitWidth(joueur.getInv().getArmeActuelle().getHitBox().getLarge());
        imageView.setFitHeight(joueur.getInv().getArmeActuelle().getHitBox().getHaut());

        paneEntites.getChildren().add(imageView);
        this.peutDonnerCoup = false;
        PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> gererCooldown(imageView));
        pause.play();
    }

    public void gererCooldown(ImageView imageView) {
        paneEntites.getChildren().remove(imageView);
        this.peutDonnerCoup = true;
    }


    public void creerProjectileVue(Projectile p) {
        p.getHitBox().setX(joueur.getX()); //DES QUE T'UTILISERAS LES TOUCHES DE DEPLACEMENT POUR L'ARC, FAIS CA DANS Projectile.setPosMap()
        p.getHitBox().setY(joueur.getY());
        Rectangle r = new Rectangle(p.getHitBox().getLarge(), p.getHitBox().getHaut(), Color.RED);
        r.setId(p.getNom());
        r.translateXProperty().bind(p.getHitBox().xProperty());
        r.translateYProperty().bind(p.getHitBox().yProperty());
        this.paneEntites.getChildren().add(r);
        this.peutDonnerCoup = false;

        PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation()));
        pause.setOnFinished(event -> this.peutDonnerCoup = true);
        pause.play();
    }

    public void supprimerProjectileVue(Projectile p) {
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + p.getNom()));
    }

}
