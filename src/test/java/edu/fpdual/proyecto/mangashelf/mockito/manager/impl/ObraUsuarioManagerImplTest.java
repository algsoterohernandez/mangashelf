package edu.fpdual.proyecto.mangashelf.mockito.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.ObraUsuario;
import edu.fpdual.proyecto.mangashelf.manager.impl.ObraUsuarioManagerImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObraUsuarioManagerImplTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ObraUsuarioManagerImpl obraUsuarioManager;

    @Test
    void findByUser_ok() throws SQLException {

        ObraUsuario expectedObraUsuario = ObraUsuario.builder()
                .usuario("raimundatorcuatadeloscarpatos@gmail.com")
                .obra("Demon Slayer")
                .capitulosLeidos(12)
                .estado("LEYENDO")
                .build();

        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenAnswer(new Answer<Boolean>() {

            private int counter = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(counter < 1){
                    counter++;
                    return true;
                } else {
                    return false;
                }
            }
        });
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

        Set<ObraUsuario> obraUsuarioSet = obraUsuarioManager.findByUser(connection,"");

        MatcherAssert.assertThat(obraUsuarioSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(obraUsuarioSet.iterator().next(), Matchers.is(expectedObraUsuario));

    }

}
