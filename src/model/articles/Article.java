package model.articles;

public abstract class Article {

    public Article(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    private String nom;
    private double prix;

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }
}
