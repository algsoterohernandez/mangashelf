package edu.fpdual.proyecto.mangashelf.JavaFX.controller;


import java.io.IOException;

import edu.fpdual.proyecto.mangashelf.JavaFX.service.App;
import javafx.fxml.FXML;

public class RegisterController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}