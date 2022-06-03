package edu.fpdual.proyecto.mangashelf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class CambioContrasenyaController {

    @FXML
    private PasswordField nuevaContrasenya;

    @FXML
    private PasswordField confirmacionContrasenya;

    @FXML
    private Button enviarCambioBoton;

    @FXML
    private Button borrarCambioBoton;

    @FXML
    private void borrarCambioContrasenya() throws IOException{

        nuevaContrasenya.clear();
        confirmacionContrasenya.clear();

    }

}
