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
    private DetailHabit detailHabit;
    private DetailParfum detailParfum;
    private ManagerVM managerVM;

    public FenetrePrincipale(ManagerVM managerVM) {
        this.managerVM = managerVM;
    }

    public void initialize(){
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
            nomDetail.textProperty().unbindBidirectional(oldArticleVM.nomProperty());
            prixDetail.textProperty().unbindBidirectional(oldArticleVM.prixProperty());
            nomDetail.setText("");
            prixDetail.setText("");
        }
        if(newArticleVM != null) {
            nomDetail.textProperty().bindBidirectional(newArticleVM.nomProperty());
            prixDetail.textProperty().bindBidirectional(newArticleVM.prixProperty(), new NumberStringConverter());
        }

        detail.getChildren().clear();
        try {
            if (newArticleVM instanceof ParfumVM parfumVM){
                if(detailParfum == null){
                    detailParfum = new DetailParfum(managerVM);
                }
                detailParfum.setParfumVM(parfumVM);
                detail.getChildren().add(detailParfum);
            }
            else if(newArticleVM instanceof HabitVM habitVM){
                if(detailHabit == null){
                    detailHabit = new DetailHabit(managerVM);
                }
                detailHabit.setHabitVM(habitVM);
                detail.getChildren().add(detailHabit);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeChoiceBoxs(){
        choixFiltre.itemsProperty().bind(managerVM.filtresProperty());
        choixFiltre.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldV, newV) -> {
                    managerVM.filtrer(newV);
                    managerVM.setFilterSelected(newV);
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

    @FXML
    private void supprimer(ActionEvent actionEvent){
        ArticleVM articleVM = listViewArticles.getSelectionModel().getSelectedItem();
        if(articleVM != null){
            managerVM.supprimerArticle(articleVM);
        }
    }
}
