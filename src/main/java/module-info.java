module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires jakarta.inject;
    requires jakarta.annotation;

    opens edu.fpdual.proyecto.mangashelf.controller to javafx.fxml;

    exports edu.fpdual.proyecto.mangashelf.controller;
    exports edu.fpdual.proyecto.mangashelf;
    exports edu.fpdual.proyecto.mangashelf.controller.dto;
    opens edu.fpdual.proyecto.mangashelf to javafx.fxml;

}