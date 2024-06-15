package com.example.zeldasae.Vue.VueArmes;

import com.example.zeldasae.Vue.VueArmes.VueArme;
import com.example.zeldasae.modele.Arme;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Epee;
import com.example.zeldasae.modele.armes.Hache;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.scene.layout.Pane;

public class CreateurVueArme {

//    this.vueArme = new VueArme(this.map.getJoueur(), this.paneEntites, map, this.mapPane);

    private Joueur joueur;
    private Pane paneEntites;
    private Monde map;
    private Pane mapPane;

    public CreateurVueArme(Joueur joueur, Pane paneEntites, Monde map, Pane mapPane) {
        this.joueur = joueur;
        this.paneEntites = paneEntites;
        this.map = map;
        this.mapPane = mapPane;
    }

    public VueArme creerVueArme() {
        Arme armeActuelle = this.joueur.getInv().getArmeActuelle();
        if(armeActuelle instanceof Epee) {
            return new VueEpee(joueur, paneEntites, map, mapPane);
        }
        else if(armeActuelle instanceof Hache) {
            return new VueHache(joueur, paneEntites, map, mapPane);
        }
        return null;
    }
}
