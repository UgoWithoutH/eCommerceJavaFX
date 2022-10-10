package model.filtres;

import model.articles.Article;

import java.util.List;

public interface Filtreur {

    List<Article> filtrer(List<Article> articles);

}
