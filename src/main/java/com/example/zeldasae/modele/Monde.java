package com.example.zeldasae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.zeldasae.Algo.BFS;

import java.util.ArrayList;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    private ArrayList<Ennemi> listeEnnemis;
    private ObservableList<Projectile> listeProjectiles;
    private BFS bfs;

    /**
     * Constructeur de la classe Monde
     */
    public Monde(Joueur joueur, BFS bfs, int rows) {

        this.joueur = joueur;
        this.terrain = new Terrain(rows);
        this.listeEnnemis = new ArrayList<>();
        this.bfs = bfs;
        this.listeProjectiles = FXCollections.observableArrayList();
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

    public ObservableList<Projectile> getListeProjectiles() {
        return this.listeProjectiles;
    }

    public void ajouterProjectile(Projectile p) {
        this.listeProjectiles.add(p);
    }

    public void retirerProjectile(Projectile p) {
        this.listeProjectiles.remove(p);
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
}
