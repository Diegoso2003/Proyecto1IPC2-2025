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
    
    /**
     * valida que sus datos hayan sido ingresados correctamente
     * @return false si el nombre o la contraseña son nulos o estan compuestos
     * de espacios en blanco, true para cualquier otra cosa
     */
    public boolean esValido() {
        return nombre != null && !nombre.isBlank() && contraseña != null && !contraseña.isBlank();
    }
    
}
