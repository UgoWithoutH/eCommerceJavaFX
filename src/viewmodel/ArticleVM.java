package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class ArticleVM {
    private StringProperty nom = new SimpleStringProperty();
        public String getNom() {return nom.get();}
        public StringProperty nomProperty() {return nom;}
        public void setNom(String nom) {this.nom.set(nom);}
    private DoubleProperty prix = new SimpleDoubleProperty();
        public double getPrix() {return prix.get();}
        public DoubleProperty prixProperty() {return prix;}
        public void setPrix(double prix) {this.prix.set(prix);}

    public ArticleVM(String nom, double prix) {
        setNom(nom);
        setPrix(prix);
    }
}
