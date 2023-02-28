
package tpvdi;

import BD.*;
import entidades.*;
import java.io.File;


public class TPVDI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestionUsuarioBD conUser = new GestionUsuarioBD("localhost", "root", "", "tpv", 3306);
        GestionProductoBD conProduct = new GestionProductoBD("localhost", "root", "", "tpv", 3306);
        GestionVentasBD conVentas = new GestionVentasBD("localhost", "root", "", "tpv", 3306);
        Usuario user = new Usuario("jarvan", "pormipadreelrey", "admin", "Jarvan", "IV");
        Producto prod = new Producto(2, "cocacola", 3.09, 7);
        Producto prod2 = new Producto(3, "fanta naranja", 2.78, 10);
        //Venta venta = new Venta(1, 2, 3, prod, conUser.buscarUsuario("xin"));
        //Venta venta2 = new Venta(1, 2, 4, prod2, conUser.buscarUsuario("xin"));
        File f = new File("C:\\Users\\Oscar\\Desktop\\TPVOD\\productos\\cocacola-logo.png");
        File f2 = new File("C:\\Users\\Oscar\\Desktop\\TPVOD\\productos\\fanta-logo.png");

        //conUser.insertarUsuario(user);
        //conProduct.insertarProducto(prod);
        //conProduct.insertarProducto(prod2);
        conProduct.updatearFotoProducto(prod.getIdProducto(), f);
        conProduct.updatearFotoProducto(prod2.getIdProducto(), f2);
        //conVentas.insertarVenta(venta);
        //conVentas.insertarVenta(venta2);
        //ventas = conVentas.listarCiertasVentas(venta.getCodVenta());
        //for(int i=0;i<ventas.size();i++){
        //    System.out.println(ventas.getVenta(i));
        //}
    }

}
