package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueProjectile {

    private Pane paneEntites;
    public VueProjectile(Pane paneEntites) {
        this.paneEntites = paneEntites;
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

    public void supprimerProjectileVue(Projectile p) {
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + p.getNom()));
    }

    private static ImageView getImageViewProjectile(Projectile p) {
        ImageView image;
        if (p.getHitBox().getLarge() > p.getHitBox().getHaut()) //TODO faire un switch pour les projectiles
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
}
