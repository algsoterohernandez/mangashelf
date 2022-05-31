package edu.fpdual.proyecto.mangashelf.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Usuarios DTO Manager.
 *
 * Contiene todas las queries definidas utilizadas para la consulta y manipulacion de datos de Usuarios.
 *
 * @author ikisaki
 *
 */
public class UsuariosManagerImpl {

    public Set<Usuarios> login(Connection con, String email, String password) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("SELECT usuarios.emailUsuarios FROM mangas.usuarios " +
                "WHERE usuarios.emailUsuarios = ? AND usuarios.contrasenyaUsuario = ?")){

            prepstm.setString(1, email);
            prepstm.setString(1, password);

            ResultSet result = prepstm.executeQuery();

            Set<Usuarios> set = new LinkedHashSet<>();
            result.beforeFirst();
            while (result.next()) {
                Usuarios usuario = new Usuarios(result);
                set.add(usuario);
            }

            return set;

        }
    }

    public int createUser(Connection con, String email, String password) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("INSERT INTO mangas.usuarios(emailUsuario, contrasenyaUsuario)" +
                "VALUES (?,?)")){
            prepstm.setString(1, email);
            prepstm.setString(2, password);

            return prepstm.executeUpdate();
        }
    }

    public int deleteUser(Connection con, String email, String password) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("DELETE FROM mangas.usuarios " +
                "WHERE usuarios.emailUsuario = ? AND usuarios.contrasenyaUsuario = ?")){
            prepstm.setString(1, email);
            prepstm.setString(2, password);

            return prepstm.executeUpdate();
        }
    }

    public int changePassword(Connection con, String email, String oldPassword, String newPassword) throws SQLException {
        try(PreparedStatement prepstm = con.prepareStatement("UPDATE mangas.usuarios " +
                "SET contrasenyaUsuario = ? WHERE emailUsuario = ? AND contrasenyaUsuario = ?")){
            prepstm.setString(1, newPassword);
            prepstm.setString(2, email);
            prepstm.setString(3, oldPassword);

            return prepstm.executeUpdate();
        }
    }
}
