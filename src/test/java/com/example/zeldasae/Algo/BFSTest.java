package com.example.zeldasae.Algo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    private BFS bfs;

    @BeforeEach
    public void setUp() {
        bfs = new BFS();
    }

    @Test
    public void testBFS2D() {

        // Grille 1

        int[][] grille1 = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        Point source1 = new Point(0, 0);
        bfs.bfs2D(grille1, source1);
        Map<Point, Point> carteParents1 = bfs.getParentMap();

        Map<Point, Point> cheminsAttendus1 = new HashMap<>();
        cheminsAttendus1.put(new Point(0, 0), null);
        cheminsAttendus1.put(new Point(0, 1), new Point(0, 0));
        cheminsAttendus1.put(new Point(1, 1), new Point(0, 1));
        cheminsAttendus1.put(new Point(2, 1), new Point(1, 1));
        cheminsAttendus1.put(new Point(2, 0), new Point(2, 1));
        cheminsAttendus1.put(new Point(2, 2), new Point(2, 1));
        cheminsAttendus1.put(new Point(2, 3), new Point(2, 2));
        cheminsAttendus1.put(new Point(3, 3), new Point(2, 3));

        for (Map.Entry<Point, Point> entree : cheminsAttendus1.entrySet()) {
            Point point = entree.getKey();
            Point parentAttendu = entree.getValue();
            Point parentActuel = carteParents1.get(point);
            assertEquals(parentAttendu, parentActuel);
        }

        // Grille 2

        int[][] grille2 = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        Point source2 = new Point(0, 0);
        bfs.bfs2D(grille2, source2);
        Map<Point, Point> carteParents2 = bfs.getParentMap();

        Map<Point, Point> cheminsAttendus2 = new HashMap<>();
        cheminsAttendus2.put(new Point(0, 0), null);
        cheminsAttendus2.put(new Point(1, 0), new Point(0, 0));
        cheminsAttendus2.put(new Point(2, 0), new Point(1, 0));
        cheminsAttendus2.put(new Point(2, 1), new Point(2, 0));
        cheminsAttendus2.put(new Point(2, 2), new Point(2, 1));
        cheminsAttendus2.put(new Point(1, 2), new Point(2, 2));
        cheminsAttendus2.put(new Point(0, 2), new Point(1, 2));

        for (Map.Entry<Point, Point> entree : cheminsAttendus2.entrySet()) {
            Point point = entree.getKey();
            Point parentAttendu = entree.getValue();
            Point parentActuel = carteParents2.get(point);
            assertEquals(parentAttendu, parentActuel);
        }

        // Grille 3

        int[][] grille3 = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        Point source3 = new Point(0, 0);
        bfs.bfs2D(grille3, source3);
        Map<Point, Point> carteParents3 = bfs.getParentMap();

        Point point3 = carteParents3.get(0);
        assertEquals(carteParents3.get(point3), null);


        // Grille 4

        int[][] grille4 = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        Point source4 = new Point(0, 0);
        bfs.bfs2D(grille4, source4);
        Map<Point, Point> carteParents4 = bfs.getParentMap();

        // Chemins attendus pour chaque point selon le parcours BFS
        Map<Point, Point> cheminsAttendus4 = new HashMap<>();
        cheminsAttendus4.put(new Point(0, 0), null);
        cheminsAttendus4.put(new Point(0, 1), new Point(0, 0));
        cheminsAttendus4.put(new Point(1, 1), new Point(0, 1));
        cheminsAttendus4.put(new Point(2, 1), new Point(1, 1));
        cheminsAttendus4.put(new Point(2, 0), new Point(2, 1));
        cheminsAttendus4.put(new Point(2, 2), new Point(2, 1));
        cheminsAttendus4.put(new Point(2, 3), new Point(2, 2));
        cheminsAttendus4.put(new Point(3, 3), new Point(2, 3));

        for (Map.Entry<Point, Point> entree : cheminsAttendus4.entrySet()) {
            Point point = entree.getKey();
            Point parentAttendu = entree.getValue();
            Point parentActuel = carteParents4.get(point);
            assertEquals(parentAttendu, parentActuel);
        }

        // Grille 5

        int[][] grille5 = {
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0}
        };
        Point source5 = new Point(0, 0);
        bfs.bfs2D(grille5, source5);
        Map<Point, Point> carteParents5 = bfs.getParentMap();

        Map<Point, Point> cheminsAttendus5 = new HashMap<>();
        cheminsAttendus5.put(new Point(0, 0), null);
        cheminsAttendus5.put(new Point(0, 1), new Point(0, 0));
        cheminsAttendus5.put(new Point(1, 1), new Point(0, 1));
        cheminsAttendus5.put(new Point(2, 1), new Point(1, 1));
        cheminsAttendus5.put(new Point(2, 0), new Point(2, 1));
        cheminsAttendus5.put(new Point(2, 2), new Point(2, 1));
        cheminsAttendus5.put(new Point(2, 3), new Point(2, 2));
        cheminsAttendus5.put(new Point(3, 3), new Point(2, 3));

        for (Map.Entry<Point, Point> entree : cheminsAttendus5.entrySet()) {
            Point point = entree.getKey();
            Point parentAttendu = entree.getValue();
            Point parentActuel = carteParents5.get(point);
            assertEquals(parentAttendu, parentActuel);
        }


        // Grille 6

        int[][] grille6 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        Point source6 = new Point(0, 0);
        bfs.bfs2D(grille6, source6);
        Map<Point, Point> carteParents6 = bfs.getParentMap();

        Point point6 = carteParents6.get(0);
        assertEquals(point6, null);


    }


    @Test
    void testConstructChemin() {

        // Test 1

        Map<Point, Point> parentMap1 = new HashMap<>();
        parentMap1.put(new Point(1, 0), new Point(0, 0));
        parentMap1.put(new Point(0, 0), null);
        Point destination1 = new Point(1, 0);
        List<Point> expected1 = List.of(new Point(0, 0), new Point(1, 0));
        LinkedList<Point> chemin1 = new LinkedList<>();
        for (Point actuel = destination1; actuel != null; actuel = parentMap1.get(actuel)) {
            chemin1.addFirst(actuel);
        }
        assertEquals(expected1, chemin1);

        // Test 2

        Map<Point, Point> parentMap2 = new HashMap<>();
        parentMap2.put(new Point(2, 0), new Point(1, 0));
        parentMap2.put(new Point(1, 0), new Point(0, 0));
        parentMap2.put(new Point(0, 0), null);
        Point destination2 = new Point(2, 0);
        List<Point> expected2 = List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0));
        LinkedList<Point> chemin2 = new LinkedList<>();
        for (Point actuel = destination2; actuel != null; actuel = parentMap2.get(actuel)) {
            chemin2.addFirst(actuel);
        }
        assertEquals(expected2, chemin2);

        // Test 3

        Map<Point, Point> parentMap3 = new HashMap<>();
        Point destination3 = new Point(0, 0);  // Aucun parent pour (0, 0)
        List<Point> expected3 = List.of(new Point(0, 0));
        LinkedList<Point> chemin3 = new LinkedList<>();
        for (Point actuel = destination3; actuel != null; actuel = parentMap3.get(actuel)) {
            chemin3.addFirst(actuel);
        }
        assertEquals(expected3, chemin3);

        // Test 4

        Map<Point, Point> parentMap4 = new HashMap<>();
        parentMap4.put(new Point(1, 1), new Point(0, 0));
        parentMap4.put(new Point(2, 2), new Point(1, 1));
        Point destination4 = new Point(2, 0);  // Pas de chemin possible jusqu'à (2, 0)
        List<Point> expected4 = List.of(new Point(2, 0));
        LinkedList<Point> chemin4 = new LinkedList<>();
        for (Point actuel = destination4; actuel != null; actuel = parentMap4.get(actuel)) {
            chemin4.addFirst(actuel);
        }
        assertEquals(expected4, chemin4);

        // Test 5

        Map<Point, Point> parentMap5 = new HashMap<>();
        parentMap5.put(new Point(1, 0), new Point(0, 0));
        parentMap5.put(new Point(2, 0), new Point(1, 0));
        parentMap5.put(new Point(2, 1), new Point(2, 0));
        parentMap5.put(new Point(2, 2), new Point(2, 1));
        parentMap5.put(new Point(1, 2), new Point(2, 2));
        parentMap5.put(new Point(0, 2), new Point(1, 2));
        Point destination5 = new Point(2, 2);  // Chemin bloqué à partir de (2, 2)
        LinkedList<Point> chemin5 = new LinkedList<>();
        for (Point actuel = destination5; actuel != null; actuel = parentMap5.get(actuel)) {
            chemin5.addFirst(actuel);
        }
        Point dernierPoint = chemin5.getLast();
        assertEquals(destination5.getX(), dernierPoint.getX());
        assertEquals(destination5.getY(), dernierPoint.getY());

        // Test 6

        Map<Point, Point> parentMap6 = new HashMap<>();
        parentMap6.put(new Point(5, 5), new Point(4, 5));
        parentMap6.put(new Point(4, 5), new Point(3, 5));
        parentMap6.put(new Point(3, 5), new Point(2, 5));
        parentMap6.put(new Point(2, 5), new Point(1, 5));
        parentMap6.put(new Point(1, 5), new Point(0, 5));
        parentMap6.put(new Point(0, 5), null);
        Point destination6 = new Point(5, 5);  // Chemin avec un grand nombre de points
        List<Point> expected6 = List.of(new Point(0, 5), new Point(1, 5), new Point(2, 5),
                new Point(3, 5), new Point(4, 5), new Point(5, 5));
        LinkedList<Point> chemin6 = new LinkedList<>();
        for (Point actuel = destination6; actuel != null; actuel = parentMap6.get(actuel)) {
            chemin6.addFirst(actuel);
        }
        assertEquals(expected6, chemin6);

        // Test 7

        Map<Point, Point> parentMap7 = new HashMap<>();
        Point destination7 = new Point(0, 0);  // Chemin pour le point d'origine (0, 0)
        List<Point> expected7 = List.of(new Point(0, 0));
        LinkedList<Point> chemin7 = new LinkedList<>();
        for (Point actuel = destination7; actuel != null; actuel = parentMap7.get(actuel)) {
            chemin7.addFirst(actuel);
        }
        assertEquals(expected7, chemin7);

        // Test 8

        Map<Point, Point> parentMap8 = new HashMap<>();
        parentMap8.put(new Point(1, 0), new Point(0, 0));
        Point destination8 = new Point(1, 0);  // Chemin pour un seul point avec un parent unique
        List<Point> expected8 = List.of(new Point(0, 0), new Point(1, 0));
        LinkedList<Point> chemin8 = new LinkedList<>();
        for (Point actuel = destination8; actuel != null; actuel = parentMap8.get(actuel)) {
            chemin8.addFirst(actuel);
        }
        assertEquals(expected8, chemin8);

        // Test 9

        Map<Point, Point> parentMap9 = new HashMap<>();
        parentMap9.put(new Point(2, 1), new Point(1, 1));
        parentMap9.put(new Point(1, 1), new Point(0, 1));
        parentMap9.put(new Point(0, 1), null);
        Point destination9 = new Point(2, 1);  // Chemin pour un point au milieu
        List<Point> expected9 = List.of(new Point(0, 1), new Point(1, 1), new Point(2, 1));
        LinkedList<Point> chemin9 = new LinkedList<>();
        for (Point actuel = destination9; actuel != null; actuel = parentMap9.get(actuel)) {
            chemin9.addFirst(actuel);
        }
        assertEquals(expected9, chemin9);

        // Test 10

        Map<Point, Point> parentMap10 = new HashMap<>();
        parentMap10.put(new Point(0, 1), new Point(1, 0));
        Point destination10 = new Point(0, -1);  // Chemin vers un point hors de la grille
        List<Point> expected10 = List.of(new Point(0, -1));  // Aucun parent défini pour (0, -1)
        LinkedList<Point> chemin10 = new LinkedList<>();
        for (Point actuel = destination10; actuel != null; actuel = parentMap10.get(actuel)) {
            chemin10.addFirst(actuel);
        }
        assertEquals(expected10, chemin10);
    }

    @Test
    void testProchainMouvement() {

        BFS bfs = new BFS();
        int[][] grille = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        Point source = new Point(0, 0);
        bfs.bfs2D(grille, source);

        int[] src1 = {3, 3};
        int[] expected1 = {2, 3};
        int[] result1 = bfs.prochainMouvement(src1);
        assertArrayEquals(expected1, result1);

        int[] src2 = {2, 2};
        int[] expected2 = {2, 1};
        int[] result2 = bfs.prochainMouvement(src2);
        assertArrayEquals(expected2, result2);

        int[] src3 = {0, 0};
        int[] result3 = bfs.prochainMouvement(src3);
        assertNull(result3);
    }


    @Test
    void testDistanceMouvement() {

        BFS bfs = new BFS();
        int[][] grille = {
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        };
        Point source = new Point(0, 0);
        bfs.bfs2D(grille, source);

        int[] src1 = {3, 3};
        int expectedDistance1 = 7;
        int resultDistance1 = bfs.distanceMouvement(src1);
        assertEquals(expectedDistance1, resultDistance1);

        int[] src2 = {2, 2};
        int expectedDistance2 = 5;
        int resultDistance2 = bfs.distanceMouvement(src2);
        assertEquals(expectedDistance2, resultDistance2);

        int[] src3 = {0, 0};
        int expectedDistance3 = 1;
        int resultDistance3 = bfs.distanceMouvement(src3);
        assertEquals(expectedDistance3, resultDistance3);
    }


}