/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.dtos.financiero;

import com.mycompany.proyecto1ipc2.dtos.Usuario;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class ReporteMasVentas {
    private Usuario usuario;
    private int totalVentas;
    private List<Compra> compras;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }
    
}
