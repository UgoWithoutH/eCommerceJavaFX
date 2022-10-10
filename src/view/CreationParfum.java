package view;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viewmodel.FragranceVM;
import viewmodel.ManagerVM;

import java.io.IOException;

public class CreationParfum extends VBox {

    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private ListView<FragranceVM> listViewFragrancesVM;
    @FXML
    private ChoiceBox<FragranceVM> choiceBoxFragrancesVM;
    private ObservableList<FragranceVM> observableFragancesVM = FXCollections.observableArrayList();
    private ListProperty<FragranceVM> fragrancesVM = new SimpleListProperty<>(observableFragancesVM);
        public ObservableList<FragranceVM> getFragrancesVM() {return FXCollections.unmodifiableObservableList(fragrancesVM.get());}
        public ReadOnlyListProperty<FragranceVM> fragrancesVMProperty() {return fragrancesVM;}
    private Scene previousScene;
    private Stage stage;
    private ManagerVM managerVM;

    public CreationParfum(ManagerVM managerVM) throws IOException {
        this.managerVM = managerVM;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreationParfum.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize(){
        listViewFragrancesVM.itemsProperty().bind(fragrancesVM);
        choiceBoxFragrancesVM.itemsProperty().bind(managerVM.choicesFragrancesProperty());
    }

    @FXML
    private void ajouterFragrance(ActionEvent actionEvent){
        FragranceVM fragranceSelected = choiceBoxFragrancesVM.getSelectionModel().getSelectedItem();

        if(!observableFragancesVM.contains(fragranceSelected)){
            observableFragancesVM.add(fragranceSelected);
        }
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private void annuler(ActionEvent actionEvent) {
        stage.setScene(previousScene);
    }

    @FXML
    private void valider(ActionEvent actionEvent) {
        managerVM.ajouterParfum(
                nom.getText(),
                Double.parseDouble(prix.getText()),
                observableFragancesVM
        );
        stage.setScene(previousScene);
    }
}
