package edu.fpdual.proyecto.mangashelf.controller.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Obra {

    private String id;
    private String titulo;
    private int anyoPublicacion;
    private String anyoTermino;
    private String capitulosTotales;

    public Obra(ResultSet result) throws SQLException {
        setId(result.getString("Id"));
        setTitulo(result.getString("Titulo"));
        setAnyoPublicacion(result.getInt("AnyoPublicacion"));
        setAnyoTermino(result.getString("AnyoTermino"));
        setCapitulosTotales(result.getString("CapitulosTotales"));
    }

    public Obra(String titulo, int anyoPublicacion, String anyoTermino, String capitulosTotales) {
        this.id = id;
        this.titulo = titulo;
        this.anyoPublicacion = anyoPublicacion;
        this.anyoTermino = anyoTermino;
        this.capitulosTotales = capitulosTotales;
    }

    public Obra() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnyoPublicacion() {
        return anyoPublicacion;
    }

    public void setAnyoPublicacion(int anyoPublicacion) {
        this.anyoPublicacion = anyoPublicacion;
    }

    public String getAnyoTermino() {
        return anyoTermino;
    }

    public void setAnyoTermino(String anyoTermino) {
        this.anyoTermino = anyoTermino;
    }

    public String getCapitulosTotales() {
        return capitulosTotales;
    }

    public void setCapitulosTotales(String capitulosTotales) {
        this.capitulosTotales = capitulosTotales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obra obra = (Obra) o;
        return anyoPublicacion == obra.anyoPublicacion && titulo.equals(obra.titulo) && anyoTermino.equals(obra.anyoTermino) && capitulosTotales.equals(obra.capitulosTotales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, anyoPublicacion, anyoTermino, capitulosTotales);
    }
}