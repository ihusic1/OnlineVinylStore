package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {
    public Button Undo;
    public Button OK;
    @FXML
    public Label badEmail;
    @FXML
    public Label badPassword;
    @FXML
    public Label errorLabel;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    UserManager userManager = new UserManager();
    private int id;

    public void initialize() {
        emailField.getStyleClass().removeAll("fieldNotCorrect");
        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(emailField.getText().trim().isEmpty()) {
                    emailField.getStyleClass().removeAll("fieldCorrect");
                    emailField.getStyleClass().add("fieldNotCorrect");
                }
                else {
                    emailField.getStyleClass().removeAll("fieldNotCorrect");
                    emailField.getStyleClass().add("fieldCorrect");
                }
            }
        });
    }

    @FXML
    private void actionSubmit() throws RecordException{

        List<User> listaUsera = userManager.getAll();

        //Establish a connection to the database
        boolean loginSuccessful = false;
        User user = new User();
        //System.out.println(" " + loginSuccessful + " ");

        //Validate the input
        if (emailField.getText().isEmpty()) {
            //display an error message
            badEmail.setText("Email cannot be empty!");

        } else badEmail.setText("");

        if (passwordField.getText().isEmpty()) {
            //display an error message
            badPassword.setText("Password cannot be empty!");

        } else {
            badPassword.setText("");
        }

        Model modelUser =  Model.getInstance();
        if (!emailField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            for (User u : listaUsera) {
                if (u.getEmail().equals(emailField.getText()) && u.getPassword().equals(passwordField.getText())) {
                    loginSuccessful = true;
                    id = u.getId();
                    modelUser.setUser(u);
                }
            }
        }

        if (loginSuccessful) {

            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/records.fxml"));
                RecordsController recordsController = new RecordsController(id);
                loader.setController(recordsController);
                Parent root = loader.load();
                stage.setTitle("Records");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            errorLabel.setText("Invalid login.");

        }
    }


    public void closeWindow(ActionEvent actionEvent) {
        Stage stage =(Stage)Undo.getScene().getWindow();
        stage.close();
    }
}
