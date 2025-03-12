/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.Instrucciones;

import com.mycompany.proyecto1ipc2.Estado;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.ArrayList;

/**
 *
 * @author rafael-cayax
 */
public class CargaTipoComputadora extends Instruccion{
    private TipoComputadora tipo;
    private String nombre;
    private String precio;

    public CargaTipoComputadora() {
        estadoFinal = 17;
        estados = new ArrayList<>();
        estados.add(new Estado(1, 'C', 2));
        estados.add(new Estado(2, 'O', 3));
        estados.add(new Estado(3, 'M', 4));
        estados.add(new Estado(4, 'P', 5));
        estados.add(new Estado(5, 'U', 6));
        estados.add(new Estado(6, 'T', 7));
        estados.add(new Estado(7, 'A', 8));
        estados.add(new Estado(8, 'D', 9));
        estados.add(new Estado(9, 'O', 10));
        estados.add(new Estado(10, 'R', 11));
        estados.add(new Estado(11, 'A', 12));
        estados.add(new Estado(12, ' ', 12));
        estados.add(new Estado(12, '\t', 12));
        estados.add(new Estado(12, '(', 13));
        estados.add(new Estado(13, ' ', 13));
        estados.add(new Estado(13, '\t', 13));
        estados.add(new Estado(13, '"', 14));
        estados.add(new Estado(15, ' ', 15));
        estados.add(new Estado(15, '\t', 15));
        estados.add(new Estado(15, ',', 16));
    }
    
    @Override
    public void realizarInstruccion() throws InvalidDataException, NotFoundException {
        tipo.setNombre(nombre.trim().replaceAll("\\s+", " "));
        try {
            tipo.setPrecio(Double.parseDouble(precio.trim()));
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidDataException("precio ingresado invalido: " + precio);
        }
        if (tipo.getPrecio() <= 0) {
            throw new InvalidDataException("Costo invalido: " + precio);
        }
        TipoComputadoraDAO repositorioCompu = new TipoComputadoraDAO();
        repositorioCompu.insertar(tipo);
    }

    @Override
    protected boolean evaluarEstados(char caracter) {
        for (Estado estado : estados) {
            if (estado.getEstadoActual() == estadoActual
                    && estado.getCaracter() == caracter) {
                estadoActual = estado.getEstadoSiguiente();
                return true;
            } else if (estadoActual == 14) {
                if (caracter == '"') {
                    estadoActual = 15;
                    return true;
                }
                nombre += caracter;
                return true;
            } else if (estadoActual == 16) {
                if (caracter == ')') {
                    estadoActual = 17;
                    return true;
                }
                precio += caracter;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void limpiarVariables() {
        estadoActual = 1;
        tipo = new TipoComputadora();
        nombre = "";
        precio = "";
    }
}
