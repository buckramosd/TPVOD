/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tpvdi;

import BD.GestionBD;
import BD.GestionUsuarioBD;
import entidades.Usuario;

/**
 *
 * @author damm
 */
public class TPVDI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestionBD conexion = new GestionBD("localhost", "root", "", "tpv", 3306);
        GestionUsuarioBD conUser = new GestionUsuarioBD("localhost", "root", "", "tpv", 3306);
        Usuario user = new Usuario("prueba", "prueba", "admin", "prueba", "prueba");
        conUser.insertarUsuario(user);
    }
    
}
