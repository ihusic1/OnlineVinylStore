package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.controllers.HomeController;
//import ba.unsa.etf.rpr.controllers.RecordController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX class
 * @author Ilma Husic
 */

public class AppFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            HomeController homeController = new HomeController();
            loader.setController(homeController);
            Parent root = loader.load();
            stage.setTitle("Online Record Store");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}