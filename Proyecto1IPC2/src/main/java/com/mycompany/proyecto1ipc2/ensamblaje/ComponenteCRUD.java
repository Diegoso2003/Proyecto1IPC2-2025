/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ComponenteCRUD extends CRUD<Componente>{

    public ComponenteCRUD() {
        super("Componente", new ComponenteDAO());
    }

    /**
     * recupera y valida los datos ingresados por el usuario para poder crear
     * el tipo de componente
     * @param request los datos del componente
     * @throws InvalidDataException si los datos son invalidos
     */
    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        try {
            entidad = new Componente();
            TipoComponente tipo = new TipoComponente();
            tipo.setId(Integer.parseInt(request.getParameter("tipo")));
            entidad.setTipo(tipo);
            entidad.setCantidad(Integer.parseInt(request.getParameter("existencia")));
            entidad.setPrecio(Double.parseDouble(request.getParameter("precio")));
            if (actu) {
                entidad.setId(Integer.parseInt(request.getParameter("id")));
            }
            if (!entidad.esValido()) {
                throw new InvalidDataException();
            }
        } catch (NumberFormatException | NullPointerException | InvalidDataException e) {
            throw new InvalidDataException("Ingrese los datos correctamente");
        }
    }

    @Override
    public Componente actualizarEntidad(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        super.actualizarEntidad(request);
        TipoComponenteDAO tipo = new TipoComponenteDAO();
        Optional<TipoComponente> tipoC = tipo.encontrarPorID(entidad.getTipo().getId());
        entidad.setTipo(tipoC.get());
        return entidad;
    }
    
}
