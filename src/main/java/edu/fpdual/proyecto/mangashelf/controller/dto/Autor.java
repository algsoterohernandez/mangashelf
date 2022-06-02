package edu.fpdual.proyecto.mangashelf.controller.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class Autor {

    private String titulo;
    private String nombre;

    public Autor(ResultSet result) throws SQLException {
        setTitulo(result.getString("Titulo"));
        setNombre(result.getString("Nombre"));
    }

    public Autor(String titulo, String nombre) {
        this.titulo = titulo;
        this.nombre = nombre;
    }

    public Autor() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return titulo.equals(autor.titulo) && nombre.equals(autor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, nombre);
    }
}