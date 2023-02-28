package tpvdi;

import BD.*;
import java.sql.Connection;
import entidades.*;
import java.io.File;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

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

        //Venta venta = new Venta(1, 2, 3, prod, conUser.buscarUsuario("xin"));
        //Venta venta2 = new Venta(1, 2, 4, prod2, conUser.buscarUsuario("xin"));
        //File f = new File("C:\\Users\\Oscar\\Desktop\\TPVOD\\productos\\cocacola-logo.png");
        //File f2 = new File("C:\\Users\\Oscar\\Desktop\\TPVOD\\productos\\fanta-logo.png");


        //conUser.insertarUsuario(user);
        //conProduct.insertarProducto(prod);
        //conProduct.insertarProducto(prod2);
        //conProduct.updatearFotoProducto(prod.getIdProducto(), f);
        //conProduct.updatearFotoProducto(prod2.getIdProducto(), f2);
        //conVentas.insertarVenta(venta);
        //conVentas.insertarVenta(venta2);
        //ventas = conVentas.listarCiertasVentas(venta.getCodVenta());
        //for(int i=0;i<ventas.size();i++){
        //    System.out.println(ventas.getVenta(i));
        //}
        
        /*
        //Impresión del informe
        JasperPrint jp = null;
        //El visor del informe
        JRViewer viewer;
        //Ruta de la plantilla del reporte
        String ficheroReporte
                = "reportes\\ListadoVentas.jrxml";
        //Parametros Map
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        //conexión con la BD
        Connection conexion;
        try {
        // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        //Establecemos conexion con la BD
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/Empresa?serverTimezone = UTC", "desarrollo", "desarrollo");
        //Compilamos el fichero de reporte
        JasperReport reporteCompilado = JasperCompileManager.compileReport(ficheroReporte);
        //Establecemos los parametros del reporte
            parameterMap.put("sueldoDesde", 2000);
            parameterMap.put("sueldoHasta", 3000);
        //Rellenamos el reporte con los parametros,conexion and the stream reader 
        jp = JasperFillManager.fillReport(reporteCompilado, parameterMap,conexion);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        viewer = new JRViewer(jp);
        this.PanelReporte.add(viewer);
        this.PanelReporte.revalidate();
        */
    }

}
