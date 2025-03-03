/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ensamblador;

import com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComponente;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComponenteCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.io.IOException;
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
@WebServlet(name = "ServletGestionTipoComponente", urlPatterns = {"/controllers/ensamblador/gestion_tipo_componente"})
public class ServletGestionTipoComponente extends HttpServlet {

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
            TipoComponenteCRUD repositorio = new TipoComponenteCRUD();
            TipoComponente tipo = repositorio.obtenerEntidad(request);
            request.setAttribute("tipo", tipo);
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            request.getRequestDispatcher("/vista_ensamblador/tipo_componente_vista.jsp").
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
            TipoComponenteCRUD update = new TipoComponenteCRUD();
            TipoComponente tipo = update.actualizarEntidad(request);
            request.setAttribute("tipo", tipo);
            request.setAttribute("exito", "tipo de componente actualizado");
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            request.getRequestDispatcher("/vista_ensamblador/tipo_componente_vista.jsp").
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
