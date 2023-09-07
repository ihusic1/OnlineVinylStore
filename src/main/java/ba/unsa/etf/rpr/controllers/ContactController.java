package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class ContactController {

    public Button Undo;
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage =(Stage)Undo.getScene().getWindow();
        stage.close();
    }
}
