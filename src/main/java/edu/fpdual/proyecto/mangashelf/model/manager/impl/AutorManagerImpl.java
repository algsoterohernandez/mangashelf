package edu.fpdual.proyecto.mangashelf.model.manager.impl;

import edu.fpdual.proyecto.mangashelf.model.dao.Autor;
import edu.fpdual.proyecto.mangashelf.model.manager.AutorManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de Autor.
 *
 * @author ikisaki
 *
 */
public class AutorManagerImpl implements AutorManager {

    @Override
    public List<Autor> findAll(Connection con) {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM Autor");
            // Set antes del primer registro.
            result.beforeFirst();

            // Inicializar variable.
            List<Autor> autores = new ArrayList<>();

            // Recorre cada resultado.
            while (result.next()) {
                // Añade un autor por resultado.
                autores.add(new Autor(result));
            }

            return autores;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
