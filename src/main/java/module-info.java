module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires activation;

    opens edu.fpdual.proyecto.mangashelf.controller to javafx.fxml;

    exports edu.fpdual.proyecto.mangashelf.controller;
    exports edu.fpdual.proyecto.mangashelf;
    opens edu.fpdual.proyecto.mangashelf to javafx.fxml;

}