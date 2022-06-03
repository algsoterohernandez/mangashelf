package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Autor;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashSet;

public class UsuariosClient {

    private WebTarget webTarget;

    public UsuariosClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/");
    }

    public LinkedHashSet<Autor> findAll() {

        LinkedHashSet<Autor> autores = webTarget.path("autor/get/desc").request(MediaType.APPLICATION_JSON).get(LinkedHashSet.class);

        return autores;
    }

}
