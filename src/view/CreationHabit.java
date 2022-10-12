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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import viewmodel.ManagerVM;

import java.io.IOException;

public class CreationHabit extends VBox {
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private ListView<Color> listViewCouleurs;
    @FXML
    private ListView<String> listViewTailles;
    @FXML
    private ChoiceBox<String> choiceBoxTailles;
    @FXML
    private ColorPicker colorPicker;
    private ObservableList<Color> observableColors = FXCollections.observableArrayList();
    private ListProperty<Color> colors = new SimpleListProperty<>(observableColors);
        public ObservableList<Color> getColors() {
            return FXCollections.unmodifiableObservableList(colors.get());
        }
        public ReadOnlyListProperty<Color> colorsProperty() {
            return colors;
        }

    private ObservableList<String> observableTailles = FXCollections.observableArrayList();
    private ListProperty<String> tailles = new SimpleListProperty<>(observableTailles);
        public ObservableList<String> getTailles() {
            return FXCollections.unmodifiableObservableList(tailles.get());
        }
        public ReadOnlyListProperty<String> taillesProperty() {
            return tailles;
        }

    private Scene previousScene;
    private Stage stage;
    private ManagerVM managerVM;

    public CreationHabit(ManagerVM managerVM) throws IOException {
        this.managerVM = managerVM;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreationHabit.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize() {
        listViewCouleurs.itemsProperty().bind(colorsProperty());
        listViewTailles.itemsProperty().bind(taillesProperty());
        choiceBoxTailles.itemsProperty().bind(managerVM.choicesTaillesProperty());
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void addColor(ActionEvent actionEvent) {
        Color color = colorPicker.getValue();
        if (color != null && !observableColors.contains(color)) {
            observableColors.add(color);
        }
    }

    @FXML
    private void addTaille(ActionEvent actionEvent) {
        String taille = choiceBoxTailles.getSelectionModel().getSelectedItem();
        if (taille != null && !observableTailles.contains(taille)) {
            observableTailles.add(taille);
        }

    }

    @FXML
    private void annuler(ActionEvent actionEvent) {
        stage.setScene(previousScene);
    }

    @FXML
    private void valider(ActionEvent actionEvent) {
        stage.setScene(previousScene);
        managerVM.ajouterHabit(
                nom.getText(),
                Double.parseDouble(prix.getText()),
                observableColors,
                observableTailles
        );
    }
}
