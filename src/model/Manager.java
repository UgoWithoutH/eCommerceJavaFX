package model;

import data.FileSaver;
import data.Saver;
import model.articles.Article;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager implements Serializable {

    public static final String PROP_ARTICLES_AJOUT = "PROP_ARTICLES_AJOUT";
    public static final String PROP_ARTICLES_SUPPR = "PROP_ARTICLES_SUPPR";
    private List<Article> articles = new ArrayList<>();
    private transient PropertyChangeSupport support;

    public void ajouterArticle(Article article){
        articles.add(article);
        int index = articles.indexOf(article);
        getSupport().fireIndexedPropertyChange(PROP_ARTICLES_AJOUT, index, articles.size() > 1 ? articles.get(index-1) : null, article);
    }

    public void supprimerArticle(Article article){
        articles.remove(article);
        getSupport().firePropertyChange(PROP_ARTICLES_SUPPR, article, null);
    }

    public List<Article> getArticles() {
        return Collections.unmodifiableList(articles);
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
