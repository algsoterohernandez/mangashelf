package edu.fpdual.proyecto.mangashelf.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.Autor;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Autor DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de Autor.
 *
 * @author ikisaki
 *
 */
public class AutorManagerImpl {

    public Set<Autor> findAll(Connection con) throws SQLException {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM mangas.autor");

            return queryResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Autor> findByOrderAsc(Connection con)throws SQLException {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM mangas.autor ORDER BY autor.nombre ASC");

            return (LinkedHashSet<Autor>) queryResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Autor> findByOrderDesc(Connection con) throws SQLException {
        // Crea el statement general.
        try (Statement stmt = con.createStatement()) {
            // Realiza la consulta de la BBDD.
            ResultSet result = stmt.executeQuery("SELECT * FROM mangas.autor ORDER BY autor.nombre DESC");

            return (LinkedHashSet<Autor>) queryResult(result);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Autor> findByName(Connection con, String name) throws SQLException  {
        try(PreparedStatement prepstm = con.prepareStatement("SELECT * FROM mangas.autor " +
                "WHERE autor.nombre LIKE ?")){

            prepstm.setString(1, "%" + name + "%");

            ResultSet result = prepstm.executeQuery();
            return queryResult(result);
        }
    }

    public Set<Autor> queryResult(ResultSet result) throws SQLException {
        Set<Autor> set = new LinkedHashSet<>();
        result.beforeFirst();
        while (result.next()) {
            Autor autor = new Autor(result);
            set.add(autor);
        }

        return set;
    }
}
