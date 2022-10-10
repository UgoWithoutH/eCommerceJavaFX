package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import viewmodel.ArticleVM;
import viewmodel.ManagerVM;

import java.io.IOException;

public class CreationArticle {

    @FXML
    private Button btnPreviousScene;
    @FXML
    private ChoiceBox<String> choixCreationArticle;
    @FXML
    private Pane creation;
    private ManagerVM managerVM;
    private Scene previousScene;
    private Stage stage;

    public CreationArticle(ManagerVM managerVM) {
        this.managerVM = managerVM;
    }

    public void initialize() {
        choixCreationArticle.itemsProperty().bind(managerVM.choicesProperty());
        choixCreationArticle.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, newValue) -> {
                    if (newValue.equals(managerVM.CHOIX_HABIT)) {
                        initializeCreationHabit();
                    } else if (newValue.equals(managerVM.CHOIX_PARFUM)) {
                        initializeCreationParfum();
                    }
                });
        choixCreationArticle.getSelectionModel().select(0);
        VBox.setVgrow(creation, Priority.ALWAYS);
    }

    private void initializeCreationParfum() {
        creation.getChildren().clear();
        try {
            CreationParfum creationParfum = new CreationParfum(managerVM);
            creationParfum.setPreviousScene(previousScene);
            creationParfum.setStage(stage);
            creation.getChildren().add(creationParfum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeCreationHabit() {
        creation.getChildren().clear();
        try {
            CreationHabit creationHabit = new CreationHabit(managerVM);
            creationHabit.setPreviousScene(previousScene);
            creationHabit.setStage(stage);
            creation.getChildren().add(creationHabit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        stage = (Stage) scene.getWindow();
    }
}
