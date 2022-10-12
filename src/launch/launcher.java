package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.FenetrePrincipale;
import viewmodel.ManagerVM;

public class launcher extends Application {

    private ManagerVM managerVM;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FenetrePrincipale.fxml"));
        managerVM = new ManagerVM();
        loader.setController(new FenetrePrincipale(managerVM));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        managerVM.save();
    }
}
