module mangashelf {
    requires lombok;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens JavaFX to javafx.fxml;

}