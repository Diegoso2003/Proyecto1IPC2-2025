/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.mycompany.proyecto1ipc2.Estado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ExtractorFactura {

    private List<Estado> estados;
    private int estadoActual;
    private String numero;

    public ExtractorFactura() {
        construirEstados();
        estadoActual = 1;
        numero = "";
    }

    private void construirEstados() {
        estados = new ArrayList<>();
        estados.add(new Estado(1, 'F', 2));
        estados.add(new Estado(2, 'a', 3));
        estados.add(new Estado(3, 'c', 4));
        estados.add(new Estado(4, 't', 5));
        estados.add(new Estado(5, 'u', 6));
        estados.add(new Estado(6, 'r', 7));
        estados.add(new Estado(7, 'a', 8));
        estados.add(new Estado(8, ' ', 8));
        estados.add(new Estado(8, '\n', 8));
        estados.add(new Estado(8, '\t', 8));
        estados.add(new Estado(8, 'N', 9));
        estados.add(new Estado(9, 'O', 10));
        estados.add(new Estado(10, '.', 11));
        estados.add(new Estado(11, ' ', 11));
        estados.add(new Estado(11, '\n', 11));
        estados.add(new Estado(11, '\t', 11));
    }

    public int obtenerCodigo(String texto) {
        boolean cambio = false;
        char[] caracteres = texto.toCharArray();
        for (char caracter : caracteres) {
            cambio = evaluarEstados(caracter);
            if (estadoActual == 13) {
                break;
            }
            if (!cambio) {
                estadoActual = 1;
            }
            System.out.println(estadoActual + "," + caracter);
        }

        return numeroEncontrado();
    }

    private boolean esNumero(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }

    private int numeroEncontrado() {
        System.out.println(numero);
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean evaluarEstados(char caracter) {
        for (Estado estado : estados) {
            if (estado.getEstadoActual() == estadoActual
                    && estado.getCaracter() == caracter) {
                estadoActual = estado.getEstadoSiguiente();
                return true;
            } else if (estadoActual == 11 && esNumero(caracter)) {
                numero += caracter;
                estadoActual = 12;
                return true;
            } else if (estadoActual == 12) {
                if (!esNumero(caracter)) {
                    estadoActual = 13;
                    return false;
                }
                numero += caracter;
                return true;
            }
        }
        return false;
    }
}
