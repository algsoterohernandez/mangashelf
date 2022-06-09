package edu.fpdual.proyecto.mangashelf.exceptions;

/**
 * ExcepcionHTTP.
 *
 * Excepción personalizada para acoger las excepciones referentes a las provocadas por el servicio web.
 *
 * @author ikisaki
 *
 */
public class ExcepcionHTTP extends Exception {

    public ExcepcionHTTP(String error){
        super(error);
    }

}
