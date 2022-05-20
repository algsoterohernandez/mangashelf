package edu.fpdual.proyecto.mangashelf.controller;

import edu.fpdual.proyecto.mangashelf.model.connector.MySQLConnector;
import edu.fpdual.proyecto.mangashelf.model.dao.Autor;
import edu.fpdual.proyecto.mangashelf.model.dao.Genero;
import edu.fpdual.proyecto.mangashelf.model.dao.Obra;
import edu.fpdual.proyecto.mangashelf.model.dao.Puntuacion;
import edu.fpdual.proyecto.mangashelf.model.manager.impl.AutorManagerImpl;
import edu.fpdual.proyecto.mangashelf.model.manager.impl.GeneroManagerImpl;
import edu.fpdual.proyecto.mangashelf.model.manager.impl.ObraManagerImpl;
import edu.fpdual.proyecto.mangashelf.model.manager.impl.PuntuacionManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try (Connection con = new MySQLConnector().getMySQLConnection()){

            List<Autor> autores = new AutorManagerImpl().findAll(con);

            for (Autor autor : autores) {

                System.out.println(autor.getNombre()+" | "+autor.getTitulo());

            }

            System.out.println("");
            System.out.println("");

            List<Genero> generos = new GeneroManagerImpl().findAll(con);

            for (Genero genero : generos) {

                System.out.println(genero.getGenero()+" | "+genero.getTitulo());

            }

            System.out.println("");
            System.out.println("");

            List<Obra> obras = new ObraManagerImpl().findAll(con);

            for (Obra obra : obras) {

                System.out.println(obra.getTitulo()+" | "+obra.getAnyoPublicacion()+" | "+obra.getAnyoTermino()+" | "+obra.getCapitulosLeidos()+" | "+obra.getCapitulosTotales()+" | "+obra.getEstado());

            }

            System.out.println("");
            System.out.println("");

            List<Puntuacion> puntuaciones = new PuntuacionManagerImpl().findAll(con);

            for (Puntuacion puntuacion : puntuaciones) {

                System.out.println(puntuacion.getPuntuacion()+" | "+puntuacion.getTitulo());

            }

            System.out.println("");
            System.out.println("");

        }
    }

}
