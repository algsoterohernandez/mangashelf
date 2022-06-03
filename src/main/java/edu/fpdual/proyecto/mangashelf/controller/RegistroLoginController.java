package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.client.UsuariosClient;
import jakarta.ws.rs.core.Response;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

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
    private Label comentarioRegistro;

    @FXML
    private TextField emailInicio;

    @FXML
    private TextField contrasenyaInicio;

    @FXML
    private Label comentarioInicio;

    //Borra los datos introducidos en la ventana de registro
    @FXML
    private void borrarRegistrar() throws IOException {

        emailRegistro.clear();
        contrasenyaRegistro.clear();

    }

    //Borra los datos introducidos en la ventana de inicio de sesion
    @FXML
    private void borrarIniciar() throws IOException {

        emailInicio.clear();
        contrasenyaInicio.clear();

    }

    //Cambia a la ventana de iniciar sesion y borra los datos introducidos en la de registro
    @FXML
    private void cambiarInicioSesion() throws IOException {

        pestanyas.getSelectionModel().select(1);

        emailRegistro.clear();
        contrasenyaRegistro.clear();

    }

    //Comprueba si el usuario ya posee una cuenta, si la tiene mostrara que ya tiene una cuenta,
    //si no, le registrara en la base de datos y abrira la main
    @FXML
    private void registrarUsuario() throws IOException {

        emailUsuario = emailRegistro.getText();
        contrasenyaUsuario = contrasenyaRegistro.getText();

        new UsuariosClient().createUser(emailUsuario, contrasenyaUsuario);

        System.out.println("Hola");

    }

    //Comprueba si el usuario ya posee una cuenta, si la tiene abrira la main,
    //si no, mostrara un mensaje de que esa cuenta no existe
    @FXML
    private void iniciarSesionUsuario() throws IOException {

        emailUsuario = emailInicio.getText();
        contrasenyaUsuario = contrasenyaInicio.getText();

    }

    //Si el usuario olvida su contrasenya, se genera una aleatoria, se actualiza en su base de datos y
    //se le envia un correo con la nueva
    @FXML
    private void generarContrasenya() throws IOException {

        emailUsuario = emailInicio.getText();

        if (emailUsuario.equals("")) {

            comentarioInicio.setTextFill(Color.RED);
            comentarioInicio.setText("Introduzca un email para enviarle una nueva contraseña");

        } else {

            String nuevaContrasenya = cadenaAleatoria(8);

            comentarioInicio.setTextFill(Color.BLACK);
            comentarioInicio.setText("Se ha enviado una nueva contraseña al email "+emailUsuario+" "+nuevaContrasenya);

        }

    }

    //Generador de contrasenyas aleatorias
    public static String cadenaAleatoria(int longitud) {

        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        String nuevaContrasenya = "";

        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, caracteres.length() - 1);
            char caracterAleatorio = caracteres.charAt(indiceAleatorio);
            nuevaContrasenya += caracterAleatorio;
        }

        return nuevaContrasenya;
    }

    public static int numeroAleatorioEnRango(int minimo, int maximo) {

        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

}
