/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tpvdi;

import BD.*;
import entidades.*;

/**
 *
 * @author damm
 */
public class TPVDI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestionUsuarioBD conUser = new GestionUsuarioBD("localhost", "root", "", "tpv", 3306);
        GestionProductoBD conProduct = new GestionProductoBD("localhost", "root", "", "tpv", 3306);
        GestionVentasBD conVentas = new GestionVentasBD("localhost", "root", "", "tpv", 3306);
        Usuario user = new Usuario("usuarioPrueba", "contraseñaPrueba", "rolPrueba", "nombrePrueba", "apellidosPrueba");
        Producto prod = new Producto(1,"refrescoPrueba",2.99,10);
        Venta venta = new Venta(1,101,2,prod,user);
        Usuario chinaso = new Usuario("xin", "xan", "vendedor", "Xin-Zhao", "Yun");
        Ventas ventas;
        conUser.insertarUsuario(chinaso);
        //conProduct.insertarProducto(prod);
        //conVentas.insertarVenta(venta);
        //ventas = conVentas.listarCiertasVentas(venta.getCodVenta());
        //for(int i=0;i<ventas.size();i++){
        //    System.out.println(ventas.getVenta(i));
        //}
    }
    
}
