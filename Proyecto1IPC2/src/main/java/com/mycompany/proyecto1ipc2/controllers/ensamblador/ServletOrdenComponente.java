/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ensamblador;

import com.mycompany.proyecto1ipc2.daos.ensamblador.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.ensamblaje.ComponenteCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
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
@WebServlet(name = "ServletOrdenComponente", urlPatterns = {"/controllers/ensamblador/orden_componente"})
public class ServletOrdenComponente extends HttpServlet {


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
            ComponenteCRUD componente = new ComponenteCRUD();
            request.setAttribute("componentes", componente.obtenerPorOrden(request));
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            ComponenteDAO componente = new ComponenteDAO();
            request.setAttribute("componentes", componente.obtenerTodo());
        } finally {
            TipoComponenteDAO tipos = new TipoComponenteDAO();
            request.setAttribute("tipos", tipos.obtenerTodo());
            request.getRequestDispatcher("/vista_ensamblador/lista_componentes.jsp"). 
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
