package model.articles.habits;

import model.articles.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Habit extends Article {
    public static final String PROP_TAILLE = "TAILLE";
    public static final String PROP_COULEUR = "COULEUR";
    private List<Taille> tailles = new ArrayList<>();
    private List<MyColor> couleurs = new ArrayList<>();

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

    public void ajouterTaille(Taille taille){
        if(!tailles.contains(taille)){
            tailles.add(taille);
            int index = tailles.indexOf(taille);
            support.fireIndexedPropertyChange(PROP_TAILLE, index, tailles.size() > 1 ? tailles.get(index-1) : null, taille);
        }
    }

    public void supprimerTaille(Taille taille){
        int index = tailles.indexOf(taille);
        tailles.remove(taille);
        support.fireIndexedPropertyChange(PROP_TAILLE, index, taille, null);
    }

    public void ajouterCouleur(MyColor couleur){
        if(!couleurs.contains(couleur)){
            couleurs.add(couleur);
            int index = couleurs.indexOf(couleur);
            support.fireIndexedPropertyChange(PROP_COULEUR, index, couleurs.size() > 1 ? couleurs.get(index-1) : null, couleurs);
        }
    }

    public void supprimerCouleur(MyColor couleur){
        int index = couleurs.indexOf(couleur);
        couleurs.remove(couleur);
        support.fireIndexedPropertyChange(PROP_COULEUR, index, couleur, null);
    }
}
