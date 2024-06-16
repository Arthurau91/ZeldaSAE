package com.example.zeldasae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HitBoxTest {

    private HitBox hitBox;
    private IntegerProperty x;
    private IntegerProperty y;
    private Terrain terrain;
    private Coffre coffre;

    @BeforeEach
    void setUp() {
        x = new SimpleIntegerProperty(50);
        y = new SimpleIntegerProperty(50);
        hitBox = new HitBox(30, 30, x, y);
        terrain = new Terrain(10);
        coffre = new Coffre(50, 50, 1);
    }

    @Test
    void testGetX() {
        assertEquals(50, hitBox.getX());
    }

    @Test
    void testGetY() {
        assertEquals(50, hitBox.getY());
    }

    @Test
    void testSetX() {
        hitBox.setX(100);
        assertEquals(100, hitBox.getX());

        hitBox.setX(-10);
        assertEquals(-10, hitBox.getX());

    }

    @Test
    void testSetY() {
        hitBox.setY(100);
        assertEquals(100, hitBox.getY());

        hitBox.setY(-10);
        assertEquals(-10, hitBox.getY());
    }


    @Test
    void testCheckBord() {

        assertTrue(hitBox.checkBord("up", 12, 12, 1));
        assertTrue(hitBox.checkBord("down", 12, 12, 1));
        assertTrue(hitBox.checkBord("left", 12, 12, 1));
        assertTrue(hitBox.checkBord("right", 12, 12, 1));

        assertFalse(hitBox.checkBord("up", 1, 1, 51));
        assertTrue(hitBox.checkBord("down", 1, 1, 51));
        assertTrue(hitBox.checkBord("left", 1, 1, 1));
        assertTrue(hitBox.checkBord("right", 1, 1, 1));

        hitBox.setX(0);
        assertFalse(hitBox.checkBord("left", 10, 10, 10));
        hitBox.setX(70);
        assertTrue(hitBox.checkBord("right", 10, 50, 50));

        hitBox.setY(0);
        assertFalse(hitBox.checkBord("up", 10, 10, 10));
        hitBox.setY(70);
        assertTrue(hitBox.checkBord("down", 10, 50, 50));

        hitBox.setX(-10);
        assertFalse(hitBox.checkBord("left", 10, 10, 10));
        hitBox.setY(-10);
        assertFalse(hitBox.checkBord("up", 10, 10, 10));

        hitBox.setX(90);
        assertTrue(hitBox.checkBord("right", 10, 10, 10));
        hitBox.setY(90);
        assertTrue(hitBox.checkBord("down", 10, 10, 10));

    }

    @Test
    void testContient() {

        assertTrue(hitBox.contient(51, 51));
        assertTrue(hitBox.contient(79, 79));

        assertFalse(hitBox.contient(50, 50));
        assertFalse(hitBox.contient(80, 80));
        assertFalse(hitBox.contient(30, 30));

        assertFalse(hitBox.contient(49, 49));
        assertFalse(hitBox.contient(81, 81));

        assertFalse(hitBox.contient(50, 51));
        assertFalse(hitBox.contient(79, 80));
    }

    @Test
    void testEstDedansEgal() {

        assertTrue(hitBox.estDedansEgal(50, 50));
        assertTrue(hitBox.estDedansEgal(80, 80));
        assertTrue(hitBox.estDedansEgal(50, 80));
        assertTrue(hitBox.estDedansEgal(80, 50));

        assertFalse(hitBox.estDedansEgal(81, 81));

        assertFalse(hitBox.estDedansEgal(-50, -50));
        assertFalse(hitBox.estDedansEgal(100, 100));

        assertTrue(hitBox.estDedansEgal(60, 60));

        assertFalse(hitBox.estDedansEgal(30, 50));
        assertFalse(hitBox.estDedansEgal(50, 30));
        assertFalse(hitBox.estDedansEgal(80, 30));
        assertTrue(hitBox.estDedansEgal(50, 80));

        assertFalse(hitBox.estDedansEgal(29, 29));
        assertFalse(hitBox.estDedansEgal(81, 81));
        assertFalse(hitBox.estDedansEgal(29, 81));
        assertFalse(hitBox.estDedansEgal(81, 29));

        assertFalse(hitBox.estDedansEgal(30, 50));
        assertFalse(hitBox.estDedansEgal(50, 30));
        assertTrue(hitBox.estDedansEgal(80, 50));
        assertTrue(hitBox.estDedansEgal(50, 80));
    }


    @Test
    void testEstDedansHitbox() {

        HitBox hitBox = new HitBox(30, 30, new SimpleIntegerProperty(50), new SimpleIntegerProperty(50));
        HitBox hitBox1 = new HitBox(30, 30, new SimpleIntegerProperty(60), new SimpleIntegerProperty(60));
        HitBox hitBox2 = new HitBox(30, 30, new SimpleIntegerProperty(100), new SimpleIntegerProperty(100));
        HitBox hitBox3 = new HitBox(20, 20, new SimpleIntegerProperty(70), new SimpleIntegerProperty(70));
        HitBox hitBox4 = new HitBox(5, 5, new SimpleIntegerProperty(10), new SimpleIntegerProperty(10));
        HitBox hitBox5 = new HitBox(30, 30, new SimpleIntegerProperty(50), new SimpleIntegerProperty(50));
        HitBox hitBox6 = new HitBox(20, 20, new SimpleIntegerProperty(10), new SimpleIntegerProperty(10));
        HitBox hitBox7 = new HitBox(30, 30, new SimpleIntegerProperty(50), new SimpleIntegerProperty(50));

        assertTrue(hitBox.estDedansHitbox(hitBox1));
        assertFalse(hitBox.estDedansHitbox(hitBox2));
        assertTrue(hitBox.estDedansHitbox(hitBox3));
        assertFalse(hitBox.estDedansHitbox(hitBox4));
        assertTrue(hitBox.estDedansHitbox(hitBox5));
        assertFalse(hitBox.estDedansHitbox(hitBox6));
        assertTrue(hitBox.estDedansHitbox(hitBox7));
    }

    @Test
    void testPivote() {

        hitBox.pivote();
        assertEquals(30, hitBox.getHaut());
        assertEquals(30, hitBox.getLarge());
        assertTrue(hitBox.isEstPivote());

        hitBox.pivote();
        assertEquals(30, hitBox.getHaut());
        assertEquals(30, hitBox.getLarge());
        assertFalse(hitBox.isEstPivote());

        hitBox = new HitBox(50, 20, x, y);
        hitBox.pivote();
        assertEquals(20, hitBox.getLarge());
        assertEquals(50, hitBox.getHaut());
        assertTrue(hitBox.isEstPivote());

        hitBox = new HitBox(1, 1, x, y);
        hitBox.pivote();
        assertEquals(1, hitBox.getHaut());
        assertEquals(1, hitBox.getLarge());
        assertTrue(hitBox.isEstPivote());

        hitBox = new HitBox(100, 100, x, y);
        hitBox.pivote();
        assertEquals(100, hitBox.getHaut());
        assertEquals(100, hitBox.getLarge());
        assertTrue(hitBox.isEstPivote());

        hitBox = new HitBox(100, 100, x, y);
        hitBox.pivote();
        hitBox.pivote();
        assertEquals(100, hitBox.getHaut());
        assertEquals(100, hitBox.getLarge());
        assertFalse(hitBox.isEstPivote());
    }



    @Test
    public void testCheckColision() {

        assertFalse(hitBox.checkColision("left", 10, terrain));
        assertFalse(hitBox.checkColision("right", 10, terrain));
        assertFalse(hitBox.checkColision("up", 10, terrain));
        assertFalse(hitBox.checkColision("down", 10, terrain));

        hitBox.setX(30);
        hitBox.setY(30);

        assertFalse(hitBox.checkColision("left", 10, terrain));
        assertFalse(hitBox.checkColision("right", 10, terrain));
        assertFalse(hitBox.checkColision("up", 10, terrain));
        assertFalse(hitBox.checkColision("down", 10, terrain));

        hitBox.setY(0);
        assertFalse(hitBox.checkColision("down", 10, terrain));

        hitBox.setY(terrain.getMap().size() * 30 - hitBox.getHaut());
        assertFalse(hitBox.checkColision("up", 10, terrain));

        hitBox.setX(0);
        assertFalse(hitBox.checkColision("right", 10, terrain));

        hitBox.setX(terrain.getMap().size() * 30 - hitBox.getLarge());
        assertFalse(hitBox.checkColision("left", 10, terrain));

    }

    @Test
    void testCoffreProximite() {

        assertTrue(hitBox.coffreProximite(coffre, 0));

        assertTrue(hitBox.coffreProximite(coffre, 1));
        assertTrue(hitBox.coffreProximite(coffre, 10));

        assertTrue(hitBox.coffreProximite(coffre, 5));
        assertTrue(hitBox.coffreProximite(coffre, 9));

        hitBox.setX(80);
        hitBox.setY(50);
        assertTrue(hitBox.coffreProximite(coffre, 30));

        hitBox.setX(50);
        hitBox.setY(80);
        assertTrue(hitBox.coffreProximite(coffre, 30));

        hitBox.setX(100);
        hitBox.setY(100);
        assertFalse(hitBox.coffreProximite(coffre, 30));

        coffre.setX(120);
        coffre.setY(50);
        hitBox.setX(100);
        hitBox.setY(50);
        assertTrue(hitBox.coffreProximite(coffre, 20));
        assertFalse(hitBox.coffreProximite(coffre, 10));

        coffre.setX(50);
        coffre.setY(120);
        hitBox.setX(50);
        hitBox.setY(100);
        assertTrue(hitBox.coffreProximite(coffre, 20));
        assertFalse(hitBox.coffreProximite(coffre, 10));
    }

}