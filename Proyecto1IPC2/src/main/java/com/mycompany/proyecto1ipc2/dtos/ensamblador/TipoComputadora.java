/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.dtos.ensamblador;

import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class TipoComputadora {
    private int idTipo;
    private String nombre;
    private double precio;
    private List<TipoComponente> indicaciones;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<TipoComponente> getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(List<TipoComponente> indicaciones) {
        this.indicaciones = indicaciones;
    }
    
}
