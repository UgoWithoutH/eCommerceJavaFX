package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.articles.Article;
import model.articles.habits.Habit;
import model.articles.habits.MyColor;
import model.articles.habits.Taille;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class HabitVM extends ArticleVM {
    private ObservableList<String> observableTailles = FXCollections.observableArrayList();
    private ListProperty<String> tailles = new SimpleListProperty<>(observableTailles);

    public ObservableList<String> getTailles() {
        return FXCollections.unmodifiableObservableList(tailles.get());
    }

    public ReadOnlyListProperty<String> taillesProperty() {
        return tailles;
    }

    private ObservableList<Color> observableColors = FXCollections.observableArrayList();
    private ListProperty<Color> colors = new SimpleListProperty(observableColors);

    public ObservableList<Color> getColors() {
        return FXCollections.unmodifiableObservableList(colors.get());
    }

    public ReadOnlyListProperty<Color> colorsProperty() {
        return colors;
    }

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Habit.PROP_NOM)) {
            setNom((String) evt.getNewValue());
        }

        if (evt.getPropertyName().equals(Habit.PROP_PRIX)) {
            setPrix((double) evt.getNewValue());
        }

        if (evt.getPropertyName().equals(Habit.PROP_TAILLE_AJOUT)) {
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            String newValue = ((Taille) ievt.getNewValue()).name();
            if (newValue != null) {
                observableTailles.add(ievt.getIndex(), newValue);
            }
        }

        if (evt.getPropertyName().equals(Habit.PROP_TAILLE_SUPPR)) {
            String taille = ((Taille) evt.getOldValue()).name();
            if (taille != null) {
                observableTailles.remove(taille);
            }
        }

        if (evt.getPropertyName().equals(Habit.PROP_COULEUR_AJOUT)) {
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            MyColor newValue = (MyColor) ievt.getNewValue();
            if (newValue != null) {
                observableColors.add(ievt.getIndex(), new Color(
                        newValue.getRed(),
                        newValue.getGreen(),
                        newValue.getBlue(),
                        newValue.getOpacity()
                ));
            }
        }

        if (evt.getPropertyName().equals(Habit.PROP_COULEUR_SUPPR)) {
            MyColor color = (MyColor) evt.getOldValue();
            if (color != null) {
                observableColors.remove(new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        color.getOpacity()
                ));
            }
        }
    }
}
