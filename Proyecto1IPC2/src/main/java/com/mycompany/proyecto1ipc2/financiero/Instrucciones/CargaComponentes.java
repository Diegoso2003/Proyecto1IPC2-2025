/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.Instrucciones;

import com.mycompany.proyecto1ipc2.Estado;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Componente;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class CargaComponentes extends Instruccion {

    private String nombrePieza;
    private String precio;
    private TipoComponente tipo;

    public CargaComponentes() {
        estadoFinal = 11;
        estados = new ArrayList<>();
        estados.add(new Estado(1, 'P', 2));
        estados.add(new Estado(2, 'I', 3));
        estados.add(new Estado(3, 'E', 4));
        estados.add(new Estado(4, 'Z', 5));
        estados.add(new Estado(5, 'A', 6));
        estados.add(new Estado(6, ' ', 6));
        estados.add(new Estado(6, '\t', 6));
        estados.add(new Estado(6, '(', 7));
        estados.add(new Estado(7, ' ', 7));
        estados.add(new Estado(7, '\t', 7));
        estados.add(new Estado(7, '"', 8));
        estados.add(new Estado(9, ' ', 9));
        estados.add(new Estado(9, '\t', 9));
        estados.add(new Estado(9, ',', 10));
    }

    @Override
    public void realizarInstruccion() throws InvalidDataException, NotFoundException {
        tipo = new TipoComponente();
        tipo.setNombre(nombrePieza.trim().replaceAll("\\s+", " "));
        double costo;
        try {
            costo = Double.valueOf(precio.trim());
        } catch (NumberFormatException e) {
            throw new InvalidDataException("precio invalido: " + precio);
        }
        if (costo <= 0) {
            throw new InvalidDataException("costo invalido: " + costo);
        }
        verificarExistencia();
        consultarInventario(costo);
    }

    @Override
    protected boolean evaluarEstados(char caracter) {
        for (Estado estado : estados) {
            if (estado.getEstadoActual() == estadoActual
                    && estado.getCaracter() == caracter) {
                estadoActual = estado.getEstadoSiguiente();
                return true;
            } else if (estadoActual == 8) {
                if (caracter == '"') {
                    estadoActual = 9;
                    return true;
                }
                nombrePieza += caracter;
                return true;
            } else if (estadoActual == 10) {
                if (caracter == ')') {
                    estadoActual = 11;
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
        nombrePieza = "";
        precio = "";
    }

    private void verificarExistencia() throws InvalidDataException {
        TipoComponenteDAO repositorioTipo = new TipoComponenteDAO();
        Optional<TipoComponente> posibleTipo = repositorioTipo.encontrarPorNombre(tipo.getNombre());
        if (!posibleTipo.isPresent()) {
            repositorioTipo.insertar(tipo);
        }
        posibleTipo = repositorioTipo.encontrarPorNombre(tipo.getNombre());
        this.tipo = posibleTipo.get();
    }

    private void consultarInventario(double costo) throws InvalidDataException {
        ComponenteDAO repositorioComponente = new ComponenteDAO();
        Componente componente = new Componente();
        componente.setTipo(tipo);
        componente.setPrecio(costo);
        if (repositorioComponente.verificarExistencia(componente)) {
            componente.setPrecio(Math.round(componente.getPrecio() * 100.0)/100.0);
            repositorioComponente.actualizarEnUno(componente);
        } else {
            componente.setCantidad(1);
            repositorioComponente.insertar(componente);
        }
    }

}
