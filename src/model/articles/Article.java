package model.articles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class Article implements Serializable {

    protected transient PropertyChangeSupport support;
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
        getSupport().firePropertyChange(PROP_NOM, old, this.nom);
    }

    public void setPrix(double prix) {
        double old = prix;
        this.prix = prix;
        getSupport().firePropertyChange(PROP_PRIX, old, this.prix);
    }

    public void ajouterPropertyChangeListener(PropertyChangeListener listener){
        getSupport().addPropertyChangeListener(listener);
    }

    private PropertyChangeSupport getSupport(){
        if(support == null){
            support = new PropertyChangeSupport(this);
        }
        return support;
    }
}
