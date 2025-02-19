/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ipc2.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class Coneccion {

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/laComputadoraFeliz";
    private static final String USER = "root";
    private static final String PASSWORD = "Programacion";
    private Connection coneccion;

    public Coneccion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            coneccion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
    }

    public Connection getConeccion() {
        return coneccion;
    }

}
