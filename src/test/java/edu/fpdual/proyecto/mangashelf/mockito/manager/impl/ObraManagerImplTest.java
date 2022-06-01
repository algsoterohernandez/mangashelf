package edu.fpdual.proyecto.mangashelf.mockito.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.Obra;
import edu.fpdual.proyecto.mangashelf.manager.impl.ObraManagerImpl;
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
class ObraManagerImplTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private ObraManagerImpl obraManager;

    @Test
    void findAll_ok() throws SQLException {

        Obra expectedObra = Obra.builder()
                .titulo("Assassination Classroom")
                .anyoPublicacion(2012)
                .anyoTermino("2016")
                .capitulosTotales("187")
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
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

        Set<Obra> obraSet = obraManager.findAll(connection);

        MatcherAssert.assertThat(obraSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(obraSet.iterator().next(), Matchers.is(expectedObra));

    }

    @Test
    void findAll_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        Set<Obra> obraSet = obraManager.findAll(connection);

        MatcherAssert.assertThat(obraSet, Matchers.nullValue());

    }

    @Test
    void findByOrderAsc_ok() throws SQLException {

        Obra expectedObra = Obra.builder()
                .titulo("Assassination Classroom")
                .anyoPublicacion(2012)
                .anyoTermino("2016")
                .capitulosTotales("187")
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
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

        Set<Obra> obraSet = obraManager.findByOrderAsc(connection);

        MatcherAssert.assertThat(obraSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(obraSet.iterator().next(), Matchers.is(expectedObra));

    }

    @Test
    void findByOrderAsc_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        Set<Obra> obraSet = obraManager.findByOrderAsc(connection);

        MatcherAssert.assertThat(obraSet, Matchers.nullValue());

    }

    @Test
    void findByOrderDesc_ok() throws SQLException {

        Obra expectedObra = Obra.builder()
                .titulo("Assassination Classroom")
                .anyoPublicacion(2012)
                .anyoTermino("2016")
                .capitulosTotales("187")
                .build();

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
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

        Set<Obra> obraSet = obraManager.findByOrderDesc(connection);

        MatcherAssert.assertThat(obraSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(obraSet.iterator().next(), Matchers.is(expectedObra));

    }

    @Test
    void findByOrderDesc_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        Set<Obra> obraSet = obraManager.findByOrderDesc(connection);

        MatcherAssert.assertThat(obraSet, Matchers.nullValue());

    }

    @Test
    void findByName_ok() throws SQLException {

        Obra expectedObra = Obra.builder()
                .titulo("Assassination Classroom")
                .anyoPublicacion(2012)
                .anyoTermino("2016")
                .capitulosTotales("187")
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

        Set<Obra> obraSet = obraManager.findByName(connection,"");

        MatcherAssert.assertThat(obraSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(obraSet.iterator().next(), Matchers.is(expectedObra));

    }

}