package model.filtres;

import model.articles.Article;
import model.articles.habits.Habit;

import java.util.List;
import java.util.stream.Collectors;

public class HabitsFiltreur implements Filtreur{
    @Override
    public List<Article> filtrer(List<Article> articles) {
        return articles.stream().filter(item -> item.getClass().equals(Habit.class)).collect(Collectors.toList());
    }
}
