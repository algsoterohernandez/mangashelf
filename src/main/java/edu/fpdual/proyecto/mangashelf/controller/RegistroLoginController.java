package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.client.UsuariosClient;
import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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

    @FXML
    private CheckBox suscripcion;

    //Borra los datos introducidos en la ventana de registro
    @FXML
    private void borrarRegistrar() throws IOException {

        emailRegistro.clear();
        contrasenyaRegistro.clear();

    }

    //Borra los datos introducidos en la ventana de inicio de sesión
    @FXML
    private void borrarIniciar() throws IOException {

        emailInicio.clear();
        contrasenyaInicio.clear();

    }

    //Cambia a la ventana de iniciar sesión y borra los datos introducidos en la de registro
    @FXML
    private void cambiarInicioSesion() throws IOException {

        pestanyas.getSelectionModel().select(1);

        emailRegistro.clear();
        contrasenyaRegistro.clear();
        comentarioRegistro.setText("");

    }

    //Comprueba si el usuario ya posee una cuenta, si la tiene mostrará que ya tiene una cuenta,
    //si no, le registrará en la base de datos y abrirá la main
    @FXML
    private void registrarUsuario() throws IOException {

        emailUsuario = emailRegistro.getText();
        contrasenyaUsuario = contrasenyaRegistro.getText();

        if (emailUsuario.equals("")) {

            comentarioRegistro.setTextFill(Color.RED);
            comentarioRegistro.setText("Introduzca un email");

        } else if (contrasenyaUsuario.equals("")) {

            comentarioRegistro.setTextFill(Color.RED);
            comentarioRegistro.setText("Introduzca la contraseña");

        } else {

            try {
                Usuarios newUser = new Usuarios(emailUsuario, contrasenyaUsuario);

                new UsuariosClient().createUser(newUser);
                comentarioRegistro.setText("");
                Mangashelf.setRoot("Main");

                if (suscripcion.isSelected()) {

                    //Aquí debe enviarse una newsletter al email del usuario

                    System.out.println("Ahora se le habría enviado un email al usuario (Newsletter)");

                }
            } catch (ExcepcionHTTP e) {
                comentarioRegistro.setTextFill(Color.RED);
                comentarioRegistro.setText("El usuario ya existe, inicie sesión");
            }


        }

    }

    //Comprueba si el usuario ya posee una cuenta, si la tiene abrirá la main,
    //si no, mostrará un mensaje de que esa cuenta no existe
    @FXML
    private void iniciarSesionUsuario() throws IOException {

        emailUsuario = emailInicio.getText();
        contrasenyaUsuario = contrasenyaInicio.getText();

        if (emailUsuario.equals("")) {

            comentarioInicio.setTextFill(Color.RED);
            comentarioInicio.setText("Introduzca un email");

        } else if (contrasenyaUsuario.equals("")) {

            comentarioInicio.setTextFill(Color.RED);
            comentarioInicio.setText("Introduzca la contraseña");

        } else {

            //Aquí debe realizarse la/s consulta/s a la BBDD con sus casos

            // Ejemplo : new UsuariosClient().createUser(emailUsuario, contrasenyaUsuario);

            comentarioInicio.setText("");

            Mangashelf.setRoot("Main");

        }

    }

    //Si el usuario olvida su contraseña, se genera una aleatoria, se actualiza en su base de datos y
    //se le envía un correo con la nueva
    @FXML
    private void generarContrasenya() throws IOException {

        emailUsuario = emailInicio.getText();

        if (emailUsuario.equals("")) {

            comentarioInicio.setTextFill(Color.RED);
            comentarioInicio.setText("Introduzca un email para enviarle una nueva contraseña");

        } else {

            String nuevaContrasenya = cadenaAleatoria(8);

            //Aquí se debe mandar un correo al email del usuario con la nueva contraseña

            comentarioInicio.setTextFill(Color.BLACK);
            comentarioInicio.setText("Se ha enviado una nueva contraseña al email "+emailUsuario+" "+nuevaContrasenya);

        }

    }

    //Generador de contraseñas aleatorias
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
