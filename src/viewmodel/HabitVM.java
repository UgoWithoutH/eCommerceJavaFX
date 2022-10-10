package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.articles.habits.Habit;
import model.articles.habits.MyColor;
import model.articles.habits.Taille;

import java.util.List;

public class HabitVM extends ArticleVM{
    private ObservableList<TailleVM> observableTailles = FXCollections.observableArrayList();
    private ListProperty<TailleVM> tailles = new SimpleListProperty<>(observableTailles);
        public ObservableList<TailleVM> getTailles() {return FXCollections.unmodifiableObservableList(tailles.get());}
        public ReadOnlyListProperty<TailleVM> taillesProperty() {return tailles;}
    private ObservableList<Color> observableColors = FXCollections.observableArrayList();
    private ListProperty<Color> colors = new SimpleListProperty(observableColors);
        public ObservableList<Color> getColors() {return FXCollections.unmodifiableObservableList(colors.get());}
        public ReadOnlyListProperty<Color> colorsProperty() {return colors;}
    private Habit model;

    public HabitVM(Habit model, String nom, double prix, List<Color> couleurs, List<TailleVM> tailles) {
        super(nom, prix);
        this.model = model;
        observableColors.addAll(couleurs);
        observableTailles.addAll(tailles);
    }
}
