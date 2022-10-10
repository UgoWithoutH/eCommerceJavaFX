package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewmodel.ParfumVM;

import java.io.IOException;
import java.io.PipedReader;

public class DetailParfum extends VBox {

    @FXML
    private ListView<String> fragrances;
    private ParfumVM parfumVM;

    public DetailParfum(ParfumVM parfumVM) throws IOException {
        this.parfumVM = parfumVM;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DetailParfum.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize(){
        fragrances.itemsProperty().bind(parfumVM.fragrancesProperty());
    }
}
