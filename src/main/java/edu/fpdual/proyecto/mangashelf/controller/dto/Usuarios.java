package edu.fpdual.proyecto.mangashelf.controller.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Usuarios {

    private String emailUsuario;
    private String contrasenyaUsuario;

    public Usuarios(ResultSet result) throws SQLException {
        setEmailUsuario(result.getString("EmailUsuario"));
        setContrasenyaUsuario(result.getString("ContrasenyaUsuario"));
    }

    public Usuarios(String emailUsuario, String contrasenyaUsuario) {
        this.emailUsuario = emailUsuario;
        this.contrasenyaUsuario = contrasenyaUsuario;
    }

    public Usuarios() {
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenyaUsuario() {
        return contrasenyaUsuario;
    }

    public void setContrasenyaUsuario(String contrasenyaUsuario) {
        this.contrasenyaUsuario = contrasenyaUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return emailUsuario.equals(usuarios.emailUsuario) && contrasenyaUsuario.equals(usuarios.contrasenyaUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailUsuario, contrasenyaUsuario);
    }
}