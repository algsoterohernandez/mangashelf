package edu.fpdual.proyecto.mangashelf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistroLoginController {

    private static String emailUsuario;

    @FXML
    private Button registrarBoton;

    @FXML
    private Button borrarRegistroBoton;

    @FXML
    private TextField emailRegistro;

    @FXML
    private TextField contrasenyaRegistro;

    @FXML
    private Button entrarBoton;

    @FXML
    private Button borrarIniciarBoton;

    @FXML
    private TextField emailInicio;

    @FXML
    private TextField contrasenyaInicio;

    @FXML
    private void borrarRegistrar() throws IOException {

        emailRegistro.clear();
        contrasenyaRegistro.clear();

    }

    @FXML
    private void borrarIniciar() throws IOException {

        emailInicio.clear();
        contrasenyaInicio.clear();

    }

}
