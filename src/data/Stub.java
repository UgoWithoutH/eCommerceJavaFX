package data;

import model.Manager;
import model.articles.Article;
import model.articles.habits.Habit;
import model.articles.habits.MyColor;
import model.articles.habits.Taille;
import model.articles.parfums.Fragrance;
import model.articles.parfums.Parfum;

import java.util.ArrayList;
import java.util.List;

public class Stub implements Loader{


    public Manager load() {

        Manager manager = new Manager();
        manager.ajouterArticle(new Habit(
                "tee shirt",
                12,
                new ArrayList<>(),
                new ArrayList<>() {{
                    add(Taille.S);
                }}
        ));
        manager.ajouterArticle(new Parfum(
                "dior",
                100,
                new ArrayList<>(){{
                    add(Fragrance.PRALINE);
                }}
        ));
        return manager;
    }
}
