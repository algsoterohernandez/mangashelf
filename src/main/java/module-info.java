module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires java.mail;
    requires activation;
    requires javafx.fxml;

    opens edu.fpdual.proyecto.mangashelf.controller to javafx.fxml;

    exports edu.fpdual.proyecto.mangashelf.controller;
    exports edu.fpdual.proyecto.mangashelf;
    opens edu.fpdual.proyecto.mangashelf to javafx.fxml;

}