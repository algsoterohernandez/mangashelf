package edu.fpdual.proyecto.mangashelf.model.manager.impl;

import edu.fpdual.proyecto.mangashelf.model.dao.Genero;
import edu.fpdual.proyecto.mangashelf.model.manager.GeneroManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de Genero.
 *
 * @author ikisaki
 *
 */
public class GeneroManagerImpl implements GeneroManager {

    @Override
    public List<Genero> findAll(Connection con) {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM Genero");
            // Set antes del primer registro.
            result.beforeFirst();

            // Inicializar variable.
            List<Genero> generos = new ArrayList<>();

            // Recorre cada resultado.
            while (result.next()) {
                // Añade un genero por resultado.
                generos.add(new Genero(result));

            }

            return generos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
