package com.example.zeldasae.Vue.VueArmes;

import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

public class VueHache extends VueArme {

    public VueHache(Joueur joueur, Pane paneEntites, Monde map, Pane mapPane) {
        super(joueur, paneEntites, map, mapPane);
    }

    @Override
    public void donnerCoup(int x, int y, KeyEvent keyEvent) {
        animationHache(x, y, super.switchImageCoup(x, y, keyEvent));
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
            getPaneEntites().getChildren().remove(imageView);
        else {
            getPaneEntites().getChildren().remove(imageView);
            int[] coAnimation = etapesAnimationHache(imageView, x, y, largeur, hauteur, iTab);
            imageView.setTranslateX(coAnimation[0]);
            imageView.setTranslateY(coAnimation[1]);
            imageView.setRotate(coAnimation[2]);
            getPaneEntites().getChildren().add(imageView);
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
