package model.articles.parfums;

import model.articles.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parfum extends Article {
    public static final String PROP_FRAGRANCES_AJOUT = "PROP_FRAGRANCES_AJOUT";
    public static final String PROP_FRAGRANCES_SUPPR = "PROP_FRAGRANCES_SUPPR";
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
            support.fireIndexedPropertyChange(PROP_FRAGRANCES_AJOUT, index, fragrances.size() > 1 ? fragrances.get(index - 1) : null, fragrance);
        }
    }

    public void supprimerFragrance(Fragrance fragrance){
        fragrances.remove(fragrance);
        support.firePropertyChange(PROP_FRAGRANCES_SUPPR, fragrance, null);
    }
}
