package edu.fpdual.proyecto.mangashelf.mockito.dao;

import edu.fpdual.proyecto.mangashelf.dao.Autor;
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
public class AutorTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void autorConstruction_ok() throws SQLException {

        Autor expectedAutor = Autor.builder()
                .titulo("Assassination Classroom")
                .nombre("Yusei Matsui")
                .build();

        doReturn(expectedAutor.getTitulo()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("Titulo")){
                    return expectedAutor.getTitulo();
                } else if (invocationOnMock.getArgument(0).equals("Nombre")) {
                    return expectedAutor.getNombre();
                } else {
                    return null;
                }
            }
        });

        Autor actualAutor = new Autor(resultSet);

        MatcherAssert.assertThat(actualAutor, Matchers.is(expectedAutor));

    }

}
