/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero;

import com.mycompany.proyecto1ipc2.Lector;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.financiero.Instrucciones.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class CargaArchivo {

    private List<String> instrucciones;
    private List<Instruccion> acciones;
    private int linea = 1;
    private StringBuilder error;
    private boolean hayError = false;

    public CargaArchivo() {
        crearAcciones();
        error = new StringBuilder();
    }

    public void cargarDatos(HttpServletRequest request) throws InvalidDataException {
        try {
            Part archivoPart = request.getPart("archivo");
            Lector lector = new Lector();
            instrucciones = lector.leerContenidoArchivo(archivoPart.getInputStream());
            evaluarInstrucciones();
            if (hayError) {
                throw new InvalidDataException(error.toString());
            }
        } catch (IOException | ServletException | NullPointerException ex) {
            throw new InvalidDataException("ingresar un archivo valido");
        }
    }

    private void crearAcciones() {
        acciones = new ArrayList<>();
        acciones.add(new CreacionUsuario());
        acciones.add(new CargaComponentes());
        acciones.add(new CargaTipoComputadora());
        acciones.add(new CargaIndicacionCompu());
        acciones.add(new CargaEnsamblaje());
    }

    private void evaluarInstrucciones() {
        for (String instruccion : instrucciones) {
            if (!instruccion.isEmpty()) {
                evaluarAcciones(instruccion);
            }
            linea++;
        }
    }

    private void evaluarAcciones(String instruccion) {
        for (Instruccion accion : acciones) {
            if (accion.esInstruccion(instruccion)) {
                try {
                    accion.realizarInstruccion();
                } catch (InvalidDataException | NotFoundException ex) {
                    agregarError(instruccion + ": " + ex.getMessage());
                }
                return;
            }
        }
        agregarError("Instruccion no reconocida: "+instruccion);
    }

    private void agregarError(String errores) {
        hayError = true;
        error.append("Error en la linea: '").append(linea)
                .append("': </br>").append(errores).
                append("</br>");
    }

}
