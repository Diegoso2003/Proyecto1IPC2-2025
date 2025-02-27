/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.ensamblador;

import com.mycompany.proyecto1ipc2.daos.TipoComponenteDAO;
import com.mycompany.proyecto1ipc2.dtos.Componente;
import com.mycompany.proyecto1ipc2.ensamblaje.AdminComponente;
import com.mycompany.proyecto1ipc2.ensamblaje.CreadorComponente;
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
@WebServlet(name = "ServletGestionComponente", urlPatterns = {"/controllers/ensamblador/gestion_componente"})
public class ServletGestionComponente extends HttpServlet {

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
            AdminComponente gestor = new AdminComponente();
            Componente componente = gestor.obtenerDatosComponente(request);
            request.setAttribute("componente", componente);
            TipoComponenteDAO tipo = new TipoComponenteDAO();
            request.setAttribute("tipos", tipo.obtenerTodo());
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            request.getRequestDispatcher("/vista_ensamblador/componente_vista.jsp")
                    .forward(request, response);
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
        CreadorComponente actu = new CreadorComponente();
        try {
            actu.actualizarComponente(request);
            TipoComponenteDAO tipo = new TipoComponenteDAO();
            request.setAttribute("tipos", tipo.obtenerTodo());
            request.setAttribute("exito", "componente actualizado correctamente");
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally{
            request.getRequestDispatcher("/vista_ensamblador/componente_vista.jsp")
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
