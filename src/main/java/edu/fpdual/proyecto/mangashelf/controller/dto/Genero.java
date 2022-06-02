package edu.fpdual.proyecto.mangashelf.controller.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class Genero {

    private String genero;
    private String titulo;

    public Genero(ResultSet result) throws SQLException {
        setGenero(result.getString("Genero"));
        setTitulo(result.getString("Titulo"));
    }

    public Genero(String genero, String titulo) {
        this.genero = genero;
        this.titulo = titulo;
    }

    public Genero() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero1 = (Genero) o;
        return genero.equals(genero1.genero) && titulo.equals(genero1.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genero, titulo);
    }
}