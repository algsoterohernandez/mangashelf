package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Autor;
import edu.fpdual.proyecto.mangashelf.controller.dto.Genero;
import edu.fpdual.proyecto.mangashelf.controller.dto.Obra;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;


public class GeneroClient {

    private WebTarget webTarget;

    /**
     * A través de webTarget conectamos con el servidor y así poder crear o validar
     * en el caso que corresponda la cuenta del usuario.
     */
    public GeneroClient() {
        ClientConfig config = new ClientConfig();
        config.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newClient(config);
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/genero");
    }

    /**
     * Comprueba que los datos que introduce el usuario se encuentran en la BBDD.
     * Según el código recibido comprobaremos si existe y es coincidente
     * o si por el contrario algún dato es erróneo
     */
    public Genero[] findByName(String nombre) throws ExcepcionHTTP {
        Response response = webTarget.path("/get/" + nombre)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {

            return response.readEntity(Genero[].class);
        } else {
            throw new ExcepcionHTTP("Error");

        }
    }

    public Genero findByID (String id) throws ExcepcionHTTP {
        Response response = webTarget.path("/getid/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(Genero.class);
        }else if (response.getStatus() == 404){
            throw new ExcepcionHTTP("Not found");
        } else {
            throw new ExcepcionHTTP("Error");

        }
    }

}