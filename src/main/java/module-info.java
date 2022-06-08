module mangashelf {
    requires static lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;
    requires jakarta.inject;
    requires jakarta.annotation;
    requires jersey.client;
    requires jersey.common;
    requires jersey.apache.connector;


    opens edu.fpdual.proyecto.mangashelf.controller to javafx.fxml;
    //opens edu.fpdual.proyecto.mangashelf.client to jersey.client;

    exports edu.fpdual.proyecto.mangashelf.controller;
    exports edu.fpdual.proyecto.mangashelf;
    exports edu.fpdual.proyecto.mangashelf.controller.dto;
    exports edu.fpdual.proyecto.mangashelf.client;

}