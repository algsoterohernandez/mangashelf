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
public class Puntuacion {

    private String titulo;
    private int puntuacion;

    public Puntuacion(ResultSet result) throws SQLException {
        setTitulo(result.getString("Titulo"));
        setPuntuacion(result.getInt("Puntuacion"));
    }


}