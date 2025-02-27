/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.daos.ComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.Componente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class AdminComponente {

    private Componente componente;

    /**
     * metodo para conectar con la base de datos de los componentes
     * @param request el id del componente
     * @return el componente buscado
     * @throws InvalidDataException en caso de no encontrar el componente o enviar
     * un id invalido
     */
    public Componente obtenerDatosComponente(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        try {
            ComponenteDAO c = new ComponenteDAO();
            int codigo = Integer.parseInt(request.getParameter("id"));
            Optional<Componente> posibleComponente = c.encontrarPorID(codigo);
            componente = posibleComponente.orElseThrow(() -> new NotFoundException
        ("componente con ID: '" + codigo + "' no encontrado"));
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidDataException("id no valido");
        }
        return componente;
    }

}
