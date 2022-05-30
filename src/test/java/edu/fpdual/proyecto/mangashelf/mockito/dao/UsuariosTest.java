package edu.fpdual.proyecto.mangashelf.mockito.dao;

import edu.fpdual.proyecto.mangashelf.dao.Usuarios;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuariosTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void usuariosConstruction_ok() throws SQLException {

        Usuarios expectedUsuarios = Usuarios.builder()
                .emailUsuario("pipomasterclass@gmail.com")
                .contrasenyaUsuario("Wachino")
                .build();

        doReturn(expectedUsuarios.getEmailUsuario()).when(resultSet).getString(any());

        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("EmailUsuario")){
                    return expectedUsuarios.getEmailUsuario();
                } else if (invocationOnMock.getArgument(0).equals("ContrasenyaUsuario")) {
                    return expectedUsuarios.getContrasenyaUsuario();
                } else {
                    return null;
                }
            }
        });

        Usuarios actualUsuarios = new Usuarios(resultSet);

        MatcherAssert.assertThat(actualUsuarios, Matchers.is(expectedUsuarios));

    }

}