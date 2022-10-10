package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.Manager;
import model.articles.Article;
import model.articles.habits.Habit;
import model.articles.habits.MyColor;
import model.articles.habits.Taille;
import model.articles.parfums.Fragrance;
import model.articles.parfums.Parfum;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerVM implements PropertyChangeListener {

    public final String CHOIX_HABIT = "Habit";
    public final String CHOIX_PARFUM = "Parfum";
    public final String FILTRE_HABIT = "Habit";
    public final String FILTRE_PARFUM = "Parfum";
    public final String FILTRE_TOUT = "TOUT";
    private ObservableList<ArticleVM> observableArticles = FXCollections.observableArrayList();
    private ListProperty<ArticleVM> articles = new SimpleListProperty<>(observableArticles);

    public ObservableList<ArticleVM> getArticles() {
        return FXCollections.unmodifiableObservableList(articles.get());
    }

    public ReadOnlyListProperty<ArticleVM> articlesProperty() {
        return articles;
    }

    private ObservableList<String> observableChoices = FXCollections.observableArrayList();
    private ListProperty<String> choices = new SimpleListProperty<>(observableChoices);

    public ObservableList<String> getChoices() {
        return FXCollections.unmodifiableObservableList(choices.get());
    }

    public ReadOnlyListProperty<String> choicesProperty() {
        return choices;
    }

    private ObservableList<FragranceVM> observableChoicesFragrances = FXCollections.observableArrayList();
    private ListProperty<FragranceVM> choicesFragrances = new SimpleListProperty<>(observableChoicesFragrances);

    public ObservableList<FragranceVM> getChoicesFragrances() {
        return FXCollections.unmodifiableObservableList(choicesFragrances.get());
    }

    public ReadOnlyListProperty<FragranceVM> choicesFragrancesProperty() {
        return choicesFragrances;
    }

    private ObservableList<TailleVM> observableChoicesTailles = FXCollections.observableArrayList();
    private ListProperty<TailleVM> choicesTailles = new SimpleListProperty<>(observableChoicesTailles);

    public ObservableList<TailleVM> getchoicesTailles() {
        return FXCollections.unmodifiableObservableList(choicesTailles.get());
    }

    public ReadOnlyListProperty<TailleVM> choicesTaillesProperty() {
        return choicesTailles;
    }

    private ObservableList<String> observableFiltres = FXCollections.observableArrayList();
    private ListProperty<String> filtres = new SimpleListProperty<>(observableFiltres);

    public ObservableList<String> getFiltres() {
        return FXCollections.unmodifiableObservableList(filtres.get());
    }

    public ReadOnlyListProperty<String> filtresProperty() {
        return filtres;
    }

    private Manager model;

    public ManagerVM() {
        this.model = new Manager();
        model.ajouterPropertyChangeListener(this);
        observableFiltres.addAll(
                FILTRE_TOUT,
                FILTRE_HABIT,
                FILTRE_PARFUM
        );
        observableChoices.addAll(
                CHOIX_HABIT,
                CHOIX_PARFUM
        );
        observableChoicesFragrances.addAll(Arrays.asList(FragranceVM.values()));
        observableChoicesTailles.addAll(Arrays.asList(TailleVM.values()));
    }

    public void ajouterParfum(String nom, double prix, List<FragranceVM> fragrancesChoix) {

        List<Fragrance> fragrances = new ArrayList<>();

        for (FragranceVM choix : fragrancesChoix) {
            fragrances.add(Fragrance.valueOf(choix.name()));
        }

        model.ajouterArticle(new Parfum(
                nom,
                prix,
                fragrances
        ));
    }

    public void ajouterHabit(String nom, double prix, List<Color> couleurs, List<TailleVM> taillesVM) {
        List<MyColor> myColors = new ArrayList<>();
        List<Taille> tailles = new ArrayList<>();

        couleurs.forEach(
                color -> myColors.add(
                        new MyColor(
                                color.getRed(),
                                color.getGreen(),
                                color.getBlue(),
                                color.getOpacity()
                        )
                )
        );

        taillesVM.forEach(
                taille -> tailles.add(Taille.valueOf(taille.name()))
        );

        model.ajouterArticle(new Habit(
                nom,
                prix,
                myColors,
                tailles
        ));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Manager.PROP_ARTICLES)) {
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            Article article = (Article) ievt.getNewValue();
            if (article instanceof Habit habit) {

                List<Color> colors = new ArrayList<>();
                List<TailleVM> tailleVM = new ArrayList<>();

                habit.getCouleurs().forEach(
                        myColor -> new Color(
                                myColor.getRed(),
                                myColor.getGreen(),
                                myColor.getBlue(),
                                myColor.getOpacity()
                        )
                );

                habit.getTailles().forEach(
                        taille -> tailleVM.add(TailleVM.valueOf(taille.name()))
                );

                observableArticles.add(ievt.getIndex(), new HabitVM(
                        habit,
                        habit.getNom(),
                        habit.getPrix(),
                        colors,
                        tailleVM
                ));
            } else if (article instanceof Parfum parfum) {

                List<FragranceVM> fragranceVM = new ArrayList<>();
                parfum.getFragrances().forEach(
                        it -> fragranceVM.add(FragranceVM.valueOf(it.name()))
                );

                observableArticles.add(ievt.getIndex(), new ParfumVM(
                        parfum,
                        parfum.getNom(),
                        parfum.getPrix(),
                        fragranceVM
                ));
            }
        }
    }
}
