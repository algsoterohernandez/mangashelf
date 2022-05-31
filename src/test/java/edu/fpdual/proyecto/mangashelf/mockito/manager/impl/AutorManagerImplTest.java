package edu.fpdual.proyecto.mangashelf.mockito.manager.impl;

import edu.fpdual.proyecto.mangashelf.dao.Autor;
import edu.fpdual.proyecto.mangashelf.manager.impl.AutorManagerImpl;
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
class AutorManagerImplTest {

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private AutorManagerImpl autorManager;

    @Test
    void findAll_ok() throws SQLException {

        Autor expectedAutor = Autor.builder()
                .titulo("Assassination Classroom")
                .nombre("Yusei Matsui")
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

        doReturn(expectedAutor.getNombre()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if(invocationOnMock.getArgument(0).equals("Titulo")){
                    return expectedAutor.getTitulo();
                } else if(invocationOnMock.getArgument(0).equals("Nombre")) {
                    return expectedAutor.getNombre();
                } else{
                    return null;
                }
            }
        });

        Set<Autor> autorSet = autorManager.findAll(connection);

        MatcherAssert.assertThat(autorSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(autorSet.iterator().next(), Matchers.is(expectedAutor));

    }

    @Test
    void findAll_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        Set<Autor> autorSet = autorManager.findAll(connection);

        MatcherAssert.assertThat(autorSet, Matchers.nullValue());

    }

    @Test
    void findByOrderAsc_ok() throws SQLException {

        Autor expectedAutor = Autor.builder()
                .titulo("Assassination Classroom")
                .nombre("Yusei Matsui")
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

        doReturn(expectedAutor.getNombre()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if(invocationOnMock.getArgument(0).equals("Titulo")){
                    return expectedAutor.getTitulo();
                } else if(invocationOnMock.getArgument(0).equals("Nombre")) {
                    return expectedAutor.getNombre();
                } else{
                    return null;
                }
            }
        });

        Set<Autor> autorSet = autorManager.findByOrderAsc(connection);

        MatcherAssert.assertThat(autorSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(autorSet.iterator().next(), Matchers.is(expectedAutor));

    }

    @Test
    void findByOrderAsc_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        Set<Autor> autorSet = autorManager.findAll(connection);

        MatcherAssert.assertThat(autorSet, Matchers.nullValue());

    }

    @Test
    void findByOrderDesc_ok() throws SQLException {

        Autor expectedAutor = Autor.builder()
                .titulo("Assassination Classroom")
                .nombre("Yusei Matsui")
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

        doReturn(expectedAutor.getNombre()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if(invocationOnMock.getArgument(0).equals("Titulo")){
                    return expectedAutor.getTitulo();
                } else if(invocationOnMock.getArgument(0).equals("Nombre")) {
                    return expectedAutor.getNombre();
                } else{
                    return null;
                }
            }
        });

        Set<Autor> autorSet = autorManager.findByOrderDesc(connection);

        MatcherAssert.assertThat(autorSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(autorSet.iterator().next(), Matchers.is(expectedAutor));

    }

    @Test
    void findByOrderDesc_ko() throws SQLException {

        when(connection.createStatement()).thenThrow(new SQLException(""));

        Set<Autor> autorSet = autorManager.findAll(connection);

        MatcherAssert.assertThat(autorSet, Matchers.nullValue());

    }

    @Test
    void findByName_ok() throws SQLException {

        Autor expectedAutor = Autor.builder()
                .titulo("Assassination Classroom")
                .nombre("Yusei Matsui")
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
        doReturn(expectedAutor.getNombre()).when(resultSet).getString(any());
        when(resultSet.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if(invocationOnMock.getArgument(0).equals("Titulo")){
                    return expectedAutor.getTitulo();
                } else if(invocationOnMock.getArgument(0).equals("Nombre")) {
                    return expectedAutor.getNombre();
                } else{
                    return null;
                }
            }
        });

        Set<Autor> autorSet = autorManager.findByName(connection,"");

        MatcherAssert.assertThat(autorSet, Matchers.hasSize(1));
        MatcherAssert.assertThat(autorSet.iterator().next(), Matchers.is(expectedAutor));

    }

}