/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.dtos.ventas;

/**
 *
 * @author rafael-cayax
 */
public class Cliente {
    private int nit;
    private String nombre;
    private String direccion;

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public boolean esValido(){
        return esNombreValido() && esDireccionValida() && esNitValido();
    }
    
    private boolean esNombreValido(){
        return nombre != null && !nombre.isBlank() && nombre.length() <= 200;
    }
    
    private boolean esDireccionValida(){
        return direccion != null && !direccion.isBlank() && direccion.length() <= 250;
    }
    
    private boolean esNitValido(){
        return nit >= 100000000 && nit <= 999999999;
    }
}
