package model;

import model.articles.Article;
import model.filtres.Filtreur;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static final String PROP_ARTICLES_AJOUT = "PROP_ARTICLES_AJOUT";
    public static final String PROP_ARTICLES_SUPPR = "PROP_ARTICLES_SUPPR";
    private List<Article> articles = new ArrayList<>();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void ajouterArticle(Article article){
        articles.add(article);
        int index = articles.indexOf(article);
        support.fireIndexedPropertyChange(PROP_ARTICLES_AJOUT, index, articles.size() > 1 ? articles.get(index-1) : null, article);
    }

    public void supprimerArticle(Article article){
        articles.remove(article);
        support.firePropertyChange(PROP_ARTICLES_SUPPR, article, null);
    }

    public List<Article> filtrer(List<Article> articles, Filtreur filtre){
        return filtre.filtrer(articles);
    }

    public void ajouterPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

}
