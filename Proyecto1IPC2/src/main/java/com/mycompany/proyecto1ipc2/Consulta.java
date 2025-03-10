/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2;

import java.time.LocalDate;

/**
 *
 * @author rafael-cayax
 */
public class Consulta {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    public boolean tieneFechaInicio(){
        return fechaInicio != null;
    }
    
    public boolean tieneFechaFin(){
        return fechaFin != null;
    }
    
    public boolean tieneAmbas(){
        return tieneFechaFin() && tieneFechaInicio();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
