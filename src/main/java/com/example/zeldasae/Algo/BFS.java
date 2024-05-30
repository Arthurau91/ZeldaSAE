package com.example.zeldasae.Algo;

import com.example.zeldasae.modele.Monde;

import java.util.*;

public class BFS {

    private Map<Point, Point> parentMap;

    private static final int[] dLigne = {-1, 1, 0, 0};
    private static final int[] dColonne = {0, 0, -1, 1};

    /**
     * Algo BFS
     * @param grille Grille contenant la map (murs, endroits pratiquables...)
     * @param src tableau avec les coordonnées du joueur
     */
    public void bfs2D(int[][] grille, Point src) {
        int ligne = grille.length;
        int colonne = grille[0].length;
        boolean[][] visite = new boolean[ligne][colonne];

        // Création de la file pour BFS
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(src);
        visite[src.getX()][src.getY()] = true;

        // Pour garder la trace des parents pour reconstruire le chemin
        Map<Point, Point> parentMap = new HashMap<>();
        parentMap.put(src, null);

        // Boucle principale de BFS
        while (!queue.isEmpty()) {
            Point actuel = queue.poll();
            int x = actuel.getX();
            int y = actuel.getY();
            ArrayList<Integer> directionAleatoire = new ArrayList<>();
            directionAleatoire.add(0);
            directionAleatoire.add(1);
            directionAleatoire.add(2);
            directionAleatoire.add(3);

            // Exploration des voisins
            for (int i = 0; i < 4; i++) {
                Random random = new Random();
                int nr = random.nextInt(directionAleatoire.size());
                int d = directionAleatoire.get(nr);
                directionAleatoire.remove(nr);
                int nx = x + dLigne[d];
                int ny = y + dColonne[d];

                // Vérifier si le voisin est dans les limites du tableau et non visité
                if (nx >= 0 && nx < ligne && ny >= 0 && ny < colonne && !visite[nx][ny] && grille[nx][ny] == 232) {
                    Point voisin = new Point(nx, ny);
                    queue.add(voisin);
                    visite[nx][ny] = true;
                    parentMap.put(voisin, actuel);
                }
            }
        }

        this.parentMap = parentMap;
    }

    /**
     * Méthode pour reconstruire le chemin à partir de la destination en utilisant le parentMap
     * @param dest Le point à atteindre à la fin du chemin
     * @return Liste de Points du chemin
     */
    private List<Point> constructChemin(Point dest) {
        LinkedList<Point> chemin = new LinkedList<>();
        for (Point actuel = dest; actuel != null; actuel = parentMap.get(actuel)) {
            chemin.add(0, actuel);
        }
        return chemin;
    }

    /**
     * Méthode qui converti les ArrayList<Integer> en tableau de int à 2 dimensions
     * @param liste Liste d'origine
     * @param ligne nombre de lignes
     * @param colonne nombre de colonnes
     * @return tableau de int à 2 dimensions
     */
    private int[][] convertListTo2DArray(ArrayList<Integer> liste, int ligne, int colonne) {
        int[][] tab = new int[ligne][colonne];
        int index = 0;

        for (int j = 0; j < colonne; j++) {
            for (int i = 0; i < ligne; i++) {
                tab[i][j] = liste.get(index++);
            }
        }

        return tab;
    }

    /**
     * Méthode qui lance le BFS pour mettre à jour la Map
     * @param monde Monde du jeu
     * @param colonnes nombre de colonnes
     * @param lignes nombre de lignes
     */
    public void lanceAlgo(Monde monde, int colonnes, int lignes){
        int[][] grille = convertListTo2DArray(monde.getTerrain().getMap(), colonnes, lignes);
        int x = (monde.getJoueur().getX()/30)%(30*lignes);
        int y = (monde.getJoueur().getY()/30)%(30*colonnes);
        bfs2D(grille, new Point(x, y));
    }

    /**
     * Méthode qui retourne un tableau avec les coordonées du prochain mouvement de l'ennemi sous forme {x, y}
     * @param src tableau avec les coordonnées de l'ennemi
     * @return tableau avec les coordonées de la prochaine destination de l'ennemi sous forme {x, y}
     */
    public int[] prochainMouvement(int[] src){
        List<Point> chemin = constructChemin(new Point(src[0], src[1]));
        if (chemin.size() > 1)
            return new int[] {chemin.get(chemin.size()-2).getX(), chemin.get(chemin.size()-2).getY()};
        return null;
    }
}
