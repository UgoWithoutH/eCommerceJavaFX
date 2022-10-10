package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private HabitVM habitVM;
    private ManagerVM managerVM;

    public DetailHabit(HabitVM habitVM, ManagerVM managerVM) throws IOException {
        this.habitVM = habitVM;
        this.managerVM = managerVM;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DetailHabit.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize(){
        tailles.itemsProperty().bind(habitVM.taillesProperty());
        couleurs.itemsProperty().bind(habitVM.colorsProperty());
    }
}
