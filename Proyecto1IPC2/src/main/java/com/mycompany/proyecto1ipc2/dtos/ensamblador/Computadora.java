/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.dtos.ensamblador;

import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class Computadora {
    private int idComputadora;
    private LocalDate fechaEnsamblaje;
    private String ensamblador;
    private double precioFabricacion;
    private double precioVenta;
    private TipoComputadora tipo;
    private EnumEstadoCompu estado;
    private List<Componente> partes;

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public LocalDate getFechaEnsamblaje() {
        return fechaEnsamblaje;
    }

    public void setFechaEnsamblaje(LocalDate fechaEnsamblaje) {
        this.fechaEnsamblaje = fechaEnsamblaje;
    }

    public String getEnsamblador() {
        return ensamblador;
    }

    public void setEnsamblador(String ensamblador) {
        this.ensamblador = ensamblador;
    }

    public double getPrecioFabricacion() {
        return precioFabricacion;
    }

    public void setPrecioFabricacion(double precioFabricacion) {
        this.precioFabricacion = precioFabricacion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public TipoComputadora getTipo() {
        return tipo;
    }

    public void setTipo(TipoComputadora tipo) {
        this.tipo = tipo;
    }

    public EnumEstadoCompu getEstado() {
        return estado;
    }

    public void setEstado(EnumEstadoCompu estado) {
        this.estado = estado;
    }

    public List<Componente> getPartes() {
        return partes;
    }

    public void setPartes(List<Componente> partes) {
        this.partes = partes;
    }

}
