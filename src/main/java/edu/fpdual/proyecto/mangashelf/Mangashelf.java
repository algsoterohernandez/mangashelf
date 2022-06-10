package edu.fpdual.proyecto.mangashelf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Mangashelf.
 *
 * Aplicación JavaFX.
 *
 * @author ikisaki
 *
 */
public class Mangashelf extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("RegistroLogin"));

        stage.setScene(scene);
        stage.getIcons().add(new Image("edu/fpdual/proyecto/mangashelf/static.img/logos/logo_mangashelf_transparente.png"));
        stage.setTitle("Mangashelf");

        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));

    }

    public static Parent loadFXML(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Mangashelf.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();

    }

    public static void main(String[] args) {
        launch();
    }

}
