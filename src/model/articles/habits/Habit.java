package model.articles.habits;

import model.articles.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Habit extends Article {
    List<Taille> tailles = new ArrayList<>();
    List<MyColor> couleurs = new ArrayList<>();

    public Habit(String nom, double prix, List<MyColor> couleurs, List<Taille> tailles) {
        super(nom, prix);
        if(tailles != null) {
            this.tailles.addAll(tailles);
        }
        if(couleurs != null){
            this.couleurs.addAll(couleurs);
        }
    }

    public List<Taille> getTailles() {
        return Collections.unmodifiableList(tailles);
    }

    public List<MyColor> getCouleurs() {
        return Collections.unmodifiableList(couleurs);
    }

    public void supprimerTaille(Taille taille){

    }
}
