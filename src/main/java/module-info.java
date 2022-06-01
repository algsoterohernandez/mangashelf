module mangashelf {
    requires lombok;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires activation;

    opens edu.fpdual.proyecto.mangashelf.JavaFX to javafx.fxml;

}