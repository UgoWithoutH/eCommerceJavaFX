package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewmodel.ManagerVM;
import viewmodel.ParfumVM;

import java.io.IOException;
import java.io.PipedReader;

public class DetailParfum extends VBox {

    @FXML
    private ListView<String> fragrances;
    @FXML
    private ChoiceBox<String> choiceFragrance;
    private ParfumVM parfumVM;
    private ManagerVM managerVM;

    public DetailParfum(ParfumVM parfumVM, ManagerVM managerVM) throws IOException {
        this.parfumVM = parfumVM;
        this.managerVM = managerVM;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DetailParfum.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize(){
        fragrances.itemsProperty().bind(parfumVM.fragrancesProperty());
        choiceFragrance.itemsProperty().bind(managerVM.choicesFragrancesProperty());
    }

    @FXML
    private void ajouterFragrance(ActionEvent actionEvent){
        String choix  = choiceFragrance.getSelectionModel().getSelectedItem();
        if(choix != null){
            parfumVM.ajouterFragrance(choix);
        }
    }

    @FXML
    private void supprimerFragrance(ActionEvent actionEvent){
        String fragrance = fragrances.getSelectionModel().getSelectedItem();
        if(fragrance != null){
            parfumVM.supprimerFragrance(fragrance);
        }
    }
}
