module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.ws.rs;

    opens edu.fpdual.proyecto.mangashelf.controller to javafx.fxml;

    exports edu.fpdual.proyecto.mangashelf.controller;
    exports edu.fpdual.proyecto.mangashelf;
    opens edu.fpdual.proyecto.mangashelf to javafx.fxml;

}