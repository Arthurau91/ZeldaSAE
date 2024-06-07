package com.example.zeldasae.modele;

import com.example.zeldasae.Algo.BFS;

import java.util.ArrayList;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    private ArrayList<Ennemi> listeEnnemis;
    private BFS bfs;


    /**
     * Constructeur de la classe Monde
     */
    public Monde(Joueur joueur, BFS bfs) {

        this.joueur = joueur;
        this.terrain = new Terrain();
        this.listeEnnemis = new ArrayList<>();
        this.bfs = bfs;
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

    public int[] cooBloc(int coo){
        ArrayList<Integer> m1 = getTerrain().getMap();
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
}
