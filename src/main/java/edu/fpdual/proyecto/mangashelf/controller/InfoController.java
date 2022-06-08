package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.client.ObraUsuarioClient;
import edu.fpdual.proyecto.mangashelf.controller.dto.Obra;
import edu.fpdual.proyecto.mangashelf.controller.dto.ObraUsuario;
import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {

    @FXML
    private Label comentarioInfo;

    @FXML
    private Label numCapitulosLeidos;

    @FXML
    private void anyadirLeido(){

        //Aqui se realizaria la consulta que añade el manga a leidos

        comentarioInfo.setText("El manga se ha añadido a Leídos");

    }

    @FXML
    private void anyadirEnCurso(ObraUsuario obus) throws ExcepcionHTTP {
        ObraUsuario obraUsuario = new ObraUsuarioClient().addObra(obus);
        System.out.println(obraUsuario);

        comentarioInfo.setText("El manga se ha añadido a En Curso");

    }

    @FXML
    private void anyadirPendiente(){

        //Aqui se realizaria la consulta que añade el manga a pendiente

        comentarioInfo.setText("El manga se ha añadido a Pendiente");

    }

    @FXML
    private void eliminarLista(){

        //Aqui se realizaria la consulta que elimina el manga de la lista del usuario

        comentarioInfo.setText("El manga se ha eliminado de su lista");

    }

    @FXML
    private void volverIndice() throws IOException {

        Mangashelf.setRoot("Main");

    }

    @FXML
    private void sumarCapitulo(){

        //Aqui se realizaria la consulta que suma un capitulo a los capitulos leidos

        numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) + 1));

    }

    @FXML
    private void restarCapitulo(){

        //Aqui se realizaria la consulta que resta un capitulo a los capitulos leidos

        numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) - 1));

    }

    @FXML
    private void comprobarObra() throws ExcepcionHTTP {
        ObraUsuario obus = new ObraUsuarioClient().
                findByID(RegistroLoginController.actualUser.getEmailUsuario(), MainController.obraSeleccionada);

        if(obus == null){
            anyadirEnCurso(new ObraUsuario(RegistroLoginController.
                    actualUser.getEmailUsuario(), MainController.obraSeleccionada, 1, "LEYENDO"));
        }else{
            if(obus.getEstado().equals("LEYENDO")) {

            }
            else if(obus.getEstado().equals("PENDIENTE")) {

            }else{

            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comprobarObra();

        } catch (ExcepcionHTTP e) {
            e.printStackTrace();
        }

    }
}
