package com.example.zeldasae.modele;

import com.example.zeldasae.Vue.VueBarreDeVie;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Entite {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private String id;
    private int width;
    private int height;
    private IntegerProperty widthProperty;
    private int column;
    private int rows;
    private String deplacement;
    private int vitesse;
    private HitBox hitBox;
    private static int n = 0;
    private IntegerProperty pv;
    private int pvMax;
    private int degats;
    private StringProperty direction;
    private VueBarreDeVie vueBarreDeVie;
    protected Monde monde;

    public Entite(int x, int y, int width, int height, int column, int rows, Monde monde) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = ""+n++;
        this.width = width;
        this.height = height;
        this.widthProperty = new SimpleIntegerProperty(this.width);
        this.column = column;
        this.rows = rows;
        this.deplacement = "null";
        this.vitesse = 10;
        this.hitBox = new HitBox(this.width, this.height, this.xProperty, this.yProperty);
        this.pvMax = 10;
        this.pv = new SimpleIntegerProperty(this.pvMax);
        this.degats = 1;
        this.direction = new SimpleStringProperty("right");
        this.monde = monde;

        //mettre côté vue
        if (this instanceof Joueur) {
            this.vueBarreDeVie = new VueBarreDeVie(100, 20);
            this.getVueBarreDeVie().setLayoutX(1050);
            this.getVueBarreDeVie().setLayoutY(10);
        } else {
            this.vueBarreDeVie = new VueBarreDeVie(90, 10);
            bindBarreDeViePosition();
        }

        //à faire dans le contrôleur
//        this.pv.addListener(new ObservateurVie(this));
    }

    public Entite(int x, int y, String id, int width, int height, int column, int rows, Monde monde) {
        this(x, y, width, height, column, rows, monde);
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

    public void setPv(int pv) {
        this.pv.setValue(pv);
    }
    public IntegerProperty pv() {
        return this.pv;
    }

    public StringProperty directionProperty() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction.setValue(direction);
    }
    public String getDirection() {
        return direction.getValue();
    }
    public void addDirectionImage(String direction){this.direction.setValue(this.getDirection()+direction);}

    public String getId() {
        return id;
    }
    public int getWidth() {
        return this.widthProperty.getValue();
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
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    private void setId(String id) {
        this.id = id;
    }
    public int getPv() {
        return pv.getValue();
    }
    public int getPvMax() {
        return pvMax;
    }
    public int getDegats() {
        return degats;
    }
    public void setDegats(int degats) {
        this.degats = degats;
    }
    public VueBarreDeVie getVueBarreDeVie() {
        return this.vueBarreDeVie;
    }
    public HitBox getHitBox() {
        return hitBox;
    }
    public String getDeplacement() {
        return deplacement;
    }
    public void setDeplacement(String deplacement) {
        this.deplacement = deplacement;
    }
    public int getVitesse() {
        return vitesse;
    }

    public void perdreVie(int degats) {
        setPv(this.getPv() - degats);
        if (this.getPv() <= 0) {
            setPv(0);
        }
    }

    public void ajouterVie(int vieRecup) {
            setPv(this.getPv() + vieRecup);
    }


    public void attaqueEntite(Entite entite) {
        if (verifVivant()) {
            entite.perdreVie(this.getDegats());
        }
    }

    public void meurt() {
        this.monde.retirerEntite(this);
    }


    public boolean verifVivant() {
        return this.getPv() > 0;
    }

    //remettre côté vue
    private void bindBarreDeViePosition() {

        DoubleBinding barreXBinding = Bindings.createDoubleBinding(() ->
                        this.getX() + (this.getWidth() - this.vueBarreDeVie.getWidth()) / 2,
                this.xProperty, this.widthProperty(), this.vueBarreDeVie.widthProperty());

        DoubleBinding barreYBinding = Bindings.createDoubleBinding(() ->
                        this.getY() - this.vueBarreDeVie.getHeight(),
                this.yProperty, this.vueBarreDeVie.heightProperty());

        this.vueBarreDeVie.layoutXProperty().bind(barreXBinding);
        this.vueBarreDeVie.layoutYProperty().bind(barreYBinding);

    }

    private IntegerProperty widthProperty() {
        return this.widthProperty;
    }

    /**
     * Méthode qui gère le déplacement d'une Entite sur le pane
     * @param m le monde contenant le terrain, le joueur et la liste d'ennemis qui est passé en paramètre à la méthode
     *          checkDeplacement()
     * @return true si le déplacement a été effectué sinon false
     */
    public boolean deplacement(Monde m) {
//        System.out.println("X : "+(this.getX()/this.width)%(this.width*this.column)+"; Y : "+(this.getY()/this.height)%(this.height*this.getRows()));
        if (verifVivant()) {
            int dx = 0;
            int dy = 0;
            int x = getX();
            int y = getY();

            if (this.deplacement.contains("up") && checkHitBox("up", m.getTerrain()))
                if (checkUp(m, vitesse)) {
                    dy -= vitesse;
                    addDirectionImage("up");
                }
            if (this.deplacement.contains("down") && checkHitBox("down", m.getTerrain()))
                if (checkDown(m, vitesse)) {
                    dy += vitesse;
                    addDirectionImage("down");
                }
            if (this.deplacement.contains("left") && checkHitBox("left", m.getTerrain()))
                if (checkLeft(m, vitesse)) {
                    dx -= vitesse;
                    addDirectionImage("left");
                }
            if (this.deplacement.contains("right") && checkHitBox("right", m.getTerrain()))
                if (checkRight(m, vitesse)) {
                    dx += vitesse;
                    addDirectionImage("right");
                }

            setX(getX() + dx);
            setY(getY() + dy);
            return x != getX() || y != getY();
        }
        return false;
    }

    private boolean checkHitBox(String direction, Terrain terrain){
        return hitBox.checkColision(direction, this.rows, terrain) &&
                hitBox.checkBord(direction, this.column, this.rows, this.vitesse);
    }

    public boolean checkColisionEntite(Monde m, int x, int y){
        for (Ennemi ennemi : m.getListeEnnemis()){
            if (this != ennemi && ennemi.getHitBox().contient(x,y))
                return true;
        }
        return false;
    }

    public boolean checkUp(Monde m, int decalages){
        for (int i = 0; i <= width; i++){
            if (checkColisionEntite(m, getX() + i, getY() - decalages))
                return false;
        }
        return true;
    }
    public boolean checkDown(Monde m, int decalages){
        for (int i = 0; i <= width; i++){
            if (checkColisionEntite(m, getX() + i, getY() + height + decalages))
                return false;
        }
        return true;
    }
    public boolean checkRight(Monde m, int decalages){
        for (int i = 0; i <= height; i++){
            if (checkColisionEntite(m, getX() + width + decalages, getY() + i))
                return false;
        }
        return true;
    }
    public boolean checkLeft(Monde m, int decalages){
        for (int i = 0; i <= height; i++){
            if (checkColisionEntite(m, getX() - decalages, getY() + i))
                return false;
        }
        return true;
    }

}
