package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
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

    @FXML
    private void volverIndiceCambio() throws IOException{

        Mangashelf.setRoot("Main");
    }

    @FXML
    private void borrarCambioContrasenya() throws IOException{

        nuevaContrasenya.clear();
        confirmacionContrasenya.clear();

    }

    @FXML
    private void enviarCambioContrasenya() throws IOException{

        if (nuevaContrasenya.getText().equals(confirmacionContrasenya.getText())) {

            //Aqui se debe realizar la consulta del cambio de contraseña

            mensajeCambio.setTextFill(Color.BLACK);
            mensajeCambio.setText("Contraseña actualizada correctamente");

        } else {

            mensajeCambio.setTextFill(Color.RED);
            mensajeCambio.setText("Las contraseñas no coinciden");

        }

    }

    @FXML
    private void eliminarCuenta() throws IOException{

        //Aqui se debe realizar la consulta para eliminar cuenta

        Mangashelf.setRoot("RegistroLogin");

    }

}
