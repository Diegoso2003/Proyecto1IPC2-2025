/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.dtos;

import com.mycompany.proyecto1ipc2.enums.EnumRol;

/**
 *
 * @author rafael-cayax
 */
public class Usuario {
    private String nombre;
    private String contraseña;
    private String confirmacionContraseña;
    private EnumRol rol;
    private boolean activo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public EnumRol getRol() {
        return rol;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getConfirmacionContraseña() {
        return confirmacionContraseña;
    }

    public void setConfirmacionContraseña(String confirmacionContraseña) {
        this.confirmacionContraseña = confirmacionContraseña;
    }
    
    private boolean sonContraseñasIguales(){
        return confirmacionContraseña != null && contraseña.equals(confirmacionContraseña);
    }
    
    private boolean esContraseñaValida(){
        return contraseña != null && contraseña.length() >= 6 && !contraseña.isBlank();
    }
    
    private boolean esNombreValido(){
        nombre = nombre.trim().replaceAll("\\s+", " ");
        return nombre != null && !nombre.isEmpty() && nombre.length() >= 6 && nombre.length() <= 200;
    }
    
    public boolean esCreacionValida(){
        return esNombreValido() && esContraseñaValida() && sonContraseñasIguales();
    }

}
