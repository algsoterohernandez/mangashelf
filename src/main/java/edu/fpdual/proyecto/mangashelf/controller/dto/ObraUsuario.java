package edu.fpdual.proyecto.mangashelf.controller.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class ObraUsuario {

    private String usuario;
    private String obra;
    private int capitulosLeidos;
    private String estado;

    public ObraUsuario(ResultSet result) throws SQLException {
        setUsuario(result.getString("Usuario"));
        setObra(result.getString("Obra"));
        setCapitulosLeidos(result.getInt("CapitulosLeidos"));
        setEstado(result.getString("Estado"));
    }

    public ObraUsuario(String usuario, String obra, int capitulosLeidos, String estado) {
        this.usuario = usuario;
        this.obra = obra;
        this.capitulosLeidos = capitulosLeidos;
        this.estado = estado;

    }

    public ObraUsuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public int getCapitulosLeidos() {
        return capitulosLeidos;
    }

    public void setCapitulosLeidos(int capitulosLeidos) {
        this.capitulosLeidos = capitulosLeidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObraUsuario that = (ObraUsuario) o;
        return capitulosLeidos == that.capitulosLeidos && usuario.equals(that.usuario) && obra.equals(that.obra) && estado.equals(that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, obra, capitulosLeidos, estado);
    }
}
