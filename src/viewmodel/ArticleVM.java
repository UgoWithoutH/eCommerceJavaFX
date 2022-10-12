package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.articles.Article;

import java.beans.PropertyChangeListener;
import java.util.Objects;

public abstract class ArticleVM implements PropertyChangeListener {
    private StringProperty nom = new SimpleStringProperty();
        public String getNom() {return nom.get();}
        public StringProperty nomProperty() {return nom;}
        public void setNom(String nom) {this.nom.set(nom);}
    private DoubleProperty prix = new SimpleDoubleProperty();
        public double getPrix() {return prix.get();}
        public DoubleProperty prixProperty() {return prix;}
        public void setPrix(double prix) {this.prix.set(prix);}
    protected Article model;

    public ArticleVM(Article model) {
        this.model = model;
        setNom(model.getNom());
        setPrix(model.getPrix());
        nomProperty().addListener((obs, oldV, newV) -> model.setNom(newV));
        prixProperty().addListener((obs, oldV, newV) -> model.setPrix(newV.doubleValue()));
        model.ajouterPropertyChangeListener(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVM articleVM = (ArticleVM) o;
        return nom.equals(articleVM.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, model);
    }
}
