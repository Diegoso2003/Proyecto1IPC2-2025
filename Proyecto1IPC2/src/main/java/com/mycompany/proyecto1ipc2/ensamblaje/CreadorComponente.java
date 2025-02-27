/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.daos.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.Componente;
import com.mycompany.proyecto1ipc2.dtos.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class CreadorComponente {

    private Componente componente;
    private boolean actu = false;

    public void crearComponente(HttpServletRequest request) throws InvalidDataException {
        obtenerYValidarDatos(request);
        crear();
    }

    /**
     * recupera y valida los datos ingresados por el usuario para poder crear
     * el tipo de componente
     * @param request los datos del componente
     * @throws InvalidDataException si los datos son invalidos
     */
    private void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        try {
            componente = new Componente();
            TipoComponente tipo = new TipoComponente();
            tipo.setId(Integer.parseInt(request.getParameter("tipo")));
            componente.setTipo(tipo);
            componente.setCantidad(Integer.parseInt(request.getParameter("existencia")));
            componente.setPrecio(Double.parseDouble(request.getParameter("precio")));
            if (actu) {
                componente.setId(Integer.parseInt(request.getParameter("id")));
            }
            if (!componente.esValido()) {
                throw new InvalidDataException();
            }
        } catch (NumberFormatException | NullPointerException | InvalidDataException e) {
            throw new InvalidDataException("Ingrese los datos correctamente");
        }
    }

    /**
     * se conecta a la base de datos para crear al componente
     * @throws InvalidDataException 
     */
    private void crear() throws InvalidDataException {
        ComponenteDAO c = new ComponenteDAO();
        c.insertar(componente);
    }

    public void actualizarComponente(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        actu = true;
        obtenerYValidarDatos(request);
        ComponenteDAO c = new ComponenteDAO();
        c.actualizar(componente);
        TipoComponenteDAO tipo = new TipoComponenteDAO();
        Optional<TipoComponente> tipoC = tipo.encontrarPorID(componente.getTipo().getId());
        componente.setTipo(tipoC.get());
        request.setAttribute("componente", componente);
    }

}
