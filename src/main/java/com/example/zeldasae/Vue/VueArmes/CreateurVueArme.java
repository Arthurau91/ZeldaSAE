package com.example.zeldasae.Vue.VueArmes;

import com.example.zeldasae.Vue.VueProjectile;
import com.example.zeldasae.modele.armes.Arme;
import com.example.zeldasae.modele.Monde;
import com.example.zeldasae.modele.armes.Bombe;
import com.example.zeldasae.modele.armes.Epee;
import com.example.zeldasae.modele.armes.Hache;
import com.example.zeldasae.modele.entities.Joueur;
import javafx.scene.layout.Pane;

public class CreateurVueArme {

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
        else if(armeActuelle instanceof Bombe) {
            return new VueBombe(joueur, paneEntites, map, mapPane);
        }
        return null;
    }

}
