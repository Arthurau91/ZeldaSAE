package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.TilePane;

public class Joueur {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private String id;
    private Inventaire inv;

    public Joueur(int x, int y) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = "j1";
        this.inv = new Inventaire();
        // à modifier
    }

    public int getX() {
        return this.xProperty.getValue();
    }

    public void setX(int n) {
        this.xProperty.setValue(n);
    }

    public IntegerProperty xProperty() {
        return xProperty;
    }


    public int getY() {
        return yProperty.getValue();
    }

    public void setY(int y) {
        this.yProperty.setValue(y);
    }


    public IntegerProperty yProperty() {
        return yProperty;
    }

    public String getId() {
        return id;
    }

    public Joueur getJoueur() {
        return this;
    }


    public void deplacementZQSD(char direction, TilePane t, Monde m) {
        int vitesse = 5;
        switch (direction) {
            case 'z':
                if (checkDeplacement(t, 0, -vitesse, m) && checkBord(direction, t, 0, -vitesse)) {
                    this.setY(this.getY() - vitesse);
                }
                System.out.println("Déplacement en Z effectué");
                break;
            case 'q':
                if(checkDeplacement(t, -vitesse, 0, m) && checkBord(direction, t, -vitesse, 0)) {
                    this.setX(this.getX()-vitesse);
                }
                System.out.println("Déplacement en Q effectué");
                break;
            case 's':
                if (checkDeplacement(t, 0, vitesse, m))
                    this.setY(this.getY()+vitesse);
                System.out.println("Déplacement en S effectué");
                break;
            case 'd':
                if (checkDeplacement(t, vitesse, 0, m) && checkBord(direction, t, vitesse, 0))
                    this.setX(this.getX()+vitesse);
                System.out.println("Déplacement en D effectué");
                break;
        }
    }

    //remet en boolean une fois que t'as bien testé

    /**
     *
     * @param t
     * @param vitesseX
     * @param vitesseY
     * @param m
     * @return
     */
        public boolean checkDeplacement(TilePane t, int vitesseX, int vitesseY, Monde m) {
            int nouvCoListe;
            nouvCoListe = ((this.getX()+vitesseX) / (int) t.getTileWidth()) + ((this.getY() + vitesseY)/ (int) t.getTileHeight() * t.getPrefColumns());


            if (nouvCoListe < 0 || nouvCoListe >= m.getMap().size() || m.getMap().get(nouvCoListe) != 0) {
                System.out.println("donc : " + nouvCoListe);
//                System.out.println("ça vloque");
                return false;
            }
            return true;
        }

        public boolean checkBord(char direction, TilePane t, int vitesseX, int vitesseY) {
            int position = (this.getX() / (int) t.getTileWidth()) + (this.getY()/ (int) t.getTileHeight() * t.getPrefColumns());
            int prochainePosition = ((this.getX()+vitesseX) / (int) t.getTileWidth()) + ((this.getY() + vitesseY)/ (int) t.getTileHeight() * t.getPrefColumns());

            System.out.println(position);
                switch (direction) {
                    case 'z':
                        if (position >= 0 && position < t.getPrefColumns()){
                            if (position != prochainePosition)
                                return false;
                        }
                        break;
                    case 'q':
                        if (position%30 == 0){
                            if (position != prochainePosition)
                            return false;
                        }
                        break;
                    case 'd':
                        if ((position+1)%30 == 0){
                            if (position != prochainePosition)
                            return false;
                        }
                        break;
                    //case 's' n'existe pas, car déjà pris en compte dans les tests précédents (checkDeplacement)
                }

            return true;
        }
}
