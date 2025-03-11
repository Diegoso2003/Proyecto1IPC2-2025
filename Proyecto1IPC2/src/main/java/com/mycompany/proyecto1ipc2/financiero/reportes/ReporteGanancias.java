/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.financiero.reportes;

import com.mycompany.proyecto1ipc2.Consulta;
import com.mycompany.proyecto1ipc2.daos.ConsultaDAO;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
import com.mycompany.proyecto1ipc2.dtos.ventas.Devolucion;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ReporteGanancias extends ConsultaDAO{
    private List<Compra> compras;
    private List<Devolucion> devoluciones;
    private double perdida = 0.0;
    private double ganancia = 0.0;
    
    @Override
    public void realizarConsulta(Consulta consulta) throws InvalidDataException {
        ReporteDevolucion reporte1 = new ReporteDevolucion();
        reporte1.realizarConsulta(consulta);
        devoluciones = reporte1.getDevoluciones();
        ReporteCompra reporte2 = new ReporteCompra();
        reporte2.realizarConsulta(consulta);
        compras = reporte2.getCompras();
        calcularGanancias();
        calcularPerdidas();
        double total = ganancia - perdida;
        if (total >= 0) {
            ganancia = total;
            perdida = 0;
        } else {
            perdida = Math.abs(total);
            ganancia = 0;
        }
        ganancia = Math.round(ganancia * 100.0)/100.0;
        perdida = Math.round(perdida * 100.0)/100.0;
    }

    private void calcularGanancias() {
        for(Compra compra: compras){
            for(DetalleCompra detalle: compra.getDetalles()){
                if (detalle.getComputadora().getEstado() == EnumEstadoCompu.VENDIDA) {
                    ganancia += detalle.getSubtotal() - detalle.getComputadora().getPrecioFabricacion();
                }
            }
        }
    }

    private void calcularPerdidas() {
        for(Devolucion devolucion: devoluciones){
            perdida += devolucion.getPerdida();
        }
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public List<Devolucion> getDevoluciones() {
        return devoluciones;
    }

    public double getPerdida() {
        return perdida;
    }

    public double getGanancia() {
        return ganancia;
    }
    
}
