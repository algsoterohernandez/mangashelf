package edu.fpdual.proyecto.mangashelf.mockito.dao;

import edu.fpdual.proyecto.mangashelf.dao.ObraUsuario;
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
public class ObraUsuarioTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void obraUsuarioConstruction_ok() throws SQLException {

        ObraUsuario expectedObraUsuario = ObraUsuario.builder()
                .usuario("raimundatorcuatadeloscarpatos@gmail.com")
                .obra("Demon Slayer")
                .capitulosLeidos(12)
                .estado("LEYENDO")
                .build();

        doReturn(expectedObraUsuario.getUsuario()).when(resultSet).getString(any());
        when(resultSet.getInt(any())).thenAnswer(new Answer<Integer>() {

            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("CapitulosLeidos")){
                    return expectedObraUsuario.getCapitulosLeidos();
                } else {
                    return null;
                }
            }
        });

        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("Usuario")){
                    return expectedObraUsuario.getUsuario();
                } else if (invocationOnMock.getArgument(0).equals("Obra")) {
                    return expectedObraUsuario.getObra();
                } else if (invocationOnMock.getArgument(0).equals("Estado")) {
                    return expectedObraUsuario.getEstado();
                } else {
                    return null;
                }
            }
        });

        ObraUsuario actualObraUsuario = new ObraUsuario(resultSet);

        MatcherAssert.assertThat(actualObraUsuario, Matchers.is(expectedObraUsuario));

    }

}