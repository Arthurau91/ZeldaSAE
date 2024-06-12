package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.zeldasae.Algo.BFS;

import java.util.ArrayList;
import java.util.List;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    private ArrayList<Ennemi> listeEnnemis;
    private ObservableList<Projectile> listeProjectiles;
    private ObservableList<Collectible> listeCollectibles;
    private BFS bfs;
    private List<Coffre> coffres;

    /**
     * Constructeur de la classe Monde
     */
    public Monde(Joueur joueur, BFS bfs, int rows) {

        this.joueur = joueur;
        this.terrain = new Terrain(rows);
        this.listeEnnemis = new ArrayList<>();
        this.listeProjectiles = FXCollections.observableArrayList();
        this.listeCollectibles = FXCollections.observableArrayList();
        this.bfs = bfs;
        this.coffres = new ArrayList<>();
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public ArrayList<Ennemi> getListeEnnemis() {
        return this.listeEnnemis;
    }

    public void retirerEntite(Entite entite) {
        getListeEnnemis().remove(entite);
    }

    public BFS getBfs() {
        return bfs;
    }

    /**
     * Méthode qui ajoute un ennemi à la liste d'ennemis du monde
     * @param ennemi ennemi qui va être rajouté à la liste d'ennemis du monde
     */
    public void addEnnemi(Ennemi ennemi) {
        this.listeEnnemis.add(ennemi);
    }

    /**
     * Méthode qui lance le déplacement de tous les ennemis du monde
     */
    public void deplacementEnnemi(){
        for (Ennemi ennemi : this.listeEnnemis) {
            ennemi.deplacement(this);
        }
    }

    public void setMap(ArrayList<Integer> map) {
        this.terrain.setMap(map);
    }

    public int[] cooBloc(int x, int y){
        int coo = terrain.changeCoo(x, y);
        int coobloc = 0;
        int coovide = 0;
        String direction = joueur.getDirection();
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

    public ObservableList<Projectile> getListeProjectiles() {
        return this.listeProjectiles;
    }

    public ObservableList<Collectible> getListeCollectibles() {
        return listeCollectibles;
    }

    public void ajouterProjectile(Projectile p) {
        this.listeProjectiles.add(p);
    }

    public void retirerProjectile(Projectile p) {
        this.listeProjectiles.remove(p);
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = null;
    }

    public void deplacerProjectilesVue() {
        for (int i = 0; i < this.listeProjectiles.size(); i++) {
            this.listeProjectiles.get(i).deplacerProjectile(this.getListeEnnemis());
            if (this.listeProjectiles.get(i).isObstacleTouche() || !this.listeProjectiles.get(i).dansMap(this.terrain)) {
                retirerProjectile(this.listeProjectiles.get(i));
                i--;
            }
        }
    }

    public void ajouterCollectible(Collectible c) {
        this.listeCollectibles.add(c);
    }

    public void addCoffre(Coffre coffre) {
        this.coffres.add(coffre);
    }

    public List<Coffre> getCoffres() {
        return coffres;
    }

    public Coffre coffreOuvert() {
        for (Coffre coffre : this.coffres) {
            if (coffre.isEstOuvert())
                return coffre;
        }

        return null;
    }

}
