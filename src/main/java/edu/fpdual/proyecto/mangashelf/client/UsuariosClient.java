package edu.fpdual.proyecto.mangashelf.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class UsuariosClient {

    private WebTarget webTarget;

    public UsuariosClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/");
    }

    public void createUser(String email, String password) {

        webTarget.path("usuarios/create/"+email+"/"+password).request(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN_TYPE).post(null);
    }

}
