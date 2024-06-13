package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Joueur;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.Projectile;
import com.example.zeldasae.modele.armes.Hache;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


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
        if(joueur.getInv().getArmeActuelle() instanceof Hache) { //TODO à remplacer plus tard avec une méthode
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

    public void animationHache(int x, int y, ImageView imageView) {
        int largeur = 30, hauteur = 30, angle = -45;
        int[] iTab = new int[1];
        iTab[0] = 0;
        boolean[] prochaineAnim = new boolean[1];
        prochaineAnim[0] = true;

        imageView.setFitHeight(largeur);
        imageView.setFitWidth(hauteur);

//        PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
//        pause.setOnFinished(event -> etapesAnimationHache(imageView, x, y, largeur, hauteur, iTab[0]));

        //prochaienAnim[0] = true
        while (iTab[0] != 9) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.05*(iTab[0]+1)));
            int i = iTab[0];
            pause.setOnFinished(event -> supprimerSpriteAnim(imageView, x, y, largeur, hauteur, i));
            pause.play();
            iTab[0]++;
        }
    }

    public void supprimerSpriteAnim(ImageView imageView, int x, int y, int largeur, int hauteur, int iTab) {
        paneEntites.getChildren().remove(imageView);
        etapesAnimationHache(imageView, x, y, largeur, hauteur, iTab);
    }

    public void etapesAnimationHache(ImageView imageView, int x, int y, int largeur, int hauteur, int i) { //TODO pour raccourcir la méthode, faire en sorte qu'elle renvoie un tableau avec x, y, et rotation
        switch (i) {
            case 0:
                imageView.setTranslateX(x);
                imageView.setTranslateY(y);
                imageView.setRotate(-45);
                break;
            case 1:
                imageView.setTranslateX(x+largeur);
                imageView.setTranslateY(y);
                imageView.setRotate(0);
                break;
            case 2:
                imageView.setTranslateX(x+largeur*2);
                imageView.setTranslateY(y);
                imageView.setRotate(45);
                break;
            case 3:
                imageView.setTranslateX(x+largeur*2);
                imageView.setTranslateY(y+hauteur);
                imageView.setRotate(90);
                break;
            case 4:
                imageView.setTranslateX(x+largeur*2);
                imageView.setTranslateY(y+hauteur*2);
                imageView.setRotate(135);
                break;
            case 5:
                imageView.setTranslateX(x+largeur);
                imageView.setTranslateY(y+hauteur*2);
                imageView.setRotate(180);
                break;
            case 6:
                imageView.setTranslateX(x);
                imageView.setTranslateY(y+hauteur*2);
                imageView.setRotate(225);
                break;
            case 7:
                imageView.setTranslateX(x);
                imageView.setTranslateY(y+hauteur);
                imageView.setRotate(270);
                break;
        }
        paneEntites.getChildren().add(imageView);
        if (i == 8)
            paneEntites.getChildren().remove(imageView);
    }

}
