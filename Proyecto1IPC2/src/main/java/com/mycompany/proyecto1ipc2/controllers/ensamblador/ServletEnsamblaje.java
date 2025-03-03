/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ensamblador;

import com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora;
import com.mycompany.proyecto1ipc2.ensamblaje.ComputadoraCRUD;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComputadoraCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author rafael-cayax
 */
@WebServlet(name = "ServletEnsamblaje", urlPatterns = {"/controllers/ensamblador/ensamblaje"})
public class ServletEnsamblaje extends HttpServlet {


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
            TipoComputadoraCRUD compu = new TipoComputadoraCRUD();
            request.setAttribute("tipo_computadora", compu.obtenerEntidad(request));
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            request.getRequestDispatcher("/vista_ensamblador/form_ensamblaje.jsp"). 
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
            ComputadoraCRUD computadora = new ComputadoraCRUD();
            computadora.crearEntidad(request);
            Computadora computadora2 = computadora.getEntidad();
            request.setAttribute("exito", "computadora creada con id: '" + computadora2.getIdComputadora() + "' y precio"
                    + " de ensamblaje de Q" + (Math.round(computadora2.getPrecioFabricacion() * 100.0) / 100.0));
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            request.getRequestDispatcher("/vista_ensamblador/form_ensamblaje.jsp"). 
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
