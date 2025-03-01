/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ensamblador;

import com.mycompany.proyecto1ipc2.daos.ComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.ensamblaje.ComponenteCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
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
@WebServlet(name = "ServletComponente", urlPatterns = {"/controllers/ensamblador/componente"})
public class ServletComponente extends HttpServlet {

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
        TipoComponenteDAO tipo = new TipoComponenteDAO();
        ComponenteDAO componente = new ComponenteDAO();
        request.setAttribute("tipos", tipo.obtenerTodo());
        request.setAttribute("componentes", componente.obtenerTodo());
        request.getRequestDispatcher("/vista_ensamblador/lista_componentes.jsp")
                .forward(request, response);}

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
            ComponenteCRUD creador = new ComponenteCRUD();
            creador.crearEntidad(request);
            request.setAttribute("exito", "componente creado con exito");
        } catch (InvalidDataException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            TipoComponenteDAO tipo = new TipoComponenteDAO();
            ComponenteDAO componente = new ComponenteDAO();
            request.setAttribute("tipos", tipo.obtenerTodo());
            request.setAttribute("componentes", componente.obtenerTodo());
            request.getRequestDispatcher("/vista_ensamblador/lista_componentes.jsp")
                    .forward(request, response);
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
