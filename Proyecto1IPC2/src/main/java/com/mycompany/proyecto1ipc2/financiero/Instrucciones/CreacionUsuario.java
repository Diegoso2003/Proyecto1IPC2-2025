/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.Instrucciones;

import com.mycompany.proyecto1ipc2.Estado;
import com.mycompany.proyecto1ipc2.daos.UsuarioDAO;
import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.enums.EnumRol;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.servicios.Encriptador;
import java.util.ArrayList;

/**
 *
 * @author rafael-cayax
 */
public class CreacionUsuario extends Instruccion {

    private String nombreUsuario = "";
    private String contraseña = "";
    private String rol = "";

    public CreacionUsuario() {
        estadoFinal = 16;
        estados = new ArrayList<>();
        estados.add(new Estado(1, 'U', 2));
        estados.add(new Estado(2, 'S', 3));
        estados.add(new Estado(3, 'U', 4));
        estados.add(new Estado(4, 'A', 5));
        estados.add(new Estado(5, 'R', 6));
        estados.add(new Estado(6, 'I', 7));
        estados.add(new Estado(7, 'O', 8));
        estados.add(new Estado(8, ' ', 8));
        estados.add(new Estado(8, '\t', 8));
        estados.add(new Estado(8, '(', 9));
        estados.add(new Estado(9, ' ', 9));
        estados.add(new Estado(9, '\t', 9));
        estados.add(new Estado(9, '"', 10));
        estados.add(new Estado(11, ' ', 11));
        estados.add(new Estado(11, '\t', 11));
        estados.add(new Estado(11, ',', 12));
        estados.add(new Estado(12, ' ', 12));
        estados.add(new Estado(12, '\t', 12));
        estados.add(new Estado(12, '"', 13));
        estados.add(new Estado(14, ',', 15));
        estados.add(new Estado(15, ' ', 15));
        estados.add(new Estado(15, '\t', 15));
    }

    @Override
    public void realizarInstruccion() throws InvalidDataException, NotFoundException {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreUsuario.trim().replaceAll("\\s+", " "));
        switch(rol.trim()){
            case "1":
                usuario.setRol(EnumRol.ENSAMBLADOR);
                break;
            case "2":
                usuario.setRol(EnumRol.VENDEDOR);
                break;
            case "3":
                usuario.setRol(EnumRol.ADMINISTRADOR);
                break;
            default:
                throw new InvalidDataException("rol no reconocido: " + rol);
        }
        usuario.setContraseña(contraseña);
        usuario.setConfirmacionContraseña(contraseña);
        if (!usuario.esCreacionValida()) {
            throw new InvalidDataException("ingresar valores validos para crear al usuario");
        }
        Encriptador encriptador = new Encriptador();
        usuario.setContraseña(encriptador.encriptar(usuario.getContraseña()));
        repositorio = new UsuarioDAO();
        repositorio.insertar(usuario);
    }

    @Override
    protected boolean evaluarEstados(char caracter) {
        for (Estado estado : estados) {
            if (estado.getEstadoActual() == estadoActual
                    && estado.getCaracter() == caracter) {
                estadoActual = estado.getEstadoSiguiente();
                return true;
            } else if (estadoActual == 10) {
                if (caracter == '"') {
                    estadoActual = 11;
                    return true;
                }
                nombreUsuario += caracter;
                return true;
            } else if (estadoActual == 13) {
                if (caracter == '"') {
                    estadoActual = 14;
                    return true;
                }
                contraseña += caracter;
                return true;
            } else if (estadoActual == 15) {
                if (caracter == ')') {
                    estadoActual = 16;
                    return true;
                }
                rol += caracter;
                return true;
            }
        }
        return false;
    }

    @Override
    protected void limpiarVariables() {
        nombreUsuario = "";
        contraseña = "";
        rol = "";
        estadoActual = 1;
    }

}
