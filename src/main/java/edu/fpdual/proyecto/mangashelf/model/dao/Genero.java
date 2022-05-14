package edu.fpdual.proyecto.mangashelf.model.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Genero {

    private String genero;
    private String titulo;

    public Genero(ResultSet result) throws SQLException {
        setGenero(result.getString("Genero"));
        setTitulo(result.getString("Titulo"));
    }


}