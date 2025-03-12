/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.Instrucciones;

import com.mycompany.proyecto1ipc2.Estado;
import com.mycompany.proyecto1ipc2.daos.BDCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public abstract class Instruccion {

    protected BDCRUD repositorio;
    protected List<Estado> estados;
    protected int estadoActual = 1;
    protected int estadoFinal;

    public boolean esInstruccion(String instruccion){
        limpiarVariables();
        char[] caracteres = instruccion.toCharArray();
        for (char caracter : caracteres) {
            if(estadoActual == estadoFinal){
                return true;
            }
            if (!evaluarEstados(caracter)) {
                return false;
            }
        }
        return estadoActual == estadoFinal;
    }

    public abstract void realizarInstruccion() throws InvalidDataException, NotFoundException;
    
    protected abstract boolean evaluarEstados(char caracter);
    protected abstract void limpiarVariables();

    protected boolean esNumero(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }
}
