package edu.fpdual.proyecto.mangashelf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistroLoginController {

    private static String emailUsuario;
    private static String contrasenyaUsuario;

    @FXML
    private TabPane pestanyas;

    @FXML
    private TextField emailRegistro;

    @FXML
    private TextField contrasenyaRegistro;

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

    @FXML
    private void cambiarInicioSesion() throws IOException {

        pestanyas.getSelectionModel().select(1);

        emailRegistro.clear();
        contrasenyaRegistro.clear();

    }

    @FXML
    private void registrarUsuario() throws IOException {

        emailUsuario = emailRegistro.getText();
        contrasenyaUsuario = contrasenyaRegistro.getText();

    }

    @FXML
    private void iniciarSesionUsuario() throws IOException {

        emailUsuario = emailInicio.getText();
        contrasenyaUsuario = contrasenyaInicio.getText();

    }



}
