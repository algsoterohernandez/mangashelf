package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class InfoController {


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
    private void anyadirEnCurso(){

        //Aqui se realizaria la consulta que añade el manga a leyendo

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

}
