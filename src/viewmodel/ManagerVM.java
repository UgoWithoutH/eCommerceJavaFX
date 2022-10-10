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

    private ObservableList<String> observableChoicesFragrances = FXCollections.observableArrayList();
    private ListProperty<String> choicesFragrances = new SimpleListProperty<>(observableChoicesFragrances);
        public ObservableList<String> getChoicesFragrances() {
            return FXCollections.unmodifiableObservableList(choicesFragrances.get());
        }
        public ReadOnlyListProperty<String> choicesFragrancesProperty() {
            return choicesFragrances;
        }

    private ObservableList<String> observableChoicesTailles = FXCollections.observableArrayList();
    private ListProperty<String> choicesTailles = new SimpleListProperty<>(observableChoicesTailles);
        public ObservableList<String> getchoicesTailles() {
            return FXCollections.unmodifiableObservableList(choicesTailles.get());
        }
        public ReadOnlyListProperty<String> choicesTaillesProperty() {
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

        List<String> fragrances = new ArrayList<>();
        for (Fragrance value : Fragrance.values()) {
            fragrances.add(value.name());
        }

        observableChoicesFragrances.addAll(fragrances);

        List<String> tailles = new ArrayList<>();
        for (Taille value : Taille.values()) {
            tailles.add(value.name());
        }
        observableChoicesTailles.addAll(tailles);
    }

    public void ajouterParfum(String nom, double prix, List<String> fragrancesChoix) {

        List<Fragrance> fragrances = new ArrayList<>();

        for (String choix : fragrancesChoix) {
            fragrances.add(Fragrance.valueOf(choix));
        }

        model.ajouterArticle(new Parfum(
                nom,
                prix,
                fragrances
        ));
    }

    public void ajouterHabit(String nom, double prix, List<Color> couleurs, List<String> taillesChoix) {
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

        taillesChoix.forEach(
                taille -> tailles.add(Taille.valueOf(taille))
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
                observableArticles.add(ievt.getIndex(), new HabitVM(habit));
            } else if (article instanceof Parfum parfum) {
                observableArticles.add(ievt.getIndex(), new ParfumVM(parfum));
            }
        }
    }
}
