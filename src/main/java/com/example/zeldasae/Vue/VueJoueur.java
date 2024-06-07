package com.example.zeldasae.Vue;

import com.example.zeldasae.modele.Entite;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public class VueJoueur extends VueEntite{

    private Image[] sprites;
    private int statusAnim;

    public VueJoueur(Entite entite, Pane paneEntites) {
        super(entite, paneEntites);
        loadSprites();
        statusAnim = 0;
        super.creerImageEntite();
    }

    private void loadSprites(){
        Image tileset = new Image("file:src/main/resources/com/example/zeldasae/assets/joueur/link.png", 300, 240, false, false);

        int tileWidth = 30;
        int tileHeight = 30;
        int colonne = (int) (tileset.getWidth() / tileWidth);
        int ligne = (int) (tileset.getHeight() / tileHeight);
        sprites = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                sprites[y * colonne + x] = new WritableImage(tileset.getPixelReader(), (x * tileWidth), (y * tileHeight), tileWidth, tileHeight);
            }
        }
    }

    @Override
    public Image getImageBas() {
        return sprites[4*10+((statusAnim++)%10)];
    }
    @Override
    public Image getImageDroite() {
        return sprites[7*10+((statusAnim++)%10)];
    }
    @Override
    public Image getImageGauche() {
        return sprites[5*10+((statusAnim++)%10)];
    }
    @Override
    public Image getImageHaut() {
        return sprites[6*10+((statusAnim++)%10)];
    }
}
