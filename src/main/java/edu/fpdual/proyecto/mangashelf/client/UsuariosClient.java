package edu.fpdual.proyecto.mangashelf.client;

import edu.fpdual.proyecto.mangashelf.controller.dto.Usuarios;
import edu.fpdual.proyecto.mangashelf.exceptions.ExcepcionHTTP;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

/**
 * UsuariosClient.
 *
 * Contiene todas las peticiones al servicio web relacionadas con Usuarios.
 *
 * @author ikisaki
 *
 */
public class UsuariosClient{

    private WebTarget webTarget;

    public UsuariosClient() {

        ClientConfig config = new ClientConfig();
        config.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newClient(config);
        this.webTarget = client.target("http://localhost:8080/MangaShelfWebService/api/usuarios");

    }

    /**
     * loginUser.
     *
     * Comprueba que los datos que introduce el usuario se encuentran en la BBDD.
     * Según el código recibido comprobaremos si existe y es coincidente
     * o si por el contrario algún dato es erróneo.
     *
     * @author ikisaki
     *
     * */
    public Usuarios loginUser(Usuarios user) throws ExcepcionHTTP {

        Response response = webTarget.request()
                .build("PATCH", Entity.entity(user,MediaType.APPLICATION_JSON))
                .invoke();

        if (response.getStatus() == 200) {

            return response.readEntity(Usuarios.class);

        } else if (response.getStatus() == 404) {

            throw new ExcepcionHTTP("EMAIL o CONTRASEÑA erróneos");

        } else

            throw new ExcepcionHTTP("Bad request");

    }

    /**
     * createUser.
     *
     * Comprueba que los datos que introduce el usuario no se encuentran en la BBDD.
     * Si existe en la BBDD, no registra nada y saltará la excepción.
     *
     * @author ikisaki
     *
     * */
    public Usuarios createUser(Usuarios newUser) throws ExcepcionHTTP {

        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newUser, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {

            return response.readEntity(Usuarios.class);

        } else if (response.getStatus() == 500) {

            throw new ExcepcionHTTP("El usuario ya existe");

        } else {

            throw new ExcepcionHTTP("Bad request");

        }

    }

    /**
     * deleteUser.
     *
     * Elimina de la base de ddatos todos los usuarios cuyo email coincida con el pasado por parámetro.
     *
     * @author ikisaki
     *
     */
    public void deleteUser(String email) throws ExcepcionHTTP {

        Response response = webTarget.path(email)
                .request()
                .delete();

        if (response.getStatus() != 200) {

            throw new ExcepcionHTTP("Error al borrar");

        }

    }

    /**
     * changePwd.
     *
     * Modifica la contraseña del usuario pasado como parámetro.
     *
     * @author ikisaki
     *
     */
    public void changePwd(Usuarios user) throws ExcepcionHTTP {

        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(user, MediaType.APPLICATION_JSON));
        
        if (response.getStatus() != 200) {

            throw new ExcepcionHTTP("Error al cambiar la contraseña");

        }

    }

}
