/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.daos.ComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.Componente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class CreadorComponente {

    private Componente componente;

    public void crearComponente(HttpServletRequest request) throws InvalidDataException {
        obtenerYValidarDatos(request);
        crear();
    }

    private void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        try {
            componente = new Componente();
            componente.setNombre(request.getParameter("nombre").trim());
            componente.setCantidad(Integer.parseInt(request.getParameter("existencia")));
            componente.setPrecio(Double.parseDouble(request.getParameter("precio")));
            if (!componente.esValido()) {
                throw new InvalidDataException();
            }
        } catch (NumberFormatException | NullPointerException | InvalidDataException e) {
            throw new InvalidDataException("Ingrese los datos correctamente");
        }
    }

    private void crear() throws InvalidDataException {
        ComponenteDAO c = new ComponenteDAO();
        c.CrearComponente(componente);
    }

}
