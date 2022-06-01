module mangashelf {
    requires lombok;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.fpdual.proyecto.mangashelf.javafx to javafx.fxml;

}