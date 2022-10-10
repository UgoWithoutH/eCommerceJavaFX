package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.articles.parfums.Fragrance;
import model.articles.parfums.Parfum;

import java.util.List;

public class ParfumVM extends ArticleVM{
    private ObservableList<FragranceVM> observableFragrances = FXCollections.observableArrayList();
    private ListProperty<FragranceVM> fragrances = new SimpleListProperty(observableFragrances);
        public ObservableList<FragranceVM> getFragrances() {return FXCollections.unmodifiableObservableList(fragrances.get());}
        public ReadOnlyListProperty<FragranceVM> fragrancesProperty() {return fragrances;}
    private Parfum model;

    public ParfumVM(Parfum model, String nom, double prix, List<FragranceVM> fragrances) {
        super(nom, prix);
        this.model = model;
        observableFragrances.addAll(fragrances);
    }
}
