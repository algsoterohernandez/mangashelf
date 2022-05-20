package edu.fpdual.proyecto.mangashelf.model.manager.impl;

import edu.fpdual.proyecto.mangashelf.model.dao.Obra;
import edu.fpdual.proyecto.mangashelf.model.manager.ObraManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de Obra.
 *
 * @author ikisaki
 *
 */
public class ObraManagerImpl implements ObraManager {

    @Override
    public List<Obra> findAll(Connection con) {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM Obra");
            // Set antes del primer registro.
            result.beforeFirst();

            // Inicializar variable.
            List<Obra> obras = new ArrayList<>();

            // Recorre cada resultado.
            while (result.next()) {
                // Añade una obra por resultado.
                obras.add(new Obra(result));

            }

            return obras;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
