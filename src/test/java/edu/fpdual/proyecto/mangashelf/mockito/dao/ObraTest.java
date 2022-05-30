package edu.fpdual.proyecto.mangashelf.mockito.dao;

import edu.fpdual.proyecto.mangashelf.dao.Obra;
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
public class ObraTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void obraConstruction_ok() throws SQLException {

        Obra expectedObra = Obra.builder()
                .titulo("Assassination Classroom")
                .anyoPublicacion(2012)
                .anyoTermino("2016")
                .capitulosTotales("187")
                .build();

        doReturn(expectedObra.getTitulo()).when(resultSet).getString(any());
        when(resultSet.getInt(any())).thenAnswer(new Answer<Integer>() {

            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("AnyoPublicacion")){
                    return expectedObra.getAnyoPublicacion();
                } else {
                    return null;
                }
            }
        });

        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("Titulo")){
                    return expectedObra.getTitulo();
                } else if (invocationOnMock.getArgument(0).equals("AnyoTermino")) {
                    return expectedObra.getAnyoTermino();
                } else if (invocationOnMock.getArgument(0).equals("CapitulosTotales")) {
                    return expectedObra.getCapitulosTotales();
                } else {
                    return null;
                }
            }
        });

        Obra actualObra = new Obra(resultSet);

        MatcherAssert.assertThat(actualObra, Matchers.is(expectedObra));

    }

}
