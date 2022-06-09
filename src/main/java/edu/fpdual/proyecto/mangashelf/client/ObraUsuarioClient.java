package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Autor;
import edu.fpdual.proyecto.mangashelf.controller.dto.ObraUsuario;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;


public class ObraUsuarioClient {

    private WebTarget webTarget;

    /**
     * A través de webTarget conectamos con el servidor y así poder crear o validar
     * en el caso que corresponda la cuenta del usuario.
     */
    public ObraUsuarioClient() {
        ClientConfig config = new ClientConfig();
        config.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newClient(config);
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/obrausuario");
    }

    /**
     * Comprueba que los datos que introduce el usuario se encuentran en la BBDD.
     * Según el código recibido comprobaremos si existe y es coincidente
     * o si por el contrario algún dato es erróneo
     */
    public ObraUsuario findByID(String email, String obra) throws ExcepcionHTTP {
        Response response = webTarget.path("/get/" + email + "/" + obra)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {

            return response.readEntity(ObraUsuario.class);
        } else if (response.getStatus() == 404){

            return null;

        }else{
            throw new ExcepcionHTTP("Error");
        }
    }

    public ObraUsuario addObra (ObraUsuario obus) throws ExcepcionHTTP{
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(obus, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(ObraUsuario.class);
        } else{
            throw new ExcepcionHTTP("Error");
        }
    }
    public ObraUsuario updateStatus (ObraUsuario obus) throws ExcepcionHTTP{
        Response response = webTarget.path("/update")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(obus, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(ObraUsuario.class);
        } else{
            throw new ExcepcionHTTP("Error");
        }
    }
    public ObraUsuario sumChap (ObraUsuario obus) throws ExcepcionHTTP{
        Response response = webTarget.path("/sum")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(obus, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(ObraUsuario.class);
        } else{
            throw new ExcepcionHTTP("Error");
        }
    }
    public ObraUsuario resChap (ObraUsuario obus) throws ExcepcionHTTP{
        Response response = webTarget.path("/res")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(obus, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            return response.readEntity(ObraUsuario.class);
        } else{
            throw new ExcepcionHTTP("Error");
        }
    }
    public void deleteObra (String email, String obra) throws ExcepcionHTTP{
        Response response = webTarget.path("/" + email + "/" + obra)
                .request()
                .delete();

        if (response.getStatus() != 200) {
            throw new ExcepcionHTTP("Error al eleminar");
        }
    }
}