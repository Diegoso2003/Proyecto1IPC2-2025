/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.ventas;

import com.mycompany.proyecto1ipc2.CRUD;
import com.mycompany.proyecto1ipc2.daos.ensamblador.ComputadoraDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.CompraDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.DetalleCompraDAO;
import com.mycompany.proyecto1ipc2.daos.ventas.DevolucionDAO;
import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.dtos.ventas.DetalleCompra;
import com.mycompany.proyecto1ipc2.dtos.ventas.Devolucion;
import com.mycompany.proyecto1ipc2.ensamblaje.ComputadoraCRUD;
import com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author rafael-cayax
 */
public class DevolucionCRUD extends CRUD<Devolucion>{

    private String facturaContenido;
    private DetalleCompra detalle;
    private DetalleCompraDAO repositorioDetalle;
    private int codigo;
    
    public DevolucionCRUD() {
        super("devolucion", new DevolucionDAO());
    }

    /**
     * metodo para validar cada uno de los datos solicitados como el documento 
     * la fecha y el id de la computadora hayan sido ingresados correctamente
     * @param request
     * @throws InvalidDataException
     * @throws NotFoundException 
     */
    @Override
    protected void obtenerYValidarDatos(HttpServletRequest request) throws InvalidDataException, NotFoundException {
        try {
            detalle = new DetalleCompra();
            entidad = new Devolucion();
            Part filePart = request.getPart("factura");
            File tempFile = File.createTempFile("temp-", ".pdf");
            filePart.write(tempFile.getAbsolutePath());
            facturaContenido = extraerTextoDePDF(tempFile);
            LocalDate fecha = LocalDate.parse(request.getParameter("fechaDevolucion"));
            entidad.setFechaDevolucion(fecha);
            ComputadoraCRUD repositorio2 = new ComputadoraCRUD();
            Computadora computadora = repositorio2.obtenerEntidad(request);
            entidad.setComputadora(computadora);
            detalle.setComputadora(computadora);
            obtenerNumeroFactura();
            validarDevolucion();
            actualizarRepositorio();
            desensamblar();
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDataException("ingresar valores validos");
        } catch (IOException | ServletException ex) {
            throw new InvalidDataException("ingrese un documento valido");
        } 
    }

    /**
     * metodo para obtener el numero de compra en la factura impresa
     */
    private void obtenerNumeroFactura() {
        ExtractorFactura factura = new ExtractorFactura();
        codigo = factura.obtenerCodigo(facturaContenido);
        Compra compra = new Compra();
        compra.setIdCompra(codigo);
        entidad.setCompra(compra);
        detalle.setCompra(compra);
    }
    
    /**
     * metodo para obtener el contenido del pdf de la factura
     * @param pdfFile
     * @return contenido de la factura
     * @throws IOException 
     */
    private String extraerTextoDePDF(File pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }
    
    /**
     * metodo que valida que la computadora no haya sido devuelta ya y valida que 
     * la computadora este dentro de los detalles de la factura
     * @throws InvalidDataException
     * @throws NotFoundException 
     */
    private void validarDevolucion() throws InvalidDataException, NotFoundException {
        repositorioDetalle = new DetalleCompraDAO();
        if (detalle.getComputadora().getEstado() == EnumEstadoCompu.DEVUELTA) {
            throw new InvalidDataException("La computadora con el ID: '" + detalle.getComputadora().getIdComputadora()
                    + "' ya ha sido devuelta. Por favor, verifique el ID e intente nuevamente.");
        }
        validarFecha();
        Optional<DetalleCompra> posibleDetalle = repositorioDetalle.encontrarPorID(detalle);
        DetalleCompra detalle2 = posibleDetalle.orElseThrow(() -> new 
        NotFoundException("No se encontró la computadora con el ID: '" + detalle.getComputadora().getIdComputadora()
                + "' en los detalles de la factura. Por favor, verifique el ID e intente nuevamente.\""));
        detalle.setSubtotal(detalle2.getSubtotal());
        entidad.setCostoVenta(detalle2.getSubtotal());
        detalle.getComputadora().setEstado(EnumEstadoCompu.DEVUELTA);
    }

    /**
     * metodo para verificar que no haya pasado mas de una semana desde la deolucion
     * @throws NotFoundException
     * @throws InvalidDataException 
     */
    private void validarFecha() throws NotFoundException, InvalidDataException {
        CompraDAO repositorioCompra = new CompraDAO();
        Optional<Compra> posibleCompra = repositorioCompra.obtenerFechaCompra(detalle.getCompra().getIdCompra());
        Compra compra = posibleCompra.orElseThrow(() -> new 
        NotFoundException("No se encontro una factura con el id '" + detalle.getCompra().getIdCompra() + "'"));
        long dias = ChronoUnit.DAYS.between(compra.getFechaCompra(), entidad.getFechaDevolucion());
        if (dias > 7) {
            throw new InvalidDataException("No se puedo realizar esta devolucion pues ha pasado la fecha limite de una semana"
                    + " para realizar una devolucion");
        }
    }

    public DetalleCompra getDetalle() {
        return detalle;
    }

    /**
     * metodo para eliminar el detalle de la factura y para actualizar el estado
     * de la computadora a devuelta
     * @throws InvalidDataException
     * @throws NotFoundException 
     */
    private void actualizarRepositorio() throws InvalidDataException, NotFoundException {
        ComputadoraDAO repositorioComputadora = new ComputadoraDAO();
        repositorioComputadora.actualizar(detalle.getComputadora());
        repositorioDetalle.eliminar(detalle);
    }

    private void desensamblar() throws InvalidDataException, NotFoundException {
        Desensamblador desensamblaje = new Desensamblador();
        desensamblaje.desensamblar(detalle.getComputadora());
    }
    
}
