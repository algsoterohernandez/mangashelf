package edu.fpdual.proyecto.mangashelf.model.manager;

import java.sql.Connection;
import java.util.List;

public interface Manager<T> {
    /**
     * Encuentra todas las entidades en la BBDD
     *
     * @param con DB connection
     * @return a {@link List} of {@link T}
     */
    List<T> findAll(Connection con);

}
