/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.financiero;

import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComputadoraCRUD;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author rafael-cayax
 */
@WebServlet(name = "ServletActualizacionCompu", urlPatterns = {"/controllers/financiero/actualizar_computadora"})
public class ServletActualizacionCompu extends HttpServlet {

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
            TipoComputadoraCRUD tipo = new TipoComputadoraCRUD();
            tipo.actualizarEntidad(request);
            request.setAttribute("computadora", tipo.obtenerEntidad(request));
            TipoComponenteDAO repositorio = new TipoComponenteDAO();
            request.setAttribute("componentes", repositorio.obtenerTodo());
            request.setAttribute("exito", "actualizado correctamente");
            request.getRequestDispatcher("/vista_financiera/gestion_compu.jsp"). 
                    forward(request, response);
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            TipoComputadoraDAO computadora = new TipoComputadoraDAO();
            request.setAttribute("tiposComputadoras", computadora.obtenerTodo());
            request.getRequestDispatcher("/vista_financiera/tipo_computadoras.jsp").
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
