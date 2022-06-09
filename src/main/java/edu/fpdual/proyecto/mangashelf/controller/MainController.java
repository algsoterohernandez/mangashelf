package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
import edu.fpdual.proyecto.mangashelf.Status;
import edu.fpdual.proyecto.mangashelf.client.AutorClient;
import edu.fpdual.proyecto.mangashelf.client.GeneroClient;
import edu.fpdual.proyecto.mangashelf.client.ObraClient;
import edu.fpdual.proyecto.mangashelf.client.ObraUsuarioClient;
import edu.fpdual.proyecto.mangashelf.controller.dto.Autor;
import edu.fpdual.proyecto.mangashelf.controller.dto.Genero;
import edu.fpdual.proyecto.mangashelf.controller.dto.Obra;
import edu.fpdual.proyecto.mangashelf.controller.dto.ObraUsuario;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController.
 *
 * Contiene todas las funciones a realizar en la ventana principal Main.
 *
 * @author ikisaki
 *
 */
public class MainController implements Initializable {

    static String obraSeleccionada;

    @FXML
    public Label nombreUsuario;

    @FXML
    private TextField buscador;

    @FXML
    private TilePane portadasMangas;

    @FXML
    private MenuButton filtroMenu;

    @FXML
    private Pane leidosBoton;

    @FXML
    private Pane enCursoBoton;

    @FXML
    private Pane pendienteBoton;

    @FXML
    private TilePane portadasMangas;

    /**
     * volverIndice.
     *
     * Reinicia la ventana Main.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void volverIndice() {

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        filtroMenu.setText("Filtro");
        buscador.setText("");
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        findAllObras();


    }

    /**
     * irInfo.
     *
     * Mueve al usuario a la página de Información.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void irInfo() throws IOException {

        Mangashelf.setRoot("Info");

    }

    /**
     * seleccionarCambiarContrasenya.
     *
     * Mueve al usuario a la página de Cambiar Contraseña.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarCambiarContrasenya() throws IOException {

        Mangashelf.setRoot("CambioContrasenya");

    }

    /**
     * findAllObras.
     *
     * Muestra todos los mangas.
     *
     * @author ikisaki
     *
     */
    @FXML
    public void findAllObras() {

        try {

            Obra[] obras = new ObraClient().findAll();

            for (Obra obra : obras) {

                crearPortada(obra.getId());

            }

        } catch (ExcepcionHTTP e) {

            System.out.println(e);

        }

    }

    /**
     * buscar.
     *
     * Realiza la búsqueda de mangas.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void buscar(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {

            realizarBusqueda();

        }

    }

    /**
     * seleccionarTitulo.
     *
     * Selecciona la opción Título del filtro.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarTitulo() {

        filtroMenu.setText("Título");

    }

    /**
     * seleccionarAutor.
     *
     * Selecciona la opción Autor del filtro.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarAutor() {

        filtroMenu.setText("Autor");

    }

    /**
     * seleccionarGenero.
     *
     * Selecciona la opción Género del filtro.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarGenero() {

        filtroMenu.setText("Género");

    }

    /**
     * findByNameObras.
     *
     * Muestra los mangas en base al nombre buscado.
     *
     * @author ikisaki
     *
     */
    @FXML
    public void findByNameObras(String nombre) {

        try {

            Obra[] obras = new ObraClient().findByName(nombre);

            for (Obra obra : obras) {

                crearPortada(obra.getId());

            }

        } catch (ExcepcionHTTP e) {

            System.out.println(e);

        }

    }

    /**
     * findByNameGenero.
     *
     * Muestra los mangas en base al género buscado.
     *
     * @author ikisaki
     *
     */
    @FXML
    public void findByNameGenero(String nombre) {

        try {

            Genero[] generos = new GeneroClient().findByName(nombre);

            for (Genero genero : generos) {

                crearPortada(genero.getTitulo());

            }

        } catch (ExcepcionHTTP e) {

            System.out.println(e);

        }

    }

    /**
     * findByNameAutor.
     *
     * Muestra los mangas en base al autor buscado.
     *
     * @author ikisaki
     *
     */
    @FXML
    public void findByNameAutor(String nombre) {

        try {

            Autor[] autores = new AutorClient().findByName(nombre);

            for (Autor autor : autores) {

                crearPortada(autor.getTitulo());
            }

        }catch (ExcepcionHTTP e){
            System.out.println(e);
        }

    }

    @FXML
    public void findAllObras(){
        try{
            Obra[] obras = new ObraClient().findAll();

            for(Obra obra : obras){
                crearPortada(obra.getId());
            }

        } catch (ExcepcionHTTP e) {

            System.out.println(e);

        }

    }

    /**
     * findByStatus.
     *
     * Muestra los mangas en base al estado del manga en la tabla del usuario.
     *
     * @author ikisaki
     *
     */
    @FXML
    public void findByStatus(Status status){
        try{
            ObraUsuario[] obras = new ObraUsuarioClient()
                    .findByStatus(RegistroLoginController.actualUser.getEmailUsuario(), status.toString());

            if(obras != null){
                for(ObraUsuario obra : obras){
                    crearPortada(obra.getObra());
                }
            }



        } catch (ExcepcionHTTP e) {

            System.out.println(e);

        }

    }

    /**
     * seleccionarPendiente.
     *
     * Muestra los mangas que se encuentran pendientes por el usuario.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarPendiente(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        findByStatus(Status.PENDIENTE);

    }

    /**
     * seleccionarEnCurso.
     *
     * Muestra los mangas que se encuentran en curso por el usuario.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarEnCurso() {

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        findByStatus(Status.LEYENDO);

    }

    /**
     * seleccionarLeidos.
     *
     * Muestra los mangas ya leídos por el usuario.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void seleccionarLeidos() {

        leidosBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        findByStatus(Status.LEIDO);

    }

    /**
     * realizarBusqueda.
     *
     * Realiza la búsqueda de mangas en base a lo buscado y el filtro.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void realizarBusqueda() {

        String busqueda = buscador.getText();

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));

        if (filtroMenu.getText().equals("Título")) {

            portadasMangas.getChildren().removeAll(portadasMangas.getChildren());
            findByNameObras(busqueda);


        } else if (filtroMenu.getText().equals("Autor")) {

            portadasMangas.getChildren().removeAll(portadasMangas.getChildren());
            findByNameAutor(busqueda);


        } else if (filtroMenu.getText().equals("Género")) {

            portadasMangas.getChildren().removeAll(portadasMangas.getChildren());
            findByNameGenero(busqueda);

        } else {

            portadasMangas.getChildren().removeAll(portadasMangas.getChildren());
            findByNameObras(busqueda);

        }

    }

    /**
     * crearPortada.
     *
     * Muestra los mangas por pantalla.
     *
     * @author ikisaki
     *
     */
    @FXML
    private void crearPortada(String nombreManga) {

        Image portada = new Image("edu/fpdual/proyecto/mangashelf/static.img/portadas/"+nombreManga+".png");
        ImageView imagenView = new ImageView(portada);

        imagenView.setOnMouseClicked(mouseEvent -> {

            try {

                obraSeleccionada = nombreManga;
                irInfo();

            } catch (IOException e) {

                e.printStackTrace();

            }
        });

        imagenView.setFitHeight(159);
        imagenView.setFitWidth(98);
        imagenView.setAccessibleRole(AccessibleRole.BUTTON);
        imagenView.setCursor(Cursor.HAND);

        portadasMangas.getChildren().add(imagenView);

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

        findAllObras();
        nombreUsuario.setText(RegistroLoginController.actualUser.getEmailUsuario());

    }

}
