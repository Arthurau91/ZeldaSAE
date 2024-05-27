package com.example.zeldasae.modele;

import com.example.zeldasae.controller.ObservateurVie;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entite {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private String id;
    private int width;
    private int height;
    private int column;
    private int rows;
    private String direction;
    private int vitesse;
    private HitBox hitBox;
    private static int n = 0;
    private IntegerProperty pv;
    private int pvDebut;
    private int degats;
    private BarreDeVie barreDeVie;

    public Entite(int x, int y, int width, int height, int column, int rows) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = ""+n++;
        this.width = width;
        this.height = height;
        this.column = column;
        this.rows = rows;
        this.direction = "null";
        this.vitesse = 10;
        this.hitBox = new HitBox(this.width, this.height, this.xProperty, this.yProperty);
        this.pvDebut = 5;
        this.pv = new SimpleIntegerProperty(this.pvDebut);
        this.degats = 1;

        if (this instanceof Joueur) {
            this.barreDeVie = new BarreDeVie(100, 20);
        } else {
            this.barreDeVie = new BarreDeVie(90, 10);
        }

        this.pv.addListener(new ObservateurVie(this));

    }

    public Entite(int x, int y, String id, int width, int height, int column, int rows) {
        this(x, y, width, height, column, rows);
        this.setId(id);
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
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getColumn() {
        return column;
    }
    public int getRows() {
        return rows;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return direction;
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    private void setId(String id) {
        this.id = id;
    }
    public int getPv() {
        return pv.getValue();
    }
    public int getPvDebut() {
        return pvDebut;
    }
    public void setPv(int pv) {
        this.pv.setValue(pv);
    }

    public IntegerProperty pv() {
        return this.pv;
    }
    public int getDegats() {
        return degats;
    }
    public void setDegats(int degats) {
        this.degats = degats;
    }
    public BarreDeVie getBarreDeVie() {
        return this.barreDeVie;
    }
    public HitBox getHitBox() {
        return hitBox;
    }

    private void mettreAJourBarreDeVie() {
        double pourcentage = ((double) this.getPv() / this.getPvDebut()) * 100;
        this.barreDeVie.setPourcentageVie(pourcentage);
    }

    public void perdreVie(int degats) {
        setPv(this.getPv() - degats);
        if (this.getPv() <= 0) {
            setPv(0);
        }
    }

    public void updatePositionBarreDeVie() {
        double barreX = this.getX() + (this.getWidth() - this.barreDeVie.getWidth()) / 2;
        this.barreDeVie.setLayoutX(barreX);
        this.barreDeVie.setLayoutY(this.getY() - this.barreDeVie.getHeight());
    }


    public void attaqueEntite(Entite entite) {
        if (verifVivant()) {
            entite.perdreVie(this.getDegats());
        }
    }

    public boolean verifVivant() {
        return this.getPv() > 0;
    }


    /**
     * Méthode qui gère le déplacement d'une Entite sur le pane
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          checkDeplacement()
     * @return true si le déplacement a été effectué sinon false
     */
    public void deplacement(Monde m) {
//        System.out.println("X : "+(this.getX()/this.width)%(this.width*this.column)+"; Y : "+(this.getY()/this.height)%(this.height*this.getRows()));
        if (verifVivant()) {
            int dx = 0;
            int dy = 0;

            if (this.direction.contains("up") && checkHitBox("up", m.getTerrain()))
                if (checkUp(m, vitesse))
                    dy -= vitesse;
            if (this.direction.contains("down") && checkHitBox("down", m.getTerrain()))
                if (checkDown(m, vitesse))
                    dy += vitesse;
            if (this.direction.contains("left") && checkHitBox("left", m.getTerrain()))
                if (checkLeft(m, vitesse))
                    dx -= vitesse;
            if (this.direction.contains("right") && checkHitBox("right", m.getTerrain()))
                if (checkRight(m, vitesse))
                    dx += vitesse;

            setX(getX() + dx);
            setY(getY() + dy);
        }
    }

    private boolean checkHitBox(String direction, Terrain terrain){
        return hitBox.checkColision(direction, this.rows, terrain) &&
                hitBox.checkBord(direction, this.column, this.rows, this.vitesse);
    }

    public boolean checkColisionEntite(Monde m, int x, int y){
        for (Ennemi ennemi : m.getListeEnnemis()){
            if (this != ennemi && ennemi.getHitBox().estDedans(x,y))
                return false;
        }
        return true;
    }

    private boolean checkUp(Monde m, int decalages){
        for (int i = 0; i <= width; i++){
            if (!checkColisionEntite(m, getX() + i, getY() - decalages))
                return false;
        }
        return true;
    }
    private boolean checkDown(Monde m, int decalages){
        for (int i = 0; i <= width; i++){
            if (!checkColisionEntite(m, getX() + i, getY() + height + decalages))
                return false;
        }
        return true;
    }
    private boolean checkRight(Monde m, int decalages){
        for (int i = 0; i <= height; i++){
            if (!checkColisionEntite(m, getX() + width + decalages, getY() + i))
                return false;
        }
        return true;
    }
    private boolean checkLeft(Monde m, int decalages){
        for (int i = 0; i <= height; i++){
            if (!checkColisionEntite(m, getX()-decalages, getY() + i))
                return false;
        }
        return true;
    }

}
