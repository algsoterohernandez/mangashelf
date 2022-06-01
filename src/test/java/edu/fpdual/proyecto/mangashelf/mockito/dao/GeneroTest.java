package edu.fpdual.proyecto.mangashelf.mockito.dao;

import edu.fpdual.proyecto.mangashelf.dao.Genero;
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
public class GeneroTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void generoConstruction_ok() throws SQLException {

        Genero expectedGenero = Genero.builder()
                .genero("Accion")
                .titulo("Assassination Classroom")
                .build();

        doReturn(expectedGenero.getGenero()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("Genero")){
                    return expectedGenero.getGenero();
                } else if (invocationOnMock.getArgument(0).equals("Titulo")) {
                    return expectedGenero.getTitulo();
                } else {
                    return null;
                }
            }
        });

        Genero actualGenero = new Genero(resultSet);

        MatcherAssert.assertThat(actualGenero, Matchers.is(expectedGenero));

    }

}
