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
import com.mycompany.proyecto1ipc2.financiero.GestionIndicacion;
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
@WebServlet(name = "ServletGestionIndicacion", urlPatterns = {"/controllers/financiero/gestion_indicacion"})
public class ServletGestionIndicacion extends HttpServlet {

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
            GestionIndicacion eliminar = new GestionIndicacion();
            eliminar.eliminarIndicacion(request);
            request.setAttribute("exito", "indicacion eliminada correctamente");
            obtenerCompu(request, response);
        } catch (InvalidDataException | NotFoundException ex) {
            agregarError(request, response, ex.getMessage());
        }
    }

    private void obtenerCompu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InvalidDataException, NotFoundException {
        TipoComputadoraCRUD tipo = new TipoComputadoraCRUD();
        request.setAttribute("computadora", tipo.obtenerEntidad(request));
        TipoComponenteDAO repositorio = new TipoComponenteDAO();
        request.setAttribute("componentes", repositorio.obtenerTodo());
        request.getRequestDispatcher("/vista_financiera/gestion_compu.jsp").
                forward(request, response);
    }

    private void agregarError(HttpServletRequest request, HttpServletResponse response, String mensaje) 
            throws ServletException, IOException {
        request.setAttribute("mensaje", mensaje);
        TipoComputadoraDAO computadora = new TipoComputadoraDAO();
        request.setAttribute("tiposComputadoras", computadora.obtenerTodo());
        request.getRequestDispatcher("/vista_financiera/tipo_computadoras.jsp").
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
            GestionIndicacion gestion = new GestionIndicacion();
            gestion.actualizarIndicacion(request);
            request.setAttribute("exito", "indicacion actualizada correctamente");
            obtenerCompu(request, response);
        } catch (InvalidDataException | NotFoundException ex) {
            agregarError(request, response, ex.getMessage());
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
