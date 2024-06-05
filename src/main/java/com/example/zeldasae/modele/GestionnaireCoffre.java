package com.example.zeldasae.modele;

import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.Vue.VueInventaire;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireCoffre {

    private Monde monde;
    private Pane pane;
    private VueInventaire vueInventaire;
    private List<VueCoffre> vueCoffreList;


    public GestionnaireCoffre(Monde monde, Pane pane, VueInventaire vueInventaire) {
        this.monde = monde;
        this.pane = pane;
        this.vueInventaire = vueInventaire;
        this.vueCoffreList = new ArrayList<>();
    }

    public void creerCoffreDansMonde() {
        VueCoffre coffre = creerVueCoffre(0, 30);
        VueCoffre coffre1 = creerVueCoffre(30, 30);
    }

    public VueCoffre creerVueCoffre(int x, int y) {
        Coffre coffre = new Coffre(x, y);
        VueCoffre vueCoffre = new VueCoffre(this.pane, this.monde.getJoueur(), coffre, vueInventaire);
        this.monde.addCoffre(coffre);
        this.vueCoffreList.add(vueCoffre);
        return vueCoffre;
    }

    public List<VueCoffre> getVueCoffreList() {
        return vueCoffreList;
    }
}
