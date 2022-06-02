module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires java.mail;
    requires activation;
    requires javafx.fxml;

    opens edu.fpdual.proyecto.mangashelf.javafx to javafx.fxml;

}