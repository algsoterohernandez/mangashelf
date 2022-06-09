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
    private void irInfo() throws IOException {
        Mangashelf.setRoot("Info");

    }
    @FXML
    public void findByNameGenero(String nombre){
        try{
            Genero[] generos = new GeneroClient().findByName(nombre);

            for(Genero genero : generos){
                crearPortada(genero.getTitulo());
            }

        }catch (ExcepcionHTTP e){
            System.out.println(e);
        }

    }
    @FXML
    public void findByNameAutor(String nombre){
        try{
            Autor[] autores = new AutorClient().findByName(nombre);

            for(Autor autor : autores){
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

        }catch (ExcepcionHTTP e){
            System.out.println(e);
        }

    }

    @FXML
    public void findByStatus(Status status){
        try{
            ObraUsuario[] obras = new ObraUsuarioClient()
                    .findByStatus(RegistroLoginController.actualUser.getEmailUsuario(), status.toString());

            for(ObraUsuario obra : obras){
                crearPortada(obra.getObra());
            }

        }catch (ExcepcionHTTP e){
            System.out.println(e);
        }
    }

    @FXML
    public void findByNameObras(String nombre){
        try{
            Obra[] obras = new ObraClient().findByName(nombre);

            for(Obra obra : obras){
                crearPortada(obra.getId());
            }

        }catch (ExcepcionHTTP e){
            System.out.println(e);
        }

    }

    //Cuando se haga click en Mangashelf, o en su logo, se reiniciará la página
    @FXML
    private void volverIndice(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        filtroMenu.setText("Filtro");
        buscador.setText("");
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());
        findAllObras();


    }

    //Cuando se pulse la tecla ENTER en el buscador realizará la búsqueda de los mangas con lo escrito
    @FXML
    private void buscar(KeyEvent event) throws IOException {

        if (event.getCode() == KeyCode.ENTER) {

            realizarBusqueda();

        }

    }

    //Cuando el botón Leídos es pulsado, cambia de color y se muestran los mangas leídos por el usuario
    @FXML
    private void seleccionarLeidos(){

        leidosBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        //Aqui debe realizarse la consulta de mangas leidos por el usuario y mostrarlos
        findByStatus(Status.LEIDO);

    }

    //Cuando el botón En Curso es pulsado, cambia de color y se muestran los mangas que se encuentran en curso por el usuario
    @FXML
    private void seleccionarEnCurso(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        //Aqui debe realizarse la consulta de mangas en curso por el usuario y mostrarlos
        findByStatus(Status.LEYENDO);

    }

    //Cuando el botón En Curso es pulsado, cambia de color y se muestran los mangas que se encuentran pendientes por el usuario
    @FXML
    private void seleccionarPendiente(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

        //Aqui debe realizarse la consulta de mangas pendientes por el usuario y mostrarlos
        findByStatus(Status.PENDIENTE);

    }

    //Al hacer click sobre la imagen de usuario, se cambia a la ventana de cambio de contraseña (o borrado de cuenta)
    @FXML
    private void seleccionarCambiarContrasenya() throws IOException {

        Mangashelf.setRoot("CambioContrasenya");

    }

    //Se selecciona la opción Título en el filtro
    @FXML
    private void seleccionarTitulo(){

        filtroMenu.setText("Título");

    }

    //Se selecciona la opción Autor en el filtro
    @FXML
    private void seleccionarAutor(){

        filtroMenu.setText("Autor");

    }

    //Se selecciona la opción Género en el filtro
    @FXML
    private void seleccionarGenero(){

        filtroMenu.setText("Género");

    }

    //Realiza una búsqueda predeterminada por título o en base a la selección del filtro y orden
    @FXML
    private void realizarBusqueda(){

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

    @FXML
    private void crearPortada(String nombreManga){

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        findAllObras();
        nombreUsuario.setText(RegistroLoginController.actualUser.getEmailUsuario());

    }

}
