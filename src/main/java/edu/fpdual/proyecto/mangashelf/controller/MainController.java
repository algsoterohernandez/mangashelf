package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.Mangashelf;
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
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label nombreUsuario;

    @FXML
    private TextField buscador;

    @FXML
    private TilePane portadasMangas;

    @FXML
    private MenuButton filtroMenu;

    @FXML
    private MenuButton ordenMenu;

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

    //Cuando se haga click en Mangashelf, o en su logo, se reiniciará la página
    @FXML
    private void volverIndice(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));
        filtroMenu.setText("Filtro");
        ordenMenu.setText("Orden");
        buscador.setText("");

        //Aqui se debe realizar la consulta para que muestre todos los mangas

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

        //Aqui debe realizarse la consulta de mangas leidos por el usuario y mostrarlos

    }

    //Cuando el botón En Curso es pulsado, cambia de color y se muestran los mangas que se encuentran en curso por el usuario
    @FXML
    private void seleccionarEnCurso(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.LIGHTGRAY));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));

        //Aqui debe realizarse la consulta de mangas en curso por el usuario y mostrarlos

    }

    //Cuando el botón En Curso es pulsado, cambia de color y se muestran los mangas que se encuentran pendientes por el usuario
    @FXML
    private void seleccionarPendiente(){

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.LIGHTGRAY));

        //Aqui debe realizarse la consulta de mangas pendientes por el usuario y mostrarlos

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

    //Se selecciona la opción Ascendente en el orden
    @FXML
    private void seleccionarAscendente(){

        ordenMenu.setText("Ascendente");

    }

    //Se selecciona la opción Descendente en el orden
    @FXML
    private void seleccionarDescendente(){

        ordenMenu.setText("Descendente");

    }

    //Realiza una búsqueda predeterminada por título o en base a la selección del filtro y orden
    @FXML
    private void realizarBusqueda(){

        String busqueda = buscador.getText();

        leidosBoton.setBackground(Background.fill(Color.WHITE));
        enCursoBoton.setBackground(Background.fill(Color.WHITE));
        pendienteBoton.setBackground(Background.fill(Color.WHITE));

        if (filtroMenu.getText().equals("Título")) {

            if (ordenMenu.getText().equals("Ascendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de titulos ascendentes y mostrar los mangas

            } else if (ordenMenu.getText().equals("Descendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de titulos descendentes y mostrar los mangas

            } else {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de titulos sin orden y mostrar los mangas

            }

        } else if (filtroMenu.getText().equals("Autor")) {

            if (ordenMenu.getText().equals("Ascendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de autor ascendente y mostrar los mangas

            } else if (ordenMenu.getText().equals("Descendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de autor descendente y mostrar los mangas

            } else {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de autor sin orden y mostrar los mangas

            }

        } else if (filtroMenu.getText().equals("Género")) {

            if (ordenMenu.getText().equals("Ascendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de genero ascendente y mostrar los mangas

            } else if (ordenMenu.getText().equals("Descendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de genero descendente y mostrar los mangas

            } else {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de genero sin orden y mostrar los mangas

            }

        } else {

            if (ordenMenu.getText().equals("Ascendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de titulo ascendente y mostrar los mangas

            } else if (ordenMenu.getText().equals("Descendente")) {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                //Aqui se debe realizar la busqueda de titulo descendente y mostrar los mangas

            } else {

                portadasMangas.getChildren().removeAll(portadasMangas.getChildren());

                nombreUsuario.setText(RegistroLoginController.actualUser.getEmailUsuario());

                //Aqui se debe realizar la busqueda de titulo sin orden y mostrar los mangas

            }

        }

    }

    @FXML
    private void crearPortada(String nombreManga){

        Image portada = new Image("edu/fpdual/proyecto/mangashelf/static.img/portadas/"+nombreManga+".png");
        ImageView imagenView = new ImageView(portada);
        imagenView.setOnMouseClicked(mouseEvent -> {
            try {
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

        nombreUsuario.setText(RegistroLoginController.actualUser.getEmailUsuario());

    }

}
