package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;

import java.util.ArrayList;
import java.util.List;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    private ArrayList<Ennemi> listeEnnemis;
    private BFS bfs;
    private List<Coffre> coffres;


    /**
     * Constructeur de la classe Monde
     */
    public Monde(Joueur joueur, BFS bfs) {

        this.joueur = joueur;
        this.terrain = new Terrain();
        this.listeEnnemis = new ArrayList<>();
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

    public void addCoffre(Coffre coffre) {
        this.coffres.add(coffre);
    }

    public List<Coffre> getCoffres() {
        return coffres;
    }
}
