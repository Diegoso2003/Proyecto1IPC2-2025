/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.Instrucciones;

import com.mycompany.proyecto1ipc2.Estado;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class CargaIndicacionCompu extends Instruccion{
    private String compu;
    private String componente;
    private String cantidad;

    public CargaIndicacionCompu() {
        estadoFinal = 24;
        estados = new ArrayList<>();
        estados.add(new Estado(1, 'E', 2));
        estados.add(new Estado(2, 'N', 3));
        estados.add(new Estado(3, 'S', 4));
        estados.add(new Estado(4, 'A', 5));
        estados.add(new Estado(5, 'M', 6));
        estados.add(new Estado(6, 'B', 7));
        estados.add(new Estado(7, 'L', 8));
        estados.add(new Estado(8, 'E', 9));
        estados.add(new Estado(9, '_', 10));
        estados.add(new Estado(10, 'P', 11));
        estados.add(new Estado(11, 'I', 12));
        estados.add(new Estado(12, 'E', 13));
        estados.add(new Estado(13, 'Z', 14));
        estados.add(new Estado(14, 'A', 15));
        estados.add(new Estado(15, 'S', 16));
        estados.add(new Estado(16, ' ', 16));
        estados.add(new Estado(16, '\t', 16));
        estados.add(new Estado(16, '(', 17));
        estados.add(new Estado(17, ' ', 17));
        estados.add(new Estado(17, '\t', 17));
        estados.add(new Estado(17, '"', 18));
        estados.add(new Estado(19, ' ', 19));
        estados.add(new Estado(19, '\t', 19));
        estados.add(new Estado(19, ',', 20));
        estados.add(new Estado(20, ' ', 20));
        estados.add(new Estado(20, '\t', 20));
        estados.add(new Estado(20, '"', 21));
        estados.add(new Estado(22, ' ', 22));
        estados.add(new Estado(22, '\t', 22));
        estados.add(new Estado(22, ',', 23));
    }
    
    @Override
    public void realizarInstruccion() throws InvalidDataException, NotFoundException {
        TipoComputadoraDAO repositorioTipoCompu = new TipoComputadoraDAO();
        Optional<TipoComputadora> posibleTipo = 
                repositorioTipoCompu.obtenerPorNombre(compu.trim().replaceAll("\\s+", " "));
        TipoComputadora tipoCompu = 
                posibleTipo.orElseThrow(() -> new InvalidDataException("tipo de computadora no encontrado"));
        TipoComponenteDAO repositorioTipoComponente = new TipoComponenteDAO();
        Optional<TipoComponente> posibleTipoComponente = 
                repositorioTipoComponente.encontrarPorNombre(componente.trim().replaceAll("\\s+", " "));
        TipoComponente tipoComponente = posibleTipoComponente.orElseThrow(() -> new InvalidDataException("componente no encontrado"));
        try {
            tipoComponente.setCantidad(Integer.parseInt(cantidad.trim()));
        } catch (NumberFormatException e) {
            throw new InvalidDataException("cantida ingresada invalida: " + cantidad);
        }
        if (tipoComponente.getCantidad() <= 0) {
            throw new InvalidDataException("cantidad ingresada invalida: " + cantidad);
        }
        repositorioTipoCompu.insertarIndicacion(tipoCompu, tipoComponente);
    }

    @Override
    protected boolean evaluarEstados(char caracter) {
        for (Estado estado : estados) {
            if (estado.getEstadoActual() == estadoActual
                    && estado.getCaracter() == caracter) {
                estadoActual = estado.getEstadoSiguiente();
                return true;
            } else if (estadoActual == 18) {
                if (caracter == '"') {
                    estadoActual = 19;
                    return true;
                }
                compu += caracter;
                return true;
            } else if (estadoActual == 21) {
                if (caracter == '"') {
                    estadoActual = 22;
                    return true;
                }
                componente += caracter;
                return true;
            } else if (estadoActual == 23){
                if (caracter == ')') {
                    estadoActual = 24;
                    return true;
                }
                cantidad += caracter;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void limpiarVariables() {
        estadoActual = 1;
        compu = "";
        componente = "";
        cantidad = "";
    }
    
}
