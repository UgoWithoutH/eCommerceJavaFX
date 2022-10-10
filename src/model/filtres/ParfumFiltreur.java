package model.filtres;

import model.articles.Article;
import model.articles.parfums.Parfum;

import java.util.List;
import java.util.stream.Collectors;

public class ParfumFiltreur implements Filtreur{
    @Override
    public List<Article> filtrer(List<Article> articles) {
        return articles.stream().filter(item -> item.getClass().equals(Parfum.class)).collect(Collectors.toList());
    }
}
