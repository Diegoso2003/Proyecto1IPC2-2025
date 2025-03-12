/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.financiero;

import com.mycompany.proyecto1ipc2.Reporte;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.financiero.reportes.ReporteDevolucion;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
@WebServlet(name = "ServletReporteDevolucion", urlPatterns = {"/controllers/financiero/reporte_devolucion"})
public class ServletReporteDevolucion extends HttpServlet {

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
        try {
            String fileName = "reporte_devoluciones.csv";
            
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            
            PrintWriter out = response.getWriter();
            ReporteDevolucion reporte = new ReporteDevolucion();
            Reporte archivo = new Reporte(reporte);
            archivo.obtenerDatosConsulta(request);
            List<String> texto = reporte.exportarContenido();
            for(String fila: texto){
                out.println(fila);
            }
            out.close();
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("/vista_financiera/form_reporte_devoluciones.jsp"). 
                    forward(request, response);
        }
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
            ReporteDevolucion devolucion = new ReporteDevolucion();
            Reporte reporte = new Reporte(devolucion);
            reporte.obtenerDatosConsulta(request);
            request.setAttribute("reporte", devolucion);
            request.getRequestDispatcher("/vista_financiera/reporte_devoluciones.jsp"). 
                    forward(request, response);
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("/vista_financiera/form_reporte_devoluciones.jsp"). 
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
