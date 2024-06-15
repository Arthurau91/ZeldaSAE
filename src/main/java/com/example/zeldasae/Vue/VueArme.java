package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.entities.Joueur;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectile;
import com.example.zeldasae.modele.armes.Hache;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class VueArme { //TODO créer des classes VueEpee, VueHache, VueProjectile...qui extends VueArme

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
        if(joueur.getInv().getArmeActuelle().getNom().equals("Hache")) {
            animationHache(x, y, imageView);
        }
        else {
            paneEntites.getChildren().add(imageView);

            PauseTransition pause = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation() - 0.3));
            pause.setOnFinished(event -> paneEntites.getChildren().remove(imageView));
            pause.play();
        }

//        Rectangle hitboxTest = new Rectangle(joueur.getInv().getArmeActuelle().getHitBox().getLarge(), joueur.getInv().getArmeActuelle().getHitBox().getHaut(), Color.RED);
//        hitboxTest.setTranslateX(x);
//        hitboxTest.setTranslateY(y);
//        paneEntites.getChildren().add(hitboxTest);
//
//        PauseTransition pauseHitbox = new PauseTransition(Duration.seconds(joueur.getInv().getArmeActuelle().getDelaiRecuperation() - 0.3));
//        pauseHitbox.setOnFinished(event -> paneEntites.getChildren().remove(hitboxTest));
//        pauseHitbox.play();
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

    public void animationHache(int x, int y, ImageView imageView) {
        int largeur = 30, hauteur = 30, angle = -45;
        int[] iTab = new int[1];
        iTab[0] = 0;
        boolean[] prochaineAnim = new boolean[1];
        prochaineAnim[0] = true;

        imageView.setFitHeight(largeur);
        imageView.setFitWidth(hauteur);

        while (iTab[0] != 9) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.02*(iTab[0]+1)));
            int i = iTab[0];
            pause.setOnFinished(event -> prochaineEtapeAnim(imageView, x, y, largeur, hauteur, i));
            pause.play();
            iTab[0]++;

        }
    }

    public void prochaineEtapeAnim(ImageView imageView, int x, int y, int largeur, int hauteur, int iTab) {
        if (iTab == 8)
            paneEntites.getChildren().remove(imageView);
        else {
            paneEntites.getChildren().remove(imageView);
            int[] coAnimation = etapesAnimationHache(imageView, x, y, largeur, hauteur, iTab);
            imageView.setTranslateX(coAnimation[0]);
            imageView.setTranslateY(coAnimation[1]);
            imageView.setRotate(coAnimation[2]);
            paneEntites.getChildren().add(imageView);
        }

    }

    public int[] etapesAnimationHache(ImageView imageView, int x, int y, int largeur, int hauteur, int i) {
        switch (i) {
            case 0:
                return new int[] {x, y, -45};
            case 1:
                return new int[] {x+largeur, y, 0};
            case 2:
                return new int[] {x+largeur*2, y, 45};
            case 3:
                return new int[] {x+largeur*2, y+hauteur, 90};
            case 4:
                return new int[] {x+largeur*2, y+hauteur*2, 135};
            case 5:
                return new int[] {x+largeur, y+hauteur*2, 180};
            case 6:
                return new int[] {x, y+hauteur*2, 225};
            case 7:
                return new int[] {x, y+hauteur, 270};
        }
            return null;
    }

}