/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ensamblador;

import com.mycompany.proyecto1ipc2.daos.ensamblador.TipoComputadoraDAO;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComputadoraCRUD;
import com.mycompany.proyecto1ipc2.ensamblaje.TipoComputadoraLista;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
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
@WebServlet(name = "ServletTipoComputadora", urlPatterns = {"/controllers/ensamblador/tipo_computadora"})
public class ServletTipoComputadora extends HttpServlet {

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
        TipoComputadoraLista lista = new TipoComputadoraLista();
        String direccion = lista.obtenerDireccion(request);
        TipoComputadoraDAO computadora = new TipoComputadoraDAO();
        request.setAttribute("tiposComputadoras", computadora.obtenerTodo());
        request.getRequestDispatcher(direccion).
                forward(request, response);
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
            TipoComputadoraCRUD repositorio = new TipoComputadoraCRUD();
            repositorio.crearEntidad(request);
            request.setAttribute("exito", "computadora agregada con exito");
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
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
