/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto1ipc2.enums;

/**
 *
 * @author rafael-cayax
 */
public enum EnumRol {
    ENSAMBLADOR(3),
    VENDEDOR(2),
    ADMINISTRADOR(1);
    
    private final int codigo;
    
    private EnumRol(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
