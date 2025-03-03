/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComputadoraDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.DetalleEnsamblajeDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.DetalleEnsamblaje;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ComputadoraCRUD extends CRUD<Computadora>{
    private ComponenteDAO repositorio2;
    private List<List<Componente>> stock;
    private StringBuilder errores;
    private boolean inventarioInsuficiente;
    private TipoComputadora tipo;

    public ComputadoraCRUD() {
        repositorio = new ComputadoraDAO();
        nombre = "Computadora";
    }
    
    @Override
    public void crearEntidad(HttpServletRequest request) throws InvalidDataException {
        TipoComputadoraCRUD compu = new TipoComputadoraCRUD();
        try {
            request.setAttribute("tipo_computadora", compu.obtenerEntidad(request));
        } catch (NotFoundException ex) {
            throw new InvalidDataException(ex.getMessage());
        }
        obtenerYValidarDatos(request);
        repositorio.insertar(entidad);
        ingresarPartes();
        actualizarInventario();
        entidad.setPrecioFabricacion((entidad.getPrecioFabricacion() * 100.0)/100.0);
    }

    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException {
        entidad = new Computadora();
        try {
            entidad.setFechaEnsamblaje(LocalDate.parse(request.getParameter("fecha")));
            entidad.setEnsamblador(request.getSession().getAttribute("Usuario").toString());
            entidad.setEstado(EnumEstadoCompu.ENSAMBLADA);
            tipo = new TipoComputadora();
            tipo.setIdTipo(obtenerID(request));
            entidad.setTipo(tipo);
            recuperarInstrucciones();
        } catch (NullPointerException | DateTimeParseException e) {
            throw new InvalidDataException("Ingrese correctamente los datos" + e);
        }
    }

    private void recuperarInstrucciones() throws InvalidDataException {
        TipoComputadoraDAO tipoCompu = new TipoComputadoraDAO();
        Optional<TipoComputadora> posibleCompu = tipoCompu.encontrarPorID(entidad.getTipo().getIdTipo());
        tipo = posibleCompu.orElseThrow(() -> new InvalidDataException("Tipo de computadora no encontrado"));
        recuperarInventario();
        verificarExistencia();
        if (inventarioInsuficiente) {
            throw new InvalidDataException(errores.toString());
        }
    }

    private void recuperarInventario() {
        List<TipoComponente> indicaciones = tipo.getIndicaciones();
        repositorio2 = new ComponenteDAO();
        stock = new ArrayList<>();
        errores = new StringBuilder();
        errores.append("No se puede completar el ensamblaje. Los siguientes componentes hacen falta:");
        for (TipoComponente indicacion: indicaciones) {
            List<Componente> inventario = repositorio2.obtenerStock(indicacion.getId());
            stock.add(inventario);
        }
    }    

    private void verificarExistencia() {
        entidad.setPrecioFabricacion(0.0);
        List<TipoComponente> indicaciones = tipo.getIndicaciones();
        Iterator<TipoComponente> iteradorIndicacion = indicaciones.iterator();
        Iterator<List<Componente>> iteradorInventario = stock.iterator();
        
        while(iteradorIndicacion.hasNext() && iteradorInventario.hasNext()){
            TipoComponente tipoAuxiliar = iteradorIndicacion.next();
            List<Componente> componentes = iteradorInventario.next();
            int cantidadNecesaria = tipoAuxiliar.getCantidad();
            for (Componente componente: componentes) {
                if (cantidadNecesaria == 0) {
                    break;
                }
                int cantidadEnStock = componente.getCantidad();
                double total;
                if (cantidadEnStock < cantidadNecesaria) {
                    cantidadNecesaria -= cantidadEnStock;
                    total = componente.getPrecio() * cantidadEnStock;
                    componente.setCantidad(0);
                } else {
                    componente.setCantidad(componente.getCantidad() - cantidadNecesaria);
                    total = componente.getPrecio() * cantidadNecesaria;
                    cantidadNecesaria = 0;
                }
                entidad.setPrecioFabricacion(entidad.getPrecioFabricacion() + total);
            }
            if (cantidadNecesaria > 0) {
                inventarioInsuficiente = true;
                errores.append("<p>- '").append(tipoAuxiliar.getNombre()).
                        append("' faltan ").append(cantidadNecesaria).append(" unidades </p>");
            }
        }
        errores.append("Por favor, reabastecer los componentes para continuar.");
    }


    private void ingresarPartes() throws InvalidDataException {
        List<TipoComponente> componentes = tipo.getIndicaciones();
        DetalleEnsamblajeDAO detalles = new DetalleEnsamblajeDAO();
        for(TipoComponente componente: componentes){
            DetalleEnsamblaje detalle = new DetalleEnsamblaje();
            detalle.setComputadora(entidad);
            detalle.setTipoComponente(componente);
            detalle.setCantidad(componente.getCantidad());
            detalles.insertar(detalle);
        }
    }

    private void actualizarInventario() throws InvalidDataException {
        for(List<Componente> componentes: stock){
            for(Componente componente: componentes){
                try {
                    repositorio2.actualizar(componente);
                } catch (NotFoundException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
