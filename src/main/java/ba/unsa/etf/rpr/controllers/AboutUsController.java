package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutUsController {

    public Button Undo;
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage =(Stage)Undo.getScene().getWindow();
        stage.close();
    }
}
