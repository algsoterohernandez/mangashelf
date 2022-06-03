package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

public class UsuariosClient {

    private WebTarget webTarget;

    public UsuariosClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/");
    }

    public void createUser(String email, String password) {
        Response response = webTarget.path("usuarios/create/" + email + "/" + password)
                .request()
                .post(null);
    }

}
