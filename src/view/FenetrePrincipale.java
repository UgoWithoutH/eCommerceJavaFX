package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import viewmodel.ArticleVM;
import viewmodel.HabitVM;
import viewmodel.ManagerVM;
import viewmodel.ParfumVM;

import java.io.IOException;

public class FenetrePrincipale {
    @FXML
    private ListView<ArticleVM> listViewArticles;
    @FXML
    private ChoiceBox<String> choixFiltre;
    @FXML
    private TextField nomDetail;
    @FXML
    private TextField prixDetail;
    @FXML
    private Pane detail;
    private ManagerVM managerVM;

    public void initialize(){
        managerVM = new ManagerVM();
        initializeListViewArticles();
        initializeChoiceBoxs();
    }

    private void initializeListViewArticles(){
        listViewArticles.itemsProperty().bind(managerVM.articlesProperty());
        listViewArticles.setCellFactory(__ -> new ArticleCell());
        listViewArticles.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> initializeDetail(oldV, newV)
        );
    }

    private void initializeDetail(ArticleVM oldArticleVM, ArticleVM newArticleVM) {
        if(oldArticleVM != null){
            nomDetail.textProperty().unbindBidirectional(oldArticleVM);
            prixDetail.textProperty().unbindBidirectional(oldArticleVM);
        }
        nomDetail.textProperty().bindBidirectional(newArticleVM.nomProperty());
        prixDetail.textProperty().bindBidirectional(newArticleVM.prixProperty(), new NumberStringConverter());
        detail.getChildren().clear();
        try {
            if (newArticleVM instanceof ParfumVM parfumVM){
                detail.getChildren().add(new DetailParfum(parfumVM));
            }
            else if(newArticleVM instanceof HabitVM habitVM){
                detail.getChildren().add(new DetailHabit(habitVM));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeChoiceBoxs(){
        choixFiltre.itemsProperty().bind(managerVM.filtresProperty());
        choixFiltre.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {

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
