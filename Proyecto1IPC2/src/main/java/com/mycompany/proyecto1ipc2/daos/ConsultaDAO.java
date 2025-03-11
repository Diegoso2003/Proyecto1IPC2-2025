/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.daos;

import com.mycompany.proyecto1ipc2.Consulta;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public abstract class ConsultaDAO {

    public abstract void realizarConsulta(Consulta consulta) throws InvalidDataException;

    protected void agregarFechaConCliente(PreparedStatement statement, Consulta consulta) throws SQLException {
        if (consulta.tieneFechaInicio()) {
            statement.setDate(2, Date.valueOf(consulta.getFechaInicio()));
            if (consulta.tieneFechaFin()) {
                statement.setDate(3, Date.valueOf(consulta.getFechaFin()));
            }
        } else if (consulta.tieneFechaFin()) {
            statement.setDate(2, Date.valueOf(consulta.getFechaFin()));
        }
    }

    protected void agregarFecha(PreparedStatement statement, Consulta consulta) throws SQLException {
        if (consulta.tieneFechaInicio()) {
            statement.setDate(1, Date.valueOf(consulta.getFechaInicio()));
            if (consulta.tieneFechaFin()) {
                statement.setDate(2, Date.valueOf(consulta.getFechaFin()));
            }
        } else if (consulta.tieneFechaFin()) {
            statement.setDate(1, Date.valueOf(consulta.getFechaFin()));
        }
    }

}
