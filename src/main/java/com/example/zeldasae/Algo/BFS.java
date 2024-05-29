package com.example.zeldasae.Algo;

import com.example.zeldasae.modele.Monde;

import java.util.*;

public class BFS {

    private Map<Point, Point> parentMap;

    private static final int[] dLigne = {-1, 1, 0, 0};
    private static final int[] dColonne = {0, 0, -1, 1};

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

            // Exploration des voisins
            for (int i = 0; i < 4; i++) {
                int nx = x + dLigne[i];
                int ny = y + dColonne[i];

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

    // Méthode pour reconstruire le chemin à partir de la destination en utilisant le parentMap
    private List<Point> constructChemin(Point dest) {
        LinkedList<Point> chemin = new LinkedList<>();
        for (Point actuel = dest; actuel != null; actuel = parentMap.get(actuel)) {
            chemin.add(0, actuel);
        }
        return chemin;
    }

    private int[][] convertListTo2DArray(ArrayList<Integer> liste, int ligne, int colonne) {
        int[][] tab = new int[ligne][colonne];
        int index = 0;

        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                tab[j][i] = liste.get(index++);
            }
        }

        return tab;
    }

    public void lanceAlgo(Monde monde, int colonnes, int lignes){
        int[][] grille = convertListTo2DArray(monde.getTerrain().getMap(), colonnes, lignes);
        int x = (monde.getJoueur().getX()/30)%(30*40);
        int y = (monde.getJoueur().getY()/30)%(30*40);
        bfs2D(grille, new Point(x, y));
    }

    public int[] prochainMouvement(int[] src){
        List<Point> chemin = constructChemin(new Point(src[0], src[1]));
        System.out.println(chemin);
//        if (chemin.size() == 2)
//            return new int[] {chemin.get(1).getX(), chemin.get(1).getY()};
        if (chemin.size() > 1)
            return new int[] {chemin.get(chemin.size()-2).getX(), chemin.get(chemin.size()-2).getY()};
        return null;
    }
}
