package com.example.zeldasae.modele;

import com.example.zeldasae.Vue.VueCoffre;
import com.example.zeldasae.Vue.VueInventaire;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireCoffre {

    private Monde monde;
    private List<Pane> coffrePanes; // Liste des panneaux de coffre où tous les coffres seront ajoutés
    private VueInventaire vueInventaire;
    private List<VueCoffre> vueCoffreList;
    private List<Coffre> coffreList;

    public GestionnaireCoffre(Monde monde, List<Pane> coffrePanes, VueInventaire vueInventaire) {
        this.monde = monde;
        this.coffrePanes = coffrePanes;
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
        Pane coffrePane = new Pane();
        coffrePane.setPrefSize(900, 445);
        coffrePane.setVisible(false);

        VueCoffre vueCoffre = new VueCoffre(coffrePane, this.monde.getJoueur(), coffre, vueInventaire);

        for (Pane pane : coffrePanes) {
            pane.getChildren().add(coffrePane);
        }

        this.monde.addCoffre(coffre);
        this.coffreList.add(coffre);
        this.vueCoffreList.add(vueCoffre);

        return vueCoffre;
    }

    public List<VueCoffre> getVueCoffreList() {
        return vueCoffreList;
    }

    public List<Coffre> getCoffreList() {
        return coffreList;
    }
}