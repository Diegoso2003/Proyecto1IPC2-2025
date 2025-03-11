/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1ipc2.controllers.usuario;

import com.mycompany.proyecto1ipc2.daos.UsuarioDAO;
import com.mycompany.proyecto1ipc2.exception.InvalidDataException;
import com.mycompany.proyecto1ipc2.exception.NotFoundException;
import com.mycompany.proyecto1ipc2.financiero.UsuarioCRUD;
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
@WebServlet(name = "ServletUsuario", urlPatterns = {"/controllers/financiero/usuario"})
public class ServletUsuario extends HttpServlet {

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
        UsuarioDAO usuario = new UsuarioDAO();
        request.setAttribute("usuarios", usuario.obtenerTodo());
        request.setAttribute("roles", usuario.obtenerRoles());
        request.getRequestDispatcher("/vista_financiera/gestion_usuarios.jsp").
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
            UsuarioCRUD usuario = new UsuarioCRUD();
            usuario.crearEntidad(request);
            request.setAttribute("exito", "usuario creado");
        } catch (InvalidDataException | NotFoundException ex) {
            request.setAttribute("mensaje", ex.getMessage());
        } finally {
            UsuarioDAO usuario = new UsuarioDAO();
            request.setAttribute("usuarios", usuario.obtenerTodo());
            request.setAttribute("roles", usuario.obtenerRoles());
            request.getRequestDispatcher("/vista_financiera/gestion_usuarios.jsp").
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
