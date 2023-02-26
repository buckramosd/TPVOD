
package BD;

import entidades.Venta;
import entidades.Ventas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    /*
    /**
     * Método que devuelve una lista de tipo venta con todas las ventas de la base de datos.
     * @return 
    
    public Ventas listarTodasVentas(){
        Ventas listado = new Ventas();
        Venta ventaA = null;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM ventas");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while(rs.next()){
                ventaA = buscarVenta(rs.getString(1));
                listado.addVenta(ventaA);
            }
            rs.close();
            sentencia.close();
            desconectar();
        } catch(SQLException ex){
            System.out.println("Error conexión" + ex.getMessage());
        }
        return listado;
    }
    
    /**
     * Método que devuelve una lista de tipo venta con todas las ventas con cierto codVenta de la base de datos.
     * @param codVenta
     * @return lista
    
    public Ventas listarCiertasVentas(int codVenta){
        Ventas listado = new Ventas();
        ResultSet rs;
        conectar();
        try {
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM ventas INNER JOIN departamentos ON departamentos.idDepartamento = empleados.codDepartamento");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while(rs.next()){
                listado.addEmpleado(new Empleado(rs.getInt(1), 
                                                 rs.getString(2), 
                                                 rs.getString(3),
                                                 new Departamento(rs.getInt(4), rs.getString(8)),
                                                 rs.getFloat(5),
                                                 rs.getString(6)));
            }
            rs.close();
            sentencia.close();
            desconectar();
        } catch(SQLException ex){
            System.out.println("Error conexión" + ex.getMessage());
        }
        
        return listado;
    }
    
    private Venta buscarVenta(int codVenta){
        Venta ventaBuscada = null;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM ventas WHERE codVenta ='%s'",
                    codVenta);
            System.out.println("Consulta SQL: " + sql);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                ventaBuscada = new Venta(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        
                );
                return ventaBuscada;
            }
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Buscar usuario)" + ex.getMessage());
        }
        return ventaBuscada;
    }
     */
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
