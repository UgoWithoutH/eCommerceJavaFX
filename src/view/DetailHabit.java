package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import viewmodel.HabitVM;
import viewmodel.ManagerVM;

import java.io.IOException;

public class DetailHabit extends VBox {

    @FXML
    private ListView<String> tailles;
    @FXML
    private ListView<Color> couleurs;
    @FXML
    private ChoiceBox<String> choiceTaille;
    @FXML
    private ColorPicker colorPicker;
    private HabitVM habitVM;
    private ManagerVM managerVM;

    public DetailHabit(ManagerVM managerVM) throws IOException {
        this.managerVM = managerVM;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DetailHabit.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize(){

    }

    @FXML
    private void ajouterTaille(ActionEvent actionEvent){
        String taille = choiceTaille.getSelectionModel().getSelectedItem();
        if(taille != null){
            habitVM.ajouterTaille(taille);
        }
    }
    @FXML
    private void supprimerTaille(ActionEvent actionEvent){
        String choix = tailles.getSelectionModel().getSelectedItem();
        if(choix != null){
            habitVM.supprimerTaille(choix);
        }
    }
    @FXML
    private void ajouterCouleur(ActionEvent actionEvent){
        Color color = colorPicker.getValue();
        if(color != null){
            habitVM.ajouterCouleur(color);
        }
    }
    @FXML
    private void supprimerCouleur(ActionEvent actionEvent){
        Color color = couleurs.getSelectionModel().getSelectedItem();
        if(color != null){
            habitVM.supprimerCouleur(color);
        }
    }

    public void setHabitVM(HabitVM habitVM) {
        this.habitVM = habitVM;
        tailles.itemsProperty().bind(habitVM.taillesProperty());
        couleurs.itemsProperty().bind(habitVM.colorsProperty());
        choiceTaille.itemsProperty().bind(managerVM.choicesTaillesProperty());
    }
}
