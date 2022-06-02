package edu.fpdual.proyecto.mangashelf.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.Genero;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Genero DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de Genero.
 *
 * @author ikisaki
 *
 */
public class GeneroManagerImpl {

    public Set<Genero> findAll(Connection con) throws SQLException {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM mangas.genero");

            return queryResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Genero> findByOrderAsc(Connection con)throws SQLException {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM mangas.genero ORDER BY genero.genero ASC");

            return queryResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Genero> findByOrderDesc(Connection con) throws SQLException {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM mangas.genero ORDER BY genero.genero DESC");

            return queryResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Genero> findByName(Connection con, String name) throws SQLException  {
        try(PreparedStatement prepstm = con.prepareStatement("SELECT * FROM mangas.genero " +
                "WHERE genero.genero LIKE ?")){

            prepstm.setString(1, "%" + name + "%");

            ResultSet result = prepstm.executeQuery();
            return queryResult(result);
        }
    }

    public Set<Genero> queryResult(ResultSet result) throws SQLException {
        Set<Genero> set = new LinkedHashSet<>();
        result.beforeFirst();
        while (result.next()) {
            Genero genero = new Genero(result);
            set.add(genero);
        }

        return set;
    }
}
