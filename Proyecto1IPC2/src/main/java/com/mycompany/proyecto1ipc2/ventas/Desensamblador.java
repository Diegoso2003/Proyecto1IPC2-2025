/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.DetalleEnsamblajeDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.DetalleEnsamblaje;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class Desensamblador {

    private List<DetalleEnsamblaje> detalles;
    private List<Componente> componentes;
    private ComponenteDAO repositorioComponente;
    
    public void desensamblar(Computadora computadora) throws InvalidDataException, NotFoundException {
        DetalleEnsamblajeDAO repositorio = new DetalleEnsamblajeDAO(computadora);
        detalles = repositorio.obtenerTodo();
        obtenerComponentes();
        actualizarComponentes();
    }

    private void obtenerComponentes() {
        componentes = new ArrayList<>();
        repositorioComponente = new ComponenteDAO();
        for(DetalleEnsamblaje detalle : detalles){
            List<Componente> lista = repositorioComponente.obtenerStockComponentes(detalle.getTipoComponente().getId());
            if (!lista.isEmpty()) {
                Componente componente = lista.getFirst();
                componente.setCantidad(componente.getCantidad() + detalle.getCantidad());
                componentes.add(componente);
            }
        }
    }

    private void actualizarComponentes() throws InvalidDataException, NotFoundException {
        for(Componente componente: componentes){
            repositorioComponente.actualizar(componente);
        }
    }
    
}
