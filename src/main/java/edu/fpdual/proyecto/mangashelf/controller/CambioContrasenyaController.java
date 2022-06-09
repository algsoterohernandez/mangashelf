package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.client.UsuariosClient;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class CambioContrasenyaController {

    @FXML
    private PasswordField nuevaContrasenya;

    @FXML
    private PasswordField confirmacionContrasenya;

    @FXML
    private Label mensajeCambio;

    /**
     * volverIndiceCambio.
     *
     * Retorna a la ventana principal Main.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void volverIndiceCambio() throws IOException{

        Mangashelf.setRoot("Main");
    }

    /**
     * enviarCambioContrasenya.
     *
     * Actualiza la contraseña del usuario con la nueva contraseña escrita.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void enviarCambioContrasenya() {

        if (nuevaContrasenya.getText().equals(confirmacionContrasenya.getText())) {

            try {

                RegistroLoginController.actualUser.setContrasenyaUsuario(nuevaContrasenya.getText());
                new UsuariosClient().changePwd(RegistroLoginController.actualUser);

                mensajeCambio.setTextFill(Color.BLACK);
                mensajeCambio.setText("Contraseña actualizada correctamente");

            } catch (ExcepcionHTTP e) {

                mensajeCambio.setTextFill(Color.RED);
                mensajeCambio.setText(e.getMessage());

            }

        } else {

            mensajeCambio.setTextFill(Color.RED);
            mensajeCambio.setText("Las contraseñas no coinciden");

        }

    }

    /**
     * borrarCambioContrasenya.
     *
     * Vacía los campos de nueva contraseña y confirmación de nueva contraseña.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void borrarCambioContrasenya() {

        nuevaContrasenya.clear();
        confirmacionContrasenya.clear();

    }

    /**
     * eliminarCuenta.
     *
     * Elimina al usuario de la base de datos y todos los registros relacionados.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void eliminarCuenta() throws IOException{

        try {

            new UsuariosClient().deleteUser(RegistroLoginController.actualUser.getEmailUsuario());
            Mangashelf.setRoot("RegistroLogin");

        } catch (ExcepcionHTTP e) {

            mensajeCambio.setTextFill(Color.RED);
            mensajeCambio.setText(e.getMessage());

        }

    }

}
