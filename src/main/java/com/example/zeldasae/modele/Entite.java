package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.TilePane;

public abstract class Entite {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private String id;
    private int width;
    private int height;
    private int column;

    public Entite(int x, int y, String id, int width, int height, int column) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = id;
        this.width = width;
        this.height = height;
        this.column = column;
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

    /**
     * Méthode qui gère le déplacement d'une Entite sur le pane
     * @param direction un char contenant la direction vers laquelle le déplacement a été lancé sous forme :
     *                  'z' = haut, 'q' = gauche, 's' = bas, 'd' = droite
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          checkDeplacement()
     * @return true si le déplacement a été effectué sinon false
     */
    public boolean deplacement(char direction, Monde m) {
        int vitesse = 30;
        switch (direction) {
            case 'z':
                if (checkDeplacement(0, -vitesse, m) && checkBord(direction)) {
                    this.setY(this.getY() - vitesse);
//                    System.out.println("Déplacement en Z effectué");
                    return true;
                }
                else return false;
            case 'q':
                if(checkDeplacement(-vitesse, 0, m) && checkBord(direction)) {
                    this.setX(this.getX()-vitesse);
//                    System.out.println("Déplacement en Q effectué");
                    return true;
                }
                else return false;
            case 's':
                if (checkDeplacement(0, vitesse, m) && checkBord(direction)){
                    this.setY(this.getY()+vitesse);
//                    System.out.println("Déplacement en S effectué");
                    return true;
                }
                else return false;
            case 'd':
                if (checkDeplacement(vitesse, 0, m) && checkBord(direction)){
                    this.setX(this.getX()+vitesse);
//                    System.out.println("Déplacement en D effectué");
                    return true;
                }
                else return false;
        }
        return false;
    }

    /**
     * Méthode qui regarde si le déplacement est possible
     * @param vitesseX le nombre de pixels que le déplacement va faire en horizontal
     * @param vitesseY le nombre de pixels que le déplacement va faire en vertical
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis
     * @return true si le déplacement est possible sinon false
     */
    public boolean checkDeplacement(int vitesseX, int vitesseY, Monde m) {
        int nouvCoListe;
        nouvCoListe = ((this.getX()+vitesseX) /  this.width) + ((this.getY() + vitesseY)/ this.height * this.column);


        if (nouvCoListe < 0 || nouvCoListe >= m.getTerrain().getMap().size() || m.getTerrain().getMap().get(nouvCoListe) != 0) {
            System.out.println("donc : " + nouvCoListe);
            return false;
        }
        return true;
    }

    /**
     * Méthode qui regarde si le mouvement prévu ne va pas sortir de la map
     * @param direction un char contenant la direction vers laquelle le déplacement a été lancé sous forme
     *                  'z' = haut, 'q' = gauche, 's' = bas, 'd' = droite
     * @return false si le déplacement comporte un risque de sortir sinon true
     */
    public boolean checkBord(char direction){
        int position = (this.getX() / this.width) + (this.getY()/ this.height * this.column);
        System.out.println(position);
        switch (direction) {
            case 'z':
                if (position >= 0 && position < this.column){
                    return false;
                }
                break;
            case 'q':
                if (position%30 == 0){
                    return false;
                }
                break;
            case 'd':
                if ((position+1)%30 == 0){
                    return false;
                }
                break;
            //case 's' n'existe pas, car déjà pris en compte dans les tests précédents (checkDeplacement)
        }

        return true;
    }
}
