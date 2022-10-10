package model.articles.parfums;

import model.articles.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parfum extends Article {
    private List<Fragrance> fragrances = new ArrayList<>();

    public Parfum(String nom, double prix, List<Fragrance> fragrances) {
        super(nom, prix);
        if(fragrances != null){
            this.fragrances.addAll(fragrances);
        }
    }

    public List<Fragrance> getFragrances() {
        return Collections.unmodifiableList(fragrances);
    }
}
