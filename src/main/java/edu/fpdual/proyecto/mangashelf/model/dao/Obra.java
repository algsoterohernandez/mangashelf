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
public class Obra {

    private String titulo;
    private int anyoPublicacion;
    //anyoTermino values: int, "En publicacion"
    private String anyoTermino;
    private int capitulosLeidos;
    //capitulosTotales values: int, "En publicacion"
    private String capitulosTotales;
    //estado values: 0(Leer), 1(Leyendo), 2(Leido)
    private int estado;

    public Obra(ResultSet result) throws SQLException {
        setTitulo(result.getString("Titulo"));
        setAnyoPublicacion(result.getInt("AnyoPublicacion"));
        setAnyoTermino(result.getString("AnyoTermino"));
        setCapitulosLeidos(result.getInt("CapitulosLeidos"));
        setCapitulosTotales(result.getString("CapitulosTotales"));
        setEstado(result.getInt("Estado"));
    }


}