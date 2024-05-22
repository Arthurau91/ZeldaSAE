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
    private int rows;
    private String direction;
    private int vitesse;
    private HitBox hitBox;
    private static int n = 0;

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
        this.hitBox = new HitBox(this.width, this.height);
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

    /**
     * Méthode qui gère le déplacement d'une Entite sur le pane
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          checkDeplacement()
     * @return true si le déplacement a été effectué sinon false
     */
    public void deplacement(Monde m) {
//        System.out.println("X : "+(this.getX()/this.width)%(this.width*this.column)+"; Y : "+(this.getY()/this.height)%(this.height*this.getRows()));
        int dx = 0;
        int dy = 0;

        if (this.direction.contains("up") && checkHitBox("up",m.getTerrain()))
            dy -= vitesse;
        if (this.direction.contains("down") && checkHitBox("down",m.getTerrain()))
            dy += vitesse;
        if (this.direction.contains("left") && checkHitBox("left",m.getTerrain()))
            dx -= vitesse;
        if (this.direction.contains("right") && checkHitBox("right",m.getTerrain()))
            dx += vitesse;

        setX(getX() + dx);
        setY(getY() + dy);
    }

    private boolean checkHitBox(String direction, Terrain terrain){
        return hitBox.checkColision(this.getX(),this.getY(), direction,this.rows, terrain) &&
                hitBox.checkBord(direction,this.getX(),this.getY(),this.column,this.rows,this.vitesse);
    }

}
