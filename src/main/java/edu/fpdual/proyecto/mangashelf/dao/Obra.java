package edu.fpdual.proyecto.mangashelf.dao;

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
public class Obra {

    private String titulo;
    private int anyoPublicacion;
    //anyoTermino values: int, "En publicacion"
    private String anyoTermino;
    //capitulosTotales values: int, "En publicacion"
    private String capitulosTotales;

    public Obra(ResultSet result) throws SQLException {
        setTitulo(result.getString("Titulo"));
        setAnyoPublicacion(result.getInt("AnyoPublicacion"));
        setAnyoTermino(result.getString("AnyoTermino"));
        setCapitulosTotales(result.getString("CapitulosTotales"));
    }


}