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


}
