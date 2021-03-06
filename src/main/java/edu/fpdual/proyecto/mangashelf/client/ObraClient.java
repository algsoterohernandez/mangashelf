package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Obra;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

/**
 * ObraClient.
 *
 * Contiene todas las peticiones al servicio web relacionadas con Obra.
 *
 * @author ikisaki
 *
 */
public class ObraClient {

    private WebTarget webTarget;

    public ObraClient() {

        ClientConfig config = new ClientConfig();
        config.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newClient(config);
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/obra");

    }

    /**
     * findAll.
     *
     * Comprueba que los datos que introduce el usuario se encuentran en la BBDD.
     * Según el código recibido comprobaremos si existe y es coincidente
     * o si por el contrario algún dato es erróneo.
     *
     * @author ikisaki
     *
     */
    public Obra[] findAll() throws ExcepcionHTTP {

        Response response = webTarget.path("/get")
            .request(MediaType.APPLICATION_JSON)
            .get();

        if (response.getStatus() == 200) {

            return response.readEntity(Obra[].class);

        } else {

            throw new ExcepcionHTTP("Error");

        }

    }

    /**
     * findByName.
     *
     * Devuelve todas las obras cuyo nombre contenga el parámetro pasado.
     *
     * @author ikisaki
     *
     */
    public Obra[] findByName(String nombre) throws ExcepcionHTTP {

        Response response = webTarget.path("/get/" + nombre)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {

            return response.readEntity(Obra[].class);

        } else {

            throw new ExcepcionHTTP(String.valueOf(response.getStatus()));

        }

    }

    /**
     * findByID.
     *
     * Devuelve la obra que posea el id pasado como parámetro.
     *
     * @author ikisaki
     *
     */
    public Obra findByID (String id) throws ExcepcionHTTP {

        Response response = webTarget.path("/getid/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {

            return response.readEntity(Obra.class);

        } else if (response.getStatus() == 404) {

            throw new ExcepcionHTTP("Not found");

        } else {

            throw new ExcepcionHTTP("Error");

        }

    }

}