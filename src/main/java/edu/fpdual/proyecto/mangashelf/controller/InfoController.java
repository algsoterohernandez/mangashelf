package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.Status;
import edu.fpdual.proyecto.mangashelf.client.ObraUsuarioClient;
import edu.fpdual.proyecto.mangashelf.controller.dto.Obra;
import edu.fpdual.proyecto.mangashelf.controller.dto.ObraUsuario;
import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {

    @FXML
    private Label comentarioInfo;

    @FXML
    private Label numCapitulosLeidos;

    @FXML
    private ImageView pendientesBotonInfo;

    @FXML
    private ImageView enCursoBotonInfo;

    @FXML
    private ImageView finalizadosBotonInfo;

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

            pendientesBotonInfo.setDisable(false);
            enCursoBotonInfo.setDisable(false);
            finalizadosBotonInfo.setDisable(false);
            Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amor.png");
            pendientesBotonInfo.setImage(iconoPendiente);
            Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendo.png");
            enCursoBotonInfo.setImage(iconoEnCurso);
            Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/libros.png");
            finalizadosBotonInfo.setImage(iconoFinalizados);

        }else{
            if(obus.getEstado().equals(Status.LEYENDO.toString())) {

                pendientesBotonInfo.setDisable(false);
                enCursoBotonInfo.setDisable(true);
                finalizadosBotonInfo.setDisable(false);
                Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amor.png");
                pendientesBotonInfo.setImage(iconoPendiente);
                Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendoverde.png");
                enCursoBotonInfo.setImage(iconoEnCurso);
                Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/libros.png");
                finalizadosBotonInfo.setImage(iconoFinalizados);

            } else if (obus.getEstado().equals(Status.PENDIENTE.toString())) {

                pendientesBotonInfo.setDisable(true);
                enCursoBotonInfo.setDisable(false);
                finalizadosBotonInfo.setDisable(false);
                Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amorverde.png");
                pendientesBotonInfo.setImage(iconoPendiente);
                Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendo.png");
                enCursoBotonInfo.setImage(iconoEnCurso);
                Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/libros.png");
                finalizadosBotonInfo.setImage(iconoFinalizados);

            }else{

                pendientesBotonInfo.setDisable(false);
                enCursoBotonInfo.setDisable(false);
                finalizadosBotonInfo.setDisable(true);
                Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amor.png");
                pendientesBotonInfo.setImage(iconoPendiente);
                Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendo.png");
                enCursoBotonInfo.setImage(iconoEnCurso);
                Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/librosverde.png");
                finalizadosBotonInfo.setImage(iconoFinalizados);

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
