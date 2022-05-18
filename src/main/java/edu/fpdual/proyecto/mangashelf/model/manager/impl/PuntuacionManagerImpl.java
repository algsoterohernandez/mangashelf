package edu.fpdual.proyecto.mangashelf.model.manager.impl;

import edu.fpdual.proyecto.mangashelf.model.dao.Puntuacion;
import edu.fpdual.proyecto.mangashelf.model.manager.PuntuacionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor DTO Manager.
 *
 * Contiene todas las queries utilizadas para la consulta y manipulacion de datos de Puntuacion.
 *
 * @author ikisaki
 *
 */
public class PuntuacionManagerImpl implements PuntuacionManager {

    @Override
    public List<Puntuacion> findAll(Connection con) {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM Puntuacion");
            // Set antes del primer registro.
            result.beforeFirst();

            // Inicializar variable.
            List<Puntuacion> puntuaciones = new ArrayList<>();

            // Recorre cada resultado.
            while (result.next()) {
                // Añade una puntuacion por resultado.
                puntuaciones.add(new Puntuacion(result));

            }

            return puntuaciones;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
