package edu.fpdual.proyecto.mangashelf.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.ObraUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ObraUsuario DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de ObraUsuario.
 *
 * @author ikisaki
 *
 */
public class ObraUsuarioManagerImpl {

    public Set<ObraUsuario> findByUser(Connection con, String email) throws SQLException  {
        try(PreparedStatement prepstm = con.prepareStatement("SELECT * FROM mangas.obra_usuario " +
                "WHERE obra_usuario.usuario LIKE ?")){

            prepstm.setString(1, "%" + email + "%");

            ResultSet result = prepstm.executeQuery();
            return queryResult(result);
        }
    }

    public int createObraUsuario(Connection con, String email, String obraLeyendo) throws SQLException {

        String estado = "LEYENDO";

        try(PreparedStatement prepstm = con.prepareStatement("INSERT INTO mangas.obra_usuario(usuario, obra, capitulosLeidos, estado)" +
                "VALUES (?,?,1,?)")){
            prepstm.setString(1, email);
            prepstm.setString(2, obraLeyendo);
            prepstm.setString(3, estado);

            return prepstm.executeUpdate();
        }
    }
    public int deleteObraUsuario(Connection con, String email) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("DELETE FROM mangas.obra_usuario " +
                "WHERE obra_usuario.usuario = ?")){
            prepstm.setString(1, email);

            return prepstm.executeUpdate();
        }
    }

    public int sumarCapitulo(Connection con, String email, String obraLeyendo) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("UPDATE mangas.obra_usuario " +
                "SET capitulosLeidos = capitulosLeidos + 1 WHERE usuario = ? AND obra = ?")){
            prepstm.setString(1, email);
            prepstm.setString(2, obraLeyendo);

            return prepstm.executeUpdate();
        }
    }

    public Set<ObraUsuario> queryResult(ResultSet result) throws SQLException {
        Set<ObraUsuario> set = new LinkedHashSet<>();
        result.beforeFirst();
        while (result.next()) {
            ObraUsuario obraUsuario = new ObraUsuario(result);
            set.add(obraUsuario);
        }

        return set;
    }

}

