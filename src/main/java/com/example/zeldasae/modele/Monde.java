package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;
import com.example.zeldasae.modele.armes.Arc;
import com.example.zeldasae.modele.armes.Boomerang;
import com.example.zeldasae.modele.armes.Hache;
import com.example.zeldasae.modele.armures.ArmureChevalier;
import com.example.zeldasae.modele.armures.ArmureFragile;
import com.example.zeldasae.modele.collectibles.Collectible;
import com.example.zeldasae.modele.collectibles.Fleche;
import com.example.zeldasae.modele.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    private ObservableList<Ennemi> listeEnnemis;
    private ObservableList<Projectile> listeProjectiles;
    private ObservableList<Collectible> listeCollectibles;
    private BFS bfs;
    private List<Coffre> coffres;

    /**
     * Constructeur de la classe Monde
     */
    public Monde(Joueur joueur, BFS bfs, int rows, int columns) {

        this.joueur = joueur;
        this.terrain = new Terrain(rows, columns);
        this.listeEnnemis = FXCollections.observableArrayList();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeCollectibles = FXCollections.observableArrayList();
        this.bfs = bfs;
        this.coffres = new ArrayList<>();
    }

    public ObservableList<Projectile> getListeProjectiles() {
        return this.listeProjectiles;
    }
    public void addProjectile(Projectile p) {
        this.listeProjectiles.add(p);
    }
    public void removeProjectile(Projectile p) {
        this.listeProjectiles.remove(p);
    }

    public ObservableList<Collectible> getListeCollectibles() {
        return listeCollectibles;
    }
    public void addCollectible(Collectible c) {
        this.listeCollectibles.add(c);
    }

    public ObservableList<Ennemi> getListeEnnemis() {
        return this.listeEnnemis;
    }
    public void addEnnemi(Ennemi ennemi) {
        this.listeEnnemis.add(ennemi);
    }

    public void addCoffre(Coffre coffre) {
        this.coffres.add(coffre);
    }
    public List<Coffre> getCoffres() {
        return coffres;
    }
    public Terrain getTerrain() {
        return this.terrain;
    }
    public Joueur getJoueur() {
        return this.joueur;
    }
    public BFS getBfs() {
        return bfs;
    }
    public void setMap(ArrayList<Integer> map) {
        this.terrain.setMap(map);
    }


    public void deplacementEnnemi(){
        for (Ennemi ennemi : this.listeEnnemis) {
            ennemi.deplacement(this);
        }
    }

    public void checkCollectiblesRamasses() {
        for(int i = 0; i < getListeCollectibles().size(); i++) {
            if (getListeCollectibles().get(i).getHitBox().estDedansHitbox(getJoueur().getHitBox())) {
                getJoueur().getInv().ajouterCollectible(getListeCollectibles().get(i));
                getListeCollectibles().remove(i);
                i--;
            }
        }
    }

    public int[] cooBloc(int x, int y, String direction){
        int coo = terrain.changeCoo(x, y);
        int coobloc = 0;
        int coovide = 0;
        if (direction.contains("up")){
            coobloc = coo - 100;
            coovide = coobloc - 100;
        }
        else if (direction.contains("down")){
            coobloc = coo + 100;
            coovide = coobloc + 100;
        }
        else if (direction.contains("right")){
            coobloc = coo + 1;
            coovide = coobloc + 1;
        }
        else if (direction.contains("left")){
            coobloc = coo - 1;
            coovide = coobloc - 1;
        }
        return new int[]{coobloc, coovide};
    }

    public void setEnnemisMorts(){
        listeEnnemis.removeIf(ennemi -> !ennemi.verifVivant());
    }

    public void deplacerProjectiles() {
        for (int i = 0; i < this.listeProjectiles.size(); i++) {
            this.listeProjectiles.get(i).seDeplace(this);
            if (listeProjectiles.get(i).aRetirer(terrain)) {
                removeProjectile(this.listeProjectiles.get(i));
                i--;
            }
        }
    }

    public Coffre coffreOuvert() {
        for (Coffre coffre : this.coffres) {
            if (coffre.isEstOuvert())
                return coffre;
        }

        return null;
    }

    public void initCoffres(){
        Coffre coffre = coffres.get(0);
        Coffre coffre1 = coffres.get(1);
        coffre.ajouterItem(new Charme("charme", 1));
        coffre.ajouterItem(new Arc());
        coffre.ajouterItem(new ArmureChevalier());
        coffre1.ajouterItem(new Hache());
        coffre1.ajouterItem(new Boomerang());
        coffre1.ajouterItem(new ArmureFragile());
    }

    public void initEnnemis(){

        Boss boss = new Boss(420, 2400, 50, 65, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(boss);

        Sentinelle sentinelle1 = new Sentinelle(2130, 2370, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(sentinelle1);

        Sentinelle sentinelle2 = new Sentinelle(2190, 2400, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(sentinelle2);

        Sentinelle sentinelle3 = new Sentinelle(2400, 2370, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(sentinelle3);

        Sentinelle sentinelle4 = new Sentinelle(2250, 2280, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(sentinelle4);

        Sentinelle sentinelle5 = new Sentinelle(2250, 2190, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(sentinelle5);

        Skeleton skeleton = new Skeleton(500, 120, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(skeleton);

        Skeleton skeleton2 = new Skeleton(1400, 1400, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(skeleton2);

        Skeleton skeleton3 = new Skeleton(500, 1400, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(skeleton3);

        Skeleton skeleton4 = new Skeleton(500, 1200, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(skeleton4);

        Kami kami = new Kami(1500, 1500, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(kami);

        Kami kami2 = new Kami(2970, 0, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(kami2);

        Kami kami3 = new Kami(2100, 600, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(kami3);

        Kami kami4 = new Kami(2500, 1500, terrain.getColumns(),  terrain.getRows(), getBfs());
        addEnnemi(kami4);
    }
}
