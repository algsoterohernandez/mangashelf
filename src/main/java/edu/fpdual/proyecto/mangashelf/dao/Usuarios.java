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
public class Usuarios {

    private String emailUsuario;
    private String contrasenyaUsuario;

    public Usuarios(ResultSet result) throws SQLException {
        setEmailUsuario(result.getString("EmailUsuario"));
        setContrasenyaUsuario(result.getString("ContrasenyaUsuario"));
    }


}