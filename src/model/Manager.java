package model;

import model.articles.Article;
import model.filtres.Filtreur;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static final String PROP_ARTICLES = "ARTICLES";
    private List<Article> articles = new ArrayList<>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void ajouterArticle(Article article){
        articles.add(article);
        int index = articles.indexOf(article);
        support.fireIndexedPropertyChange(PROP_ARTICLES, index, articles.size() > 1 ? articles.get(index-1) : null, articles.get(index));
    }

    public void supprimerArticle(Article article){
        articles.remove(article);
    }

    public List<Article> filtrer(List<Article> articles, Filtreur filtre){
        return filtre.filtrer(articles);
    }

    public void ajouterPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

}
