package data;

import model.articles.Article;
import model.articles.habits.Habit;
import model.articles.habits.MyColor;
import model.articles.habits.Taille;
import model.articles.parfums.Fragrance;
import model.articles.parfums.Parfum;

import java.util.ArrayList;
import java.util.List;

public class Stub {


    public List<Article> creer() {
        return new ArrayList<>() {{
            add(new Habit(
                    "tee shirt",
                    12,
                    new ArrayList<>() {{
                        add(new MyColor(0.144, 0.214, 0.455, 1));
                        add(new MyColor(0.144, 0.244, 0.455, 1));
                    }},
                    new ArrayList<>() {{
                        add(Taille.S);
                    }}
            ));
            add(new Parfum(
                    "dior",
                    100,
                    new ArrayList<>(){{
                        add(Fragrance.PRALINE);
                    }}
            ));
        }};
    }
}
