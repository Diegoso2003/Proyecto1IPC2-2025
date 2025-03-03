/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ensamblaje;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author rafael-cayax
 */
public class TipoComputadoraLista {

    public String obtenerDireccion(HttpServletRequest request) {
        String direccion;
        String area = request.getParameter("area");
        if (area == null || area.isBlank()) {
            direccion = "/vista_financiera/tipo_computadoras.jsp";
            return direccion;
        }
        
        direccion = "/vista_ensamblador/tipo_computadoras.jsp";
        return direccion;
    }
    
}
