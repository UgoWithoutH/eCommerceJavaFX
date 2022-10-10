package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.ArticleVM;
import viewmodel.ManagerVM;

import java.io.IOException;

public class FenetrePrincipale {
    @FXML
    private ListView<ArticleVM> listViewArticles;
    @FXML
    private ChoiceBox<String> choixFiltre;

    private ManagerVM managerVM;

    public void initialize(){
        managerVM = new ManagerVM();
        initializeListViewArticles();
        initializeChoiceBoxs();
    }

    private void initializeListViewArticles(){
        listViewArticles.itemsProperty().bind(managerVM.articlesProperty());
        listViewArticles.setCellFactory(__ -> new ArticleCell());
    }

    private void initializeChoiceBoxs(){
        choixFiltre.itemsProperty().bind(managerVM.filtresProperty());
        choixFiltre.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {
                    String choix = choixFiltre.getSelectionModel().getSelectedItem();
                    if(choix.equals(managerVM.FILTRE_TOUT)){

                    }
                }
        );
        choixFiltre.getSelectionModel().select(0);
    }

    @FXML
    private void creer(ActionEvent actionEvent) throws IOException {
        CreationArticle creationArticle = new CreationArticle(managerVM);
        Stage stage = (Stage) ((Button)(actionEvent.getSource())).getScene().getWindow();
        creationArticle.setPreviousScene(stage.getScene());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CreationArticle.fxml"));
        loader.setController(creationArticle);
        stage.setScene(new Scene(loader.load()));
    }
}
