/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto1ipc2.enums;

/**
 *
 * @author rafael-cayax
 */
public enum EnumEstadoCompu {
    ENSAMBLADA("En sala de ventas"),
    VENDIDA("computadora vendida"),
    DEVUELTA("computadora devuelta");
    
    private final String descripcion;
    
    private EnumEstadoCompu(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}
