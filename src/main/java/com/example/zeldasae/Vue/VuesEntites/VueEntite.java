package com.example.zeldasae.Vue.VuesEntites;

import com.example.zeldasae.Vue.VueBarreDeVie;
import com.example.zeldasae.modele.entities.Entite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public abstract class VueEntite {

    private Entite entite;
    private Pane paneEntites;
    protected ImageView imgEntite;
    protected Image[] sprites;
    private String olddirection;
    protected VueBarreDeVie vueBarreDeVie;
    private int tempsTransition;
    protected int statusAnim;

    public VueEntite(Entite entite, Pane paneEntites, IntegerProperty temps) {
        this.tempsTransition = 0;
        this.entite = entite;
        this.olddirection = entite.getDirection();
        this.paneEntites = paneEntites;
        this.entite.directionProperty().addListener((obs, old, nouv)-> changeImage(nouv, old));
        temps.addListener((obs, old, nouv) -> changeImageStatique(nouv.intValue()));
    }

    public VueBarreDeVie getVueBarreDeVie() {
        return vueBarreDeVie;
    }
    protected Entite getEntite() {
        return entite;
    }
    public abstract Image getImageBas();
    public abstract Image getImageHaut();
    public abstract Image getImageDroite();
    public abstract Image getImageGauche();
    public abstract Image getImageStatiqueBas();
    public abstract Image getImageStatiqueHaut();
    public abstract Image getImageStatiqueDroite();
    public abstract Image getImageStatiqueGauche();

    public void creerImageEntite() {
        imgEntite = new ImageView();
        Image image;
        image = this.getImageDroite();
        imgEntite.setImage(image);

        imgEntite.setId(entite.getId());
        imgEntite.translateXProperty().bind(entite.xProperty());
        imgEntite.translateYProperty().bind(entite.yProperty());
        imgEntite.setId(entite.getId());

        this.paneEntites.getChildren().add(imgEntite);
    }

    public void supprimerImageEntite() {
        this.paneEntites.getChildren().remove(this.paneEntites.lookup("#" + entite.getId()));
    }

    private void changeImage(String nouv, String old){
        olddirection = old;
        if (nouv.contains("up") && tempsTransition%5 == 0) {
            imgEntite.setImage(this.getImageHaut());
        }
        if (nouv.contains("down") && tempsTransition%5 == 0) {
            imgEntite.setImage(this.getImageBas());
        }
        if (nouv.contains("right") && tempsTransition%5 == 0) {
            imgEntite.setImage(this.getImageDroite());
        }
        if (nouv.contains("left") && tempsTransition%5 == 0){
            imgEntite.setImage(this.getImageGauche());
        }
        tempsTransition++;
    }

    protected void bindBarreDeViePosition() {

        DoubleBinding barreXBinding = Bindings.createDoubleBinding(() ->
                        entite.getX() + (entite.getWidth() - this.vueBarreDeVie.getWidth()) / 2,
                entite.xProperty(), vueBarreDeVie.widthProperty(), this.vueBarreDeVie.widthProperty());

        DoubleBinding barreYBinding = Bindings.createDoubleBinding(() ->
                        entite.getY() - this.vueBarreDeVie.getHeight(),
                entite.yProperty(), this.vueBarreDeVie.heightProperty());

        this.vueBarreDeVie.layoutXProperty().bind(barreXBinding);
        this.vueBarreDeVie.layoutYProperty().bind(barreYBinding);

    }

    private void changeImageStatique(int nouv){
        if (!entite.isBouge() && nouv%10 == 0) {
            if ((!entite.getDeplacement().contains("up")) && olddirection.contains("up")) {
                imgEntite.setImage(this.getImageStatiqueHaut());
            }
            if ((!entite.getDeplacement().contains("down")) && olddirection.contains("down")) {
                imgEntite.setImage(this.getImageStatiqueBas());
            }
            if ((!entite.getDeplacement().contains("right")) && olddirection.contains("right")) {
                imgEntite.setImage(this.getImageStatiqueDroite());
            }
            if ((!entite.getDeplacement().contains("left")) && olddirection.contains("left")) {
                imgEntite.setImage(this.getImageStatiqueGauche());
            }
        }
    }

    protected void loadSprites(Image tileset, int tileWidth, int tileHeight){
        int colonne = (int) (tileset.getWidth() / tileWidth);
        int ligne = (int) (tileset.getHeight() / tileHeight);
        sprites = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                sprites[y * colonne + x] = new WritableImage(tileset.getPixelReader(), (x * tileWidth), (y * tileHeight), tileWidth, tileHeight);
            }
        }
    }
}
