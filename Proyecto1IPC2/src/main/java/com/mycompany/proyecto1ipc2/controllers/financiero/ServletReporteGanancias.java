/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.financiero;

import com.mycompany.proyecto1ipc2.Reporte;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.financiero.reportes.ReporteGanancias;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael-cayax
 */
@WebServlet(name = "ServletReporteGanancias", urlPatterns = {"/controllers/financiero/reporte_ganancias"})
public class ServletReporteGanancias extends HttpServlet {

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
            ReporteGanancias reporteGanancias = new ReporteGanancias();
            Reporte reporte = new Reporte(reporteGanancias);
            reporte.obtenerDatosConsulta(request);
            request.setAttribute("reporte", reporteGanancias);
            request.getRequestDispatcher("/vista_financiera/reporte_ganancias.jsp"). 
                    forward(request, response);
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaja", ex.getMessage());
            request.getRequestDispatcher("/vista_financiera/form_reporte_ganancias.jsp"). 
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
