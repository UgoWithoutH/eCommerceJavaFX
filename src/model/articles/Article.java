package model.articles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Article {

    protected PropertyChangeSupport support = new PropertyChangeSupport(this);
    public static final String PROP_NOM = "NOM";
    public static final String PROP_PRIX = "PRIX";

    public Article(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    private String nom;
    private double prix;

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setNom(String nom) {
        String old = nom;
        this.nom = nom;
        support.firePropertyChange(PROP_NOM, old, this.nom);
    }

    public void setPrix(double prix) {
        double old = prix;
        this.prix = prix;
        support.firePropertyChange(PROP_PRIX, old, this.prix);
    }

    public void ajouterPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
