/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComputadoraDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.CompraDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class Factura {
    private Document documento;
    private Compra compra;

    public Factura(Document documento, Compra compra) {
        this.documento = documento;
        this.compra = compra;
    }
    
    public void crearFactura() throws InvalidDataException, NotFoundException, DocumentException {
        CompraDAO repositorio = new CompraDAO();
        repositorio.actualizar(compra);
        actualizarComputadoras();
        escribirDocumento();
    }

    private void actualizarComputadoras() throws InvalidDataException, NotFoundException {
        ComputadoraDAO repositorio = new ComputadoraDAO();
        List<DetalleCompra> detalles = compra.getDetalles();
        for(DetalleCompra detalle: detalles){
            Computadora computadora = detalle.getComputadora();
            computadora.setEstado(EnumEstadoCompu.VENDIDA);
            repositorio.actualizar(computadora);
        }
    }

    private void escribirDocumento() throws DocumentException {
        documento.open();
        documento.add(new Paragraph("Factura NO." + compra.getIdCompra()));
        documento.add(new Paragraph("Cliente: " + compra.getCliente().getNombre()));
        documento.add(new Paragraph("NIT: " + compra.getCliente().getDireccion()));
        documento.add(new Paragraph("Direccion: " + compra.getCliente().getDireccion()));
        documento.add(new Paragraph("Fecha: " + compra.getFechaCompra().toString()));
        documento.add(new Paragraph("Total: Q" + compra.getTotal()));
        documento.add(new Paragraph("Detalles de la compra:"));
        documento.add(new Paragraph("\n"));
        agregarDetalles();
        documento.close();
    }

    private void agregarDetalles() throws DocumentException {
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell("ID");
        tabla.addCell("nombre");
        tabla.addCell("subtotal");
        List<DetalleCompra> detalles = compra.getDetalles();
        for(DetalleCompra detalle: detalles){
            Computadora computadora = detalle.getComputadora();
            tabla.addCell(computadora.getIdComputadora() + "");
            tabla.addCell(computadora.getTipo().getNombre());
            tabla.addCell(detalle.getSubtotal() + "");
        }
        documento.add(tabla);
    }
    
}
