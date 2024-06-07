package com.example.zeldasae.modele;

import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.Vue.VueInventaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireCoffre {

    private Monde monde;
    private Pane pane;
    private VueInventaire vueInventaire;
    private ArrayList<VueCoffre> vueCoffreList;
    private ArrayList<Coffre> coffreList;


    public GestionnaireCoffre(Monde monde, Pane pane, VueInventaire vueInventaire) {
        this.monde = monde;
        this.pane = pane;
        this.vueInventaire = vueInventaire;
        this.vueCoffreList = new ArrayList<>();
        this.coffreList = new ArrayList<>();
    }

    public void creerCoffreDansMonde() {
        creerVueCoffre(0, 30, 0);
        creerVueCoffre(30, 30, 1);
    }

    public VueCoffre creerVueCoffre(int x, int y, int id) {
        Coffre coffre = new Coffre(x, y, id);
        VueCoffre vueCoffre = new VueCoffre(this.pane, this.monde.getJoueur(), coffre, vueInventaire);
        this.monde.addCoffre(coffre);
        this.coffreList.add(coffre);
        this.vueCoffreList.add(vueCoffre);
        return vueCoffre;
    }

    public List<VueCoffre> getVueCoffreList() {
        return vueCoffreList;
    }

    public ArrayList<Coffre> getCoffreList() {
        return coffreList;
    }
}
