package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

public class UsuariosClient{

    private WebTarget webTarget;

    public UsuariosClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/");
    }

    public Usuarios loginUser(Usuarios user) throws ExcepcionHTTP {
        Response response = webTarget.path("patch")
                .request()
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                .build("PATCH", Entity.entity(user,MediaType.APPLICATION_JSON))
                .invoke();

        if (response.getStatus() == 200){
            return response.readEntity(Usuarios.class);
        }else{
            throw new ExcepcionHTTP("EMAIL o CONTRASEÑA erronea");
        }


    }

    public Usuarios createUser(Usuarios newUser) throws ExcepcionHTTP {

        Response response = webTarget.path("usuarios")
                .request()
                .post(Entity.entity(newUser, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200){
            return response.readEntity(Usuarios.class);
        }else{
            throw new ExcepcionHTTP("El usuario ya existe");
        }


    }
}
