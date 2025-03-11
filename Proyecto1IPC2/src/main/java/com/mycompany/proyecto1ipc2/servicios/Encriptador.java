/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.servicios;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rafael-cayax
 */
public class Encriptador {
    
    /**
     * metodo para evaluar la contraseña ingresada con la que esta encriptada
     * @param entrada
     * @param hasheada
     * @return 
     */
    public boolean esValida(String entrada, String hasheada){
        return BCrypt.checkpw(entrada, hasheada);
    }
    
    public String encriptar(String contraseña){
        return BCrypt.hashpw(contraseña, BCrypt.gensalt());
    }
}
