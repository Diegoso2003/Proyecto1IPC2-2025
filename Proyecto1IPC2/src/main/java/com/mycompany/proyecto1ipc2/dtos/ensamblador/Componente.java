/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.dtos.ensamblador;

import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;

/**
 *
 * @author rafael-cayax
 */
public class Componente {
    private int cantidad;
    private double precio;
    private int id;
    private TipoComponente tipo;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoComponente getTipo() {
        return tipo;
    }

    public void setTipo(TipoComponente tipo) {
        this.tipo = tipo;
    }
    
    /**
     * metodo que valida si los datos del componente son validos
     * @return true si es valido 
     */
    public boolean esValido(){
        return cantidad >= 0 && precio > 0;
    }
}
