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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(Habit.PROP_NOM)){
            setNom((String) evt.getNewValue());
        }

        if(evt.getPropertyName().equals(Habit.PROP_PRIX)){
            setPrix((double) evt.getNewValue());
        }

        if(evt.getPropertyName().equals(Habit.PROP_TAILLE)){
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            Object newValue = ievt.getNewValue();
            if(newValue != null) {
                observableTailles.add(ievt.getIndex(),((Taille) newValue).name());
            }else{
                observableTailles.remove(ievt.getIndex());
            }
        }

        if(evt.getPropertyName().equals(Habit.PROP_PRIX)){
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            Object newValue = ievt.getNewValue();
            if(newValue != null) {
                MyColor color = (MyColor) ievt.getNewValue();
                observableColors.add(ievt.getIndex(), new Color(
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue(),
                        color.getOpacity()
                ));
            }else{
                observableColors.remove(ievt.getIndex());
            }
        }
    }
}
