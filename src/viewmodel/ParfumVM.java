package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.articles.habits.Habit;
import model.articles.habits.Taille;
import model.articles.parfums.Fragrance;
import model.articles.parfums.Parfum;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class ParfumVM extends ArticleVM{
    private ObservableList<String> observableFragrances = FXCollections.observableArrayList();
    private ListProperty<String> fragrances = new SimpleListProperty(observableFragrances);
        public ObservableList<String> getFragrances() {return FXCollections.unmodifiableObservableList(fragrances.get());}
        public ReadOnlyListProperty<String> fragrancesProperty() {return fragrances;}

    public ParfumVM(Parfum model) {
        super(model);
        model.getFragrances().forEach(
                it -> observableFragrances.add(it.name())
        );
    }

    public void ajouterFragrance(String fragrance){
        ((Parfum) model).ajouterFragrance(Fragrance.valueOf(fragrance));
    }

    public void supprimerFragrance(String fragrance){
        ((Parfum) model).supprimerFragrance(Fragrance.valueOf(fragrance));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(Parfum.PROP_NOM)){
            setNom((String) evt.getNewValue());
        }

        if(evt.getPropertyName().equals(Parfum.PROP_PRIX)){
            setPrix((double) evt.getNewValue());
        }

        if(evt.getPropertyName().equals(Parfum.PROP_FRAGRANCES_AJOUT)){
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            Object newValue = ievt.getNewValue();
            if(newValue != null) {
                observableFragrances.add(ievt.getIndex(),((Fragrance) newValue).name());
            }else{
                observableFragrances.remove(ievt.getIndex());
            }
        }

        if(evt.getPropertyName().equals(Parfum.PROP_FRAGRANCES_SUPPR)){
            String fragrance = ((Fragrance) evt.getOldValue()).name();
            if(fragrance != null){
                observableFragrances.remove(fragrance);
            }
        }
    }
}
