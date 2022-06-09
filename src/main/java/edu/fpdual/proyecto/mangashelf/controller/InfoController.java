package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.Status;
import edu.fpdual.proyecto.mangashelf.client.*;
import edu.fpdual.proyecto.mangashelf.controller.dto.*;
import edu.fpdual.proyecto.mangashelf.client.ObraClient;
import edu.fpdual.proyecto.mangashelf.client.ObraUsuarioClient;
import edu.fpdual.proyecto.mangashelf.controller.dto.Obra;
import edu.fpdual.proyecto.mangashelf.controller.dto.ObraUsuario;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * InfoController.
 *
 * Contiene todas las funciones a realizar en la ventana de Información.
 *
 * @author ikisaki
 *
 */
public class InfoController implements Initializable {

    @FXML
    private ImageView portadaManga;

    @FXML
    private Label tituloManga;

    @FXML
    private Label autorManga;

    @FXML
    private Label generoManga;

    @FXML
    private Label capitulosManga;

    @FXML
    private Label anyoPublicacion;

    @FXML
    private Label anyoFinalizacion;

    @FXML
    private Label numCapitulosLeidos;

    @FXML
    private ImageView pendientesBoton;

    @FXML
    private ImageView enCursoBoton;

    @FXML
    private ImageView finalizadosBoton;

    @FXML
    private ImageView eliminarImg;

    @FXML
    private Label eliminarTexto;

    @FXML
    private Label comentarioInfo;

    /**
     * actualizarInfo.
     *
     * Actualiza la información de la pestaña de Información con una serie de datos (Portada, Título, Autor, etc) en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void actualizarInfo() throws ExcepcionHTTP {

        Obra obra = new ObraClient().findByID(MainController.obraSeleccionada);

        Image portada = new Image("edu/fpdual/proyecto/mangashelf/static.img/portadas/"+obra.getId()+".png");
        portadaManga.setImage(portada);
        tituloManga.setText(obra.getTitulo());
        capitulosManga.setText(obra.getCapitulosTotales());
        anyoPublicacion.setText(String.valueOf(obra.getAnyoPublicacion()));
        anyoFinalizacion.setText(obra.getAnyoTermino());

        Autor autor = new AutorClient().findByID(MainController.obraSeleccionada);

        autorManga.setText(autor.getNombre());

        Genero genero = new GeneroClient().findByID(MainController.obraSeleccionada);

        generoManga.setText(genero.getGenero());

    }

    /**
     * volverIndice.
     *
     * Devuelve al usuario a la página principal Main.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void volverIndice() throws IOException {

        Mangashelf.setRoot("Main");

    }

    /**
     * addObra.
     *
     * Añade a la base de datos el registro referente al usuario y el manga seleccionado en la tabla obra_usuario.
     *
     * @author ikisaki
     *
     */
    private void addObra () throws ExcepcionHTTP {

        ObraUsuario obus = new ObraUsuario(RegistroLoginController.actualUser.getEmailUsuario(), MainController.obraSeleccionada, 0, Status.PENDIENTE.toString());
        new ObraUsuarioClient().addObra(obus);

    }

    /**
     * anyadirPendiente.
     *
     * Actualiza el estado de la base de datos del usuario a PENDIENTE en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void anyadirPendiente() throws ExcepcionHTTP {

        ObraUsuario obus = comprobarObra();

        obus.setEstado(Status.PENDIENTE.toString());

        new ObraUsuarioClient().updateStatus(obus);

        comentarioInfo.setText("El manga se ha añadido a Pendientes");

        comprobarEstado();

    }

    /**
     * anyadirEnCurso.
     *
     * Actualiza el estado de la base de datos del usuario a LEYENDO en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void anyadirEnCurso() throws ExcepcionHTTP {
        ObraUsuario obus = comprobarObra();

        obus.setEstado(Status.LEYENDO.toString());
        new ObraUsuarioClient().updateStatus(obus);

        comentarioInfo.setText("El manga se ha añadido a En Curso");
        comprobarEstado();

    }

    /**
     * anyadirLeido.
     *
     * Actualiza el estado de la base de datos del usuario a LEIDO en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void anyadirLeido() throws ExcepcionHTTP {

        ObraUsuario obus = comprobarObra();
        Obra obra = new ObraClient().findByID(MainController.obraSeleccionada);

        if (!obra.getCapitulosTotales().equals("En publicacion")) {

            obus.setCapitulosLeidos(Integer.parseInt(obra.getCapitulosTotales()));
            obus.setEstado(Status.LEIDO.toString());

            new ObraUsuarioClient().updateStatus(obus);

            comentarioInfo.setText("El manga se ha añadido a Finalizados");
            numCapitulosLeidos.setText(String.valueOf(obus.getCapitulosLeidos()));
            comentarioInfo.setText("El manga se ha añadido a Finalizados");

        } else {

            comentarioInfo.setText("El manga está aún en publicación");

        }

        comprobarEstado();

    }

    /**
     * eliminarLista.
     *
     * Elimina de la base de datos el registro referente al usuario y el manga seleccionado en la tabla obra_usuario.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void eliminarLista() throws ExcepcionHTTP {

        ObraUsuario obus = comprobarObra();

        new ObraUsuarioClient().deleteObra(obus.getUsuario(), obus.getObra());

        numCapitulosLeidos.setText(String.valueOf(0));
        comentarioInfo.setText("El manga se ha eliminado de su lista");

        comprobarEstado();

    }

    /**
     * sumarCapitulo.
     *
     * Suma uno al número de capítulos que ha leído el usuario en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void sumarCapitulo() throws ExcepcionHTTP {

        ObraUsuario obus = comprobarObra();
        Obra obra = new ObraClient().findByID(MainController.obraSeleccionada);

        if (!obra.getCapitulosTotales().equals("En publicacion")) {

            if (Integer.parseInt(obra.getCapitulosTotales()) > obus.getCapitulosLeidos()) {

                new ObraUsuarioClient().sumChap(obus);

                numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) + 1));

            }

        } else {

            new ObraUsuarioClient().sumChap(obus);

            numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) + 1));

        }

        if (obus.getEstado().equals(Status.PENDIENTE.toString()) && obus.getCapitulosLeidos() == 0) {

            anyadirEnCurso();

        } else {

            comprobarEstado();

        }

    }

    /**
     * restarCapitulo.
     *
     * Resta uno al número de capítulos que ha leído el usuario en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void restarCapitulo() throws ExcepcionHTTP {

        ObraUsuario obus = new ObraUsuarioClient().findByID(RegistroLoginController.actualUser.getEmailUsuario(), MainController.obraSeleccionada);

        if (obus.getCapitulosLeidos() > 0) {

            new ObraUsuarioClient().resChap(obus);

            numCapitulosLeidos.setText(String.valueOf(Integer.parseInt(numCapitulosLeidos.getText()) - 1));

        }

    }

    /**
     * comprobarObra.
     *
     * Comprueba si el usuario ya posee un registro en la base de datos en base al manga seleccionado.
     *
     * @author ikisaki
     *
     */
    @FXML
    private ObraUsuario comprobarObra() throws ExcepcionHTTP {

        ObraUsuario obus = new ObraUsuarioClient().findByID(RegistroLoginController.actualUser.getEmailUsuario(), MainController.obraSeleccionada);

        if (obus == null) {

            addObra();

        }

        return new ObraUsuarioClient().findByID(RegistroLoginController.actualUser.getEmailUsuario(), MainController.obraSeleccionada);

    }

    /**
     * comprobarEstado.
     *
     * Comprueba el estado del manga seleccionado en el registro del usuario de la base de datos (Altera la deshabilitación de los botones en base al estado del manga).
     *
     * @author ikisaki
     *
     */
    @FXML
    private void comprobarEstado() throws ExcepcionHTTP {

        ObraUsuario obus = new ObraUsuarioClient().findByID(RegistroLoginController.actualUser.getEmailUsuario(), MainController.obraSeleccionada);

        if (obus == null) {

            pendientesBoton.setDisable(false);
            enCursoBoton.setDisable(false);
            finalizadosBoton.setDisable(false);
            eliminarImg.setDisable(true);
            eliminarTexto.setDisable(true);
            Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amor.png");
            pendientesBoton.setImage(iconoPendiente);
            Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendo.png");
            enCursoBoton.setImage(iconoEnCurso);
            Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/libros.png");
            finalizadosBoton.setImage(iconoFinalizados);
            numCapitulosLeidos.setText(String.valueOf(0));

        } else {

            numCapitulosLeidos.setText(String.valueOf(obus.getCapitulosLeidos()));

            if (obus.getEstado().equals(Status.LEYENDO.toString())) {

                pendientesBoton.setDisable(false);
                enCursoBoton.setDisable(true);
                finalizadosBoton.setDisable(false);
                eliminarImg.setDisable(false);
                eliminarTexto.setDisable(false);
                Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amor.png");
                pendientesBoton.setImage(iconoPendiente);
                Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendoverde.png");
                enCursoBoton.setImage(iconoEnCurso);
                Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/libros.png");
                finalizadosBoton.setImage(iconoFinalizados);

            } else if (obus.getEstado().equals(Status.PENDIENTE.toString())) {

                pendientesBoton.setDisable(true);
                enCursoBoton.setDisable(false);
                finalizadosBoton.setDisable(false);
                eliminarImg.setDisable(false);
                eliminarTexto.setDisable(false);
                Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amorverde.png");
                pendientesBoton.setImage(iconoPendiente);
                Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendo.png");
                enCursoBoton.setImage(iconoEnCurso);
                Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/libros.png");
                finalizadosBoton.setImage(iconoFinalizados);

            } else {

                pendientesBoton.setDisable(false);
                enCursoBoton.setDisable(false);
                finalizadosBoton.setDisable(true);
                eliminarImg.setDisable(false);
                eliminarTexto.setDisable(false);
                Image iconoPendiente = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/amor.png");
                pendientesBoton.setImage(iconoPendiente);
                Image iconoEnCurso = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/leyendo.png");
                enCursoBoton.setImage(iconoEnCurso);
                Image iconoFinalizados = new Image("edu/fpdual/proyecto/mangashelf/static.img/iconos/librosverde.png");
                finalizadosBoton.setImage(iconoFinalizados);

            }

        }

    }

    /**
     * initialize.
     *
     * Ejecuta las funciones que se encuentren en su interior al acceder a la ventana.
     *
     * @author ikisaki
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            actualizarInfo();
            comprobarEstado();

        } catch (ExcepcionHTTP e) {

            e.printStackTrace();

        }

    }

}
