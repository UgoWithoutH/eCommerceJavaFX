package viewmodel;

import data.*;
import javafx.beans.property.*;
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
import java.io.IOException;
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
        public ObservableList<ArticleVM> getArticles() {return FXCollections.unmodifiableObservableList(articles.get());}
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
        public ObservableList<String> getChoicesFragrances() {return FXCollections.unmodifiableObservableList(choicesFragrances.get());}
        public ReadOnlyListProperty<String> choicesFragrancesProperty() {
            return choicesFragrances;
        }
    private ObservableList<String> observableChoicesTailles = FXCollections.observableArrayList();
    private ListProperty<String> choicesTailles = new SimpleListProperty<>(observableChoicesTailles);
        public ObservableList<String> getchoicesTailles() {return FXCollections.unmodifiableObservableList(choicesTailles.get());}
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
    private String filterSelected;

    private Saver saver = new FileSaver();
    private Loader loader = new FileLoader();
    private Manager model;

    public ManagerVM() {
        try {
            model = loader.load();
        } catch (IOException | ClassNotFoundException e) {
            model = new Stub().load();
        }
        model.ajouterPropertyChangeListener(this);
        model.getArticles().forEach(
                it ->
                {
                    if (it instanceof Habit habit) {
                        observableArticles.add(new HabitVM(habit));
                    } else if (it instanceof Parfum parfum) {
                        observableArticles.add(new ParfumVM(parfum));
                    }
                }
        );
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

    public void setFilterSelected(String filterSelected) {
        this.filterSelected = filterSelected;
    }

    public void filtrer(String choix) {
        List<ArticleVM> articleVMS = new ArrayList<>();
        switch (choix) {
            case FILTRE_TOUT -> filtreTout(articleVMS);
            case FILTRE_HABIT -> filtreHabits(articleVMS);
            case FILTRE_PARFUM -> filtreParfums(articleVMS);
        }

        observableArticles.clear();
        observableArticles.addAll(articleVMS);
    }

    private void filtreTout(List<ArticleVM> articleVMS) {
        model.getArticles().forEach(
                it -> {
                    if (it instanceof Habit habit) {
                        articleVMS.add(new HabitVM(habit));
                    } else if (it instanceof Parfum parfum) {
                        articleVMS.add(new ParfumVM(parfum));
                    }
                }
        );
    }

    private void filtreHabits(List<ArticleVM> articleVMS) {
        model.getArticles().forEach(
                it -> {
                    if (it instanceof Habit habit) {
                        articleVMS.add(new HabitVM(habit));
                    }
                }
        );
    }

    private void filtreParfums(List<ArticleVM> articleVMS) {
        model.getArticles().forEach(
                it -> {
                    if (it instanceof Parfum parfum) {
                        articleVMS.add(new ParfumVM(parfum));
                    }
                }
        );
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

    public void supprimerArticle(ArticleVM articleVM) {
        if (articleVM instanceof HabitVM habitVM) {
            List<MyColor> colors = new ArrayList<>();
            habitVM.getColors().forEach(
                    it -> colors.add(
                            new MyColor(
                                    it.getRed(),
                                    it.getGreen(),
                                    it.getBlue(),
                                    it.getOpacity()
                            )
                    )
            );
            List<Taille> tailles = new ArrayList<>();
            habitVM.getTailles().forEach(
                    it -> tailles.add(Taille.valueOf(it))
            );
            model.supprimerArticle(new Habit(
                    habitVM.getNom(),
                    habitVM.getPrix(),
                    colors,
                    tailles
            ));
        } else if (articleVM instanceof ParfumVM parfumVM) {
            List<Fragrance> fragrances = new ArrayList<>();
            parfumVM.getFragrances().forEach(
                    it -> fragrances.add(Fragrance.valueOf(it))
            );
            model.supprimerArticle(new Parfum(
                    parfumVM.getNom(),
                    parfumVM.getPrix(),
                    fragrances
            ));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Manager.PROP_ARTICLES_AJOUT)) {
            Article article = (Article) evt.getNewValue();
            if (article instanceof Habit habit && filterIsActivated(FILTRE_HABIT)) {
                observableArticles.add(new HabitVM(habit));
            } else if (article instanceof Parfum parfum && filterIsActivated(FILTRE_PARFUM)) {
                observableArticles.add(new ParfumVM(parfum));
            }
        } else if (evt.getPropertyName().equals(Manager.PROP_ARTICLES_SUPPR)) {
            Article article = (Article) evt.getOldValue();
            if (article instanceof Habit habit && filterIsActivated(FILTRE_HABIT)) {
                observableArticles.remove(new HabitVM(habit));
            } else if (article instanceof Parfum parfum && filterIsActivated(FILTRE_PARFUM)) {
                observableArticles.remove(new ParfumVM(parfum));
            }
        }
    }

    private boolean filterIsActivated(String filterDesired){
        return filterDesired.equals(filterSelected);
    }

    public void save() throws IOException {
        saver.save(model);
    }
}
