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
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class SignupController {
    public Button Undo;
    public Button OK;

    public  TextField firstNameField;
    public Label firstNameError;
    public  TextField lastNameField;
    public Label lastNameError;
    public PasswordField passwordField;
    public Label passwordError;
    public  TextField emailField;
    public Label emailError;
    public  TextField phoneField;
    public Label phoneError;
    public  TextField addressField;
    public Label addressError;

    UserManager userManager = new UserManager();

    public void actionSubmit(ActionEvent actionEvent) throws IOException, RecordException {
        List<User> listaRegistrovanihUsera = userManager.getAll();
        //Retrieve user input form form field
        String firstNameInput = firstNameField.getText();
        String lastNameInput = lastNameField.getText();
        String emailInput = emailField.getText();
        String passwordInput = passwordField.getText();
        String addressInput = addressField.getText();
        String phoneInput = phoneField.getText();

        boolean check = false;

        // Validate the input
        if (Objects.equals(firstNameField.getText(), "")) {
            // Display an error message
            firstNameError.setText("This field is required.");
            check = true;
        }
        if (Objects.equals(lastNameField.getText(), "")) {
            // Display an error message
            lastNameError.setText("This field is required.");
            check = true;
        }
        if (Objects.equals(addressField.getText(), "")) {
            // Display an error message
            passwordError.setText("This field is required.");
            check = true;
        }
        if (Objects.equals(phoneField.getText(), "")) {
            // Display an error message
            phoneError.setText("This field is required.");
            check = true;
        }

        //validate the input
        if (Objects.equals(emailField.getText(), "")) {
            // Display an error message
            emailError.setText("This field is required");
            check = true;
        }
        if (Objects.equals(passwordField.getText(), "")) {
            // Display an error message
            passwordError.setText("This field is required");
            check = true;
        }else {
            // Use a regular expression to check the email format
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(emailInput).matches()) {
                // Display an error message
                emailError.setText("Please enter a valid e-mail address (i.e. name@company.com)");
                check = true;
            }
        }

        if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !addressField.getText().isEmpty() && !phoneField.getText().isEmpty()&& !emailField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            for (User u : listaRegistrovanihUsera) {
                if (u.getEmail().equals(emailField.getText()) && u.getPassword().equals(passwordField.getText())) {
                    check = true;
                }
            }
        }
        if (!check) {
            // Create a new user data object and set the instance variables
            User user = new User();
            user.setFirstName(firstNameInput);
            user.setLastName(lastNameInput);
            user.setAddress(addressInput);
            user.setPhone_number(Integer.parseInt(phoneInput));
            user.setEmail(emailInput);
            user.setPassword(passwordInput);


            try {
                FileReader reader = new FileReader("src/main/resources/application.properties");
                Properties p = new Properties();
                p.load(reader);
                String s1 = p.getProperty("db.username"), s2 = p.getProperty("db.password"), s3 = p.getProperty("db.connection_string");
                Connection connection = DriverManager.getConnection(s3, s1, s2);
                System.out.println(s1 + " " + s2 + " " + s3);

                // Add the new user to the database
                User insertedUser = userManager.add(user);

                // Check if the user object was returned by the add User method from UserDaoSQLImpl.java
                try {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registration");
                    alert.setHeaderText("Results:");
                    alert.setContentText("Thank you for registration! Please log in.");
                    alert.showAndWait();

                    Stage stage = (Stage) OK.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
                    fxmlLoader.setController(new LoginController());
                    Parent root = fxmlLoader.load();
                    stage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

                // Close the connection to the database
                connection.close();

            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    public void closeWindow(ActionEvent actionEvent)
    {
        Stage stage =(Stage)Undo.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void initialize()
    {
        // Add an event listener to the name field
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the name field is changed
            firstNameError.setText("");
        });

        // Add an event listener to the surname field
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the surname field is changed
            lastNameError.setText("");
        });

        // Add an event listener to the address field
        addressField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the username field is changed
            addressError.setText("");
        });
        // Add an event listener to the phone number field
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the username field is changed
            phoneError.setText("");
        });

        // Add an event listener to the email field
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the name field is changed
            emailError.setText("");
        });

        // Add an event listener to the password field
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the error message when the password field is changed
            passwordError.setText("");
        });




    }
}
