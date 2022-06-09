package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.Status;
import edu.fpdual.proyecto.mangashelf.client.ObraClient;
import edu.fpdual.proyecto.mangashelf.client.ObraUsuarioClient;
import edu.fpdual.proyecto.mangashelf.client.UsuariosClient;
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
    private void anyadirLeido(ObraUsuario obus) throws ExcepcionHTTP {

        //Aqui se realizaria la consulta que añade el manga a leidos
        Obra obra = new ObraClient().findByID(MainController.obraSeleccionada);

        if(!obra.getCapitulosTotales().equals("En publicacion")){
            obus.setCapitulosLeidos(Integer.parseInt(obra.getCapitulosTotales()));
            obus.setEstado(Status.LEIDO.toString());
            new ObraUsuarioClient().updateStatus(obus);
            comentarioInfo.setText("El manga se ha añadido a Leídos");
        }else{
            comentarioInfo.setText("El manga está aún en publicación");
        }

        comprobarObra();

    }

    private void addObra (ObraUsuario obus) throws ExcepcionHTTP {
        ObraUsuario obraUsuario = new ObraUsuarioClient().addObra(obus);
        anyadirEnCurso(obus);
    }

    @FXML
    private void anyadirEnCurso(ObraUsuario obus) throws ExcepcionHTTP {
        obus.setEstado(Status.LEYENDO.toString());
        new ObraUsuarioClient().updateStatus(obus);

        comentarioInfo.setText("El manga se ha añadido a En Curso");
        comprobarObra();

    }

    @FXML
    private void anyadirPendiente(ObraUsuario obus) throws ExcepcionHTTP {
        obus.setEstado(Status.PENDIENTE.toString());
        new ObraUsuarioClient().updateStatus(obus);

        comentarioInfo.setText("El manga se ha añadido a Pendiente");
        comprobarObra();

    }

    @FXML
    private void eliminarLista(ObraUsuario obus) throws ExcepcionHTTP {

        new ObraUsuarioClient().deleteObra(obus.getUsuario(), obus.getObra());
        comentarioInfo.setText("El manga se ha eliminado de su lista");
        comprobarObra();

    }

    @FXML
    private void volverIndice() throws IOException {

        Mangashelf.setRoot("Main");

    }

    @FXML
    private void sumarCapitulo(ObraUsuario obus) throws ExcepcionHTTP {

        //Aqui se realizaria la consulta que suma un capitulo a los capitulos leidos

        Obra obra = new ObraClient().findByID(MainController.obraSeleccionada);

        if(!obra.getCapitulosTotales().equals("En publicacion")){
            if(Integer.parseInt(obra.getCapitulosTotales()) > obus.getCapitulosLeidos()){
                new ObraUsuarioClient().sumChap(obus);
                numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) + 1));
            }
        }else{
            new ObraUsuarioClient().sumChap(obus);
            numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) + 1));
        }

        if(obus.getEstado().equals(Status.PENDIENTE) && obus.getCapitulosLeidos() == 0){
            anyadirEnCurso(obus);
        }else{
            comprobarObra();
        }



    }



    @FXML
    private void restarCapitulo(ObraUsuario obus) throws ExcepcionHTTP {

        //Aqui se realizaria la consulta que resta un capitulo a los capitulos leidos
        if(obus.getCapitulosLeidos() > 0){
            new ObraUsuarioClient().resChap(obus);
            numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) - 1));
        }



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
