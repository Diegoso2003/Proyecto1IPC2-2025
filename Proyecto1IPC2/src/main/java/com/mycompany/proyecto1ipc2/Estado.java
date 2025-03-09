/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2;

/**
 *
 * @author rafael-cayax
 */
public class Estado {
    private final int estadoActual;
    private final char caracter;
    private final int estadoSiguiente;

    public Estado(int estadoActual, char caracter, int estadoSiguiente) {
        this.estadoActual = estadoActual;
        this.caracter = caracter;
        this.estadoSiguiente = estadoSiguiente;
    }

    public int getEstadoActual() {
        return estadoActual;
    }

    public char getCaracter() {
        return caracter;
    }

    public int getEstadoSiguiente() {
        return estadoSiguiente;
    }
    
}
