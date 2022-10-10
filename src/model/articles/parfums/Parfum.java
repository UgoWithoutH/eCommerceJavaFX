package model.articles.parfums;

import model.articles.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parfum extends Article {
    private static final String PROP_FRAGRANCES = "FRAGRANCES";
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

    public void ajouterFragrance(Fragrance fragrance){
        if(!fragrances.contains(fragrance)){
            fragrances.add(fragrance);
            int index = fragrances.indexOf(fragrance);
            support.fireIndexedPropertyChange(PROP_FRAGRANCES, index, fragrances.size() > 1 ? fragrances.get(index - 1) : null, fragrance);
        }
    }

    public void supprimerFragrance(Fragrance fragrance){
        fragrances.remove(fragrance);
        int index = fragrances.indexOf(fragrance);
        support.fireIndexedPropertyChange(PROP_FRAGRANCES, index, fragrance, null);
    }
}
