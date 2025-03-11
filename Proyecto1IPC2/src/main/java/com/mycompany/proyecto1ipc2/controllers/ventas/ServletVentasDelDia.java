/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ventas;

import com.mycompany.proyecto1ipc2.Reporte;
import com.mycompany.proyecto1ipc2.daos.ventas.consulta.VentasDelDiaDAO;
import com.mycompany.proyecto1ipc2.dtos.ventas.Compra;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael-cayax
 */
@WebServlet(name = "ServletVentasDelDia", urlPatterns = {"/controllers/ventas/ventas_del_dia"})
public class ServletVentasDelDia extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VentasDelDiaDAO ventas = new VentasDelDiaDAO();
            Reporte reporte = new Reporte(ventas);
            reporte.obtenerDatosConsulta(request);
            request.setAttribute("compras", ventas);
            request.getRequestDispatcher("/vista_ventas/compras_del_dia.jsp"). 
                    forward(request, response);
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("/vista_ventas/form_compras_del_dia.jsp"). 
                    forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
