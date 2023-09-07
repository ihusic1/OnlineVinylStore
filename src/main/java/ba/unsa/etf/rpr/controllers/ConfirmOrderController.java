package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmOrderController {
    public Button close;

    public void closeAction(ActionEvent actionEvent) {
        Stage stage =(Stage)close.getScene().getWindow();
        stage.close();
    }
}