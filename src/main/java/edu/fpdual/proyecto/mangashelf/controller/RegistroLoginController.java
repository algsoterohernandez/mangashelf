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

    static Usuarios actualUser;

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


    /** La función borrarRegistrar():
     * Permite borrar los datos introducidos en la ventana de registro (crear cuenta).
     * */
    @FXML
    private void borrarRegistrar(){

        emailRegistro.clear();
        contrasenyaRegistro.clear();

    }

    /** La función borrarIniciar():
     * Permite borrar los datos introducidos en la ventana de inicio de sesión (iniciar sesión)
     * */
    @FXML
    private void borrarIniciar(){

        emailInicio.clear();
        contrasenyaInicio.clear();

    }

    /** La función cambiarInicioSesion():
     * Cambia a la ventanan de inicio de sesión y elimina los datos introducidos en la ventana
     * del registro
     * */
    @FXML
    private void cambiarInicioSesion(){

        pestanyas.getSelectionModel().select(1);

        emailRegistro.clear();
        contrasenyaRegistro.clear();
        comentarioRegistro.setText("");

    }

    /** La función registrarUsuario():
     * Hace una comprobación para ver si el usuario ya tiene cuenta.
     * Si el usuario no tiene cuenta, lo registrará en la BBDD y abrirá la pantalla principal de la app
     * Además si ha seleccionado la suscripción a la newsletter, se enviará un email confirmando suscripción
     * */
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

                actualUser = new UsuariosClient().createUser(newUser);

                comentarioRegistro.setText("");
                Mangashelf.setRoot("Main");

                if (suscripcion.isSelected()) {

                    //Aquí debe enviarse una newsletter al email del usuario
                    System.out.println("Ahora se le habría enviado un email al usuario (Newsletter)");
                }
            } catch (ExcepcionHTTP e) {
                comentarioRegistro.setTextFill(Color.RED);
                comentarioRegistro.setText(e.getMessage());
            }
        }
    }

   /** La función iniciarSesionUsuario():
    * Realiza comprobación de igualdad con los datos de la BBDD.
    * Si los datos introducidos con coincidentes, abrirá la pantalla principal de la app,
    * en caso contrario saltará una excepción.
    * */
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

            try {

                Usuarios user = new Usuarios(emailUsuario, contrasenyaUsuario);
                actualUser = new UsuariosClient().loginUser(user);

                comentarioInicio.setText("");
                Mangashelf.setRoot("Main");

            } catch (ExcepcionHTTP e) {
                comentarioInicio.setTextFill(Color.RED);
                comentarioInicio.setText(e.getMessage());
            }
        }

    }

   /** La función generarContrasenya():
    * Si el usuario ha olvidado su contraseña, se generará una nueva contraseña aleatoria.
    * Para ello, al usuario se le pedirá que indique su email y se le enviará.
    * La contraseña aleatoria sustituirá a la contraseña inicial en la BBDD.
    * El usuario podrá acceder con esa contraseña y desde su perfil modificarla para poner una nueva
    * contraseña.
    * */
    @FXML
    private void generarContrasenya() throws ExcepcionHTTP {

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


   /** La función cadenaAleatoria():
    * Permite generar una cadena de caracteres aleatoria como medida de seguridad para el usuario
    * y la designará como una nueva contraseña
    * */
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
