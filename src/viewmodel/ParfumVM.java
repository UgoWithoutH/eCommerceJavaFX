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
}
