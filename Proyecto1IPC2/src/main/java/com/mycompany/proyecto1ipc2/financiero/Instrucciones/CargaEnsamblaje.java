/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.Instrucciones;

import com.mycompany.proyecto1ipc2.Estado;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComputadoraDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora;
import com.mycompany.proyecto1ipc2.ensamblaje.Ensamblaje;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class CargaEnsamblaje extends Instruccion{
    private String datos = "ENSAMBLAR_COMPUTADORA(“Gamer Pro”, dmorales, “21/02/2025”)";
    private String compu;
    private String usuario;
    private String fecha;

    public CargaEnsamblaje() {
        estadoFinal = 30;
        estados = new ArrayList<>();
        estados.add(new Estado(1, 'E', 2));
        estados.add(new Estado(2, 'N', 3));
        estados.add(new Estado(3, 'S', 4));
        estados.add(new Estado(4, 'A', 5));
        estados.add(new Estado(5, 'M', 6));
        estados.add(new Estado(6, 'B', 7));
        estados.add(new Estado(7, 'L', 8));
        estados.add(new Estado(8, 'A', 9));
        estados.add(new Estado(9, 'R', 10));
        estados.add(new Estado(10, '_', 11));
        estados.add(new Estado(11, 'C', 12));
        estados.add(new Estado(12, 'O', 13));
        estados.add(new Estado(13, 'M', 14));
        estados.add(new Estado(14, 'P', 15));
        estados.add(new Estado(15, 'U', 16));
        estados.add(new Estado(16, 'T', 17));
        estados.add(new Estado(17, 'A', 18));
        estados.add(new Estado(18, 'D', 19));
        estados.add(new Estado(19, 'O', 20));
        estados.add(new Estado(20, 'R', 21));
        estados.add(new Estado(21, 'A', 22));
        estados.add(new Estado(22, ' ', 22));
        estados.add(new Estado(22, '\t', 22));
        estados.add(new Estado(22, '(', 23));
        estados.add(new Estado(23, ' ', 23));
        estados.add(new Estado(23, '\t', 23));
        estados.add(new Estado(23, '"', 24));
        estados.add(new Estado(25, ' ', 25));
        estados.add(new Estado(25, '\t', 25));
        estados.add(new Estado(25, ',', 26));
        estados.add(new Estado(27, ' ', 27));
        estados.add(new Estado(27, '\t', 27));
        estados.add(new Estado(27, '"', 28));
        estados.add(new Estado(29, ' ', 29));
        estados.add(new Estado(29, '\t', 29));
        estados.add(new Estado(29, ')', 30));
    }
    
    @Override
    public void realizarInstruccion() throws InvalidDataException, NotFoundException {
        TipoComputadoraDAO repositorioCompu = new TipoComputadoraDAO();
        Optional<TipoComputadora> posibleTipo = repositorioCompu.obtenerPorNombre(compu.trim().replaceAll("\\s+", " "));
        TipoComputadora tipo = posibleTipo.orElseThrow(() -> new InvalidDataException("computadora no encontrada"));
        Computadora computadora = new Computadora();
        computadora.setTipo(tipo);
        computadora.setFechaEnsamblaje(formatearFecha());
        computadora.setEnsamblador(usuario.trim().replaceAll("\\s+", " "));
        ComputadoraDAO repositorio2 = new ComputadoraDAO();
        Ensamblaje ensamblaje = new Ensamblaje(computadora);
        ensamblaje.ensamblar();
        repositorio2.insertar(computadora);
        ensamblaje.actualizarRepositorios();
    }

    @Override
    protected boolean evaluarEstados(char caracter) {
        for (Estado estado : estados) {
            if (estado.getEstadoActual() == estadoActual
                    && estado.getCaracter() == caracter) {
                estadoActual = estado.getEstadoSiguiente();
                return true;
            } else if (estadoActual == 24) {
                if (caracter == '"') {
                    estadoActual = 25;
                    return true;
                }
                compu += caracter;
                return true;
            } else if (estadoActual == 26) {
                if (caracter == ',') {
                    estadoActual = 27;
                    return true;
                }
                usuario += caracter;
                return true;
            } else if (estadoActual == 28) {
                if (caracter == '"') {
                    estadoActual = 29;
                    return true;
                }
                fecha += caracter;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void limpiarVariables() {
        estadoActual = 1;
        compu = "";
        usuario = "";
        fecha = "";
    }
    
    private LocalDate formatearFecha() throws InvalidDataException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate fechaFormateada = LocalDate.parse(fecha, formatter);
            return fechaFormateada;
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("fecha ingresada invalida: '" + fecha + "'");
        }
    }
    
}
