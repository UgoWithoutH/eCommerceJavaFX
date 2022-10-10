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
    private ObservableList<String> observableTailles = FXCollections.observableArrayList();
    private ListProperty<String> tailles = new SimpleListProperty<>(observableTailles);
        public ObservableList<String> getTailles() {return FXCollections.unmodifiableObservableList(tailles.get());}
        public ReadOnlyListProperty<String> taillesProperty() {return tailles;}
    private ObservableList<Color> observableColors = FXCollections.observableArrayList();
    private ListProperty<Color> colors = new SimpleListProperty(observableColors);
        public ObservableList<Color> getColors() {return FXCollections.unmodifiableObservableList(colors.get());}
        public ReadOnlyListProperty<Color> colorsProperty() {return colors;}

    public HabitVM(Habit model) {
        super(model);
        initializeTailles(model);
        initializeColors(model);
    }

    private void initializeColors(Habit model) {
        model.getCouleurs().forEach(
                it -> observableColors.add(new Color(
                        it.getRed(),
                        it.getGreen(),
                        it.getBlue(),
                        it.getOpacity()
                ))
        );
    }

    private void initializeTailles(Habit model) {
        model.getTailles().forEach(
                it -> observableTailles.add(it.name())
        );
    }
}
