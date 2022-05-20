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
public class Autor {

    private String titulo;
    private String nombre;

    public Autor(ResultSet result) throws SQLException {
        setTitulo(result.getString("Titulo"));
        setNombre(result.getString("Nombre"));
    }


}