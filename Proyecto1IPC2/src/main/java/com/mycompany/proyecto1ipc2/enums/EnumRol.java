/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto1ipc2.enums;

/**
 *
 * @author rafael-cayax
 */
public enum EnumRol {
    ENSAMBLADOR(3, "Encargado de ensamblaje"),
    VENDEDOR(2, "Encargado de ventas"),
    ADMINISTRADOR(1, "Administrador");
    
    private final int codigo;
    private final String descripcion;
    
    private EnumRol(int codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
