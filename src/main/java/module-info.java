module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires activation;

    opens edu.fpdual.proyecto.mangashelf.javafx to javafx.fxml;

}