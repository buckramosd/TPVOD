
package BD;

import entidades.Venta;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class GestionVentasBD {
    private GestionBD bd = new GestionBD("localhost", "root", "", "tpv", 3306);
    private Connection conexion;
    
    /**
     * Método para insertar una nueva venta en la base de datos.
     * @param venta
     * @return 
     */
    public boolean insertarVenta(Venta venta){
        boolean resultado = false;       
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO venta(codVenta, cantidad, idProducto, username) VALUES ('%s', '%s','%s', '%s')",
                   venta.getCodVenta(),venta.getCantidad(), venta.getProducto().getIdProducto(), venta.getUsuario().getNombreUsuario());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar empleado)" + ex.getMessage());
        }
        return resultado;
    }
    
    public boolean actualizarVenta(Venta venta, Venta venta_new) {
        boolean resultado = false;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE ventas SET codVenta='%s', cantidad='%s', idProducto='%s', username='%s' WHERE idVenta='%s'",
                    venta_new.getCodVenta(), venta_new.getCantidad(), venta_new.getProducto().getIdProducto(), venta_new.getUsuario().getNombreUsuario(),
                    venta.getIdVenta());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex){
            System.out.println("Error en conexión (Actualizar venta)" + ex.getMessage());
        }
        return resultado;
    }
    
    
}
