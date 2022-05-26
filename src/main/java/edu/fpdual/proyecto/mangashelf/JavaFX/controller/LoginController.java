package edu.fpdual.proyecto.mangashelf.JavaFX.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    public TextField usernameField;
    public Button resetButton;
    public Button loginButton;
    public ImageView logo;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void logData(){
        if(usernameField.getText().equals("usuario") && passwordField.getText().equals("Feriadeabril1")){
            showAlert("Registered User");
        }else{
            showAlert("the data entered is not correct. Please try again");
        }
    }

    public void resetAction(){
        usernameField.clear();
        passwordField.clear();
    }

    public void showAlert(String text){
        Label secondLabel = new Label (text);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);


        Stage newWindow = new Stage();
        newWindow.setTitle("Alerta de sesión");
        newWindow.setScene(secondScene);

        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.show();
    }

}