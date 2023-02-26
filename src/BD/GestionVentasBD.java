
package BD;

import entidades.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class GestionVentasBD {
   private final String HOST;
    private final String USUARIO;
    private final String PASSWORD;
    private final String BD;
    private final int PUERTO;
    private Connection conexion;

    public GestionVentasBD(String HOST, String USUARIO, String PASSWORD, String BD, int PUERTO) {
        this.HOST = HOST;
        this.USUARIO = USUARIO;
        this.PASSWORD = PASSWORD;
        this.BD = BD;
        this.PUERTO = PUERTO;
    }
    
    /**
     * Método para insertar una nueva venta en la base de datos.
     * @param venta
     * @return 
     */
    public boolean insertarVenta(Venta venta){
        boolean resultado = false;       
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO venta(codVenta, cantidad, idProducto, username) VALUES ('%s', '%s','%s', '%s')",
                   venta.getCodVenta(),venta.getCantidad(), venta.getProducto().getIdProducto(), venta.getUsuario().getNombreUsuario());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar venta)" + ex.getMessage());
        }
        return resultado;
    }
    
    public boolean actualizarVenta(Venta venta, Venta venta_new) {
        boolean resultado = false;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE ventas SET codVenta='%s', cantidad='%s', idProducto='%s', username='%s' WHERE idVenta='%s'",
                    venta_new.getCodVenta(), venta_new.getCantidad(), venta_new.getProducto().getIdProducto(), venta_new.getUsuario().getNombreUsuario(),
                    venta.getIdVenta());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex){
            System.out.println("Error en conexión (Actualizar venta)" + ex.getMessage());
        }
        return resultado;
    }
    
    private boolean conectar() {
        boolean resultado = true;
        try {
            //Indicamos el driver a utilizar
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Inicializar la cadena de conexión
            conexion = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD + "?serverTimezone=UTC", USUARIO, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver MySQL " + ex.getMessage());
            resultado = false;
        } catch (SQLException ex) {
            System.out.println("Error de conexión " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    private boolean desconectar() {
        boolean resultado = true;
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al desconectar " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }
}
