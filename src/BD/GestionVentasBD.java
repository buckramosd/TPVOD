package BD;

import entidades.Producto;
import entidades.Usuario;
import entidades.Venta;
import entidades.Ventas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private final GestionProductoBD conProduct = new GestionProductoBD("localhost", "root", "", "tpv", 3306);

    public GestionVentasBD(String HOST, String USUARIO, String PASSWORD, String BD, int PUERTO) {
        this.HOST = HOST;
        this.USUARIO = USUARIO;
        this.PASSWORD = PASSWORD;
        this.BD = BD;
        this.PUERTO = PUERTO;
    }

    /**
     * Método para insertar una nueva venta en la base de datos. Hay control de
     * que la cantidad no exceda el stock que tenemos de ese producto en el
     * "almacén".
     *
     * @param venta
     * @return
     */
    public boolean insertarVenta(Venta venta) {
        boolean resultado = false;
        // Creo objeto producto con los datos de la venta
        Producto prod = conProduct.buscarProducto(venta.getProducto().getIdProducto());
        // Si la cantidad a vender del producto es mayor que su stock
        if (venta.getCantidad() > prod.getStock()) {
            // establezco que la cantidad a vender sea ese stock, para no excederlo
            venta.setCantidad(prod.getStock());
        }
        // Con este método cambio el stock del producto en la tabla producto(BD), con una serie de condiciones expuestas en la documentación
        // del método
        // La ejecución de este método viene después de establecer la cantidad para que no haya incongluencias en la base de datos
        conProduct.modificarStockProducto(venta.getCantidad(), venta.getProducto().getIdProducto());
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO ventas(codVenta, cantidad, idProducto, username) VALUES ('%s', '%s','%s', '%s')",
                    venta.getCodVenta(), venta.getCantidad(), venta.getProducto().getIdProducto(), venta.getUsuario().getNombreUsuario());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar venta)" + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método para buscar una venta por su id
     *
     * @param venta
     * @return
     */
    public Venta buscarVenta(Venta venta) {
        Venta ventaBuscada = null;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT ventas.* FROM ((ventas "
                    + "INNER JOIN productos ON ventas.idProducto = productos.idProducto)"
                    + "INNER JOIN usuarios ON ventas.username = usuarios.username)"
                    + "WHERE idVenta='%s'",
                    venta.getIdVenta());
            System.out.println("Consulta SQL: " + sql);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                ventaBuscada = new Venta(rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        new Producto(rs.getInt(5), rs.getString(6), rs.getDouble(7), rs.getInt(8)),
                        new Usuario(rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)
                        ));
                return ventaBuscada;
            }
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Buscar producto)" + ex.getMessage());
        }

        return ventaBuscada;
    }

    /**
     *
     * @param venta
     * @param venta_new
     * @return
     */
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
        } catch (SQLException ex) {
            System.out.println("Error en conexión (Actualizar venta)" + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método para modificar cantidades a un pedido/venta.
     *
     * @param venta
     * @param cantidad
     * @return
     */
    public boolean modificarCantidadVenta(Venta venta, int cantidad) {
        boolean resultado = false;
        try {
            conectar();
            String sql = String.format("UPDATE ventas SET cantidad=? WHERE idVenta=? AND codVenta=?");
            PreparedStatement pstmt = conexion.prepareStatement(sql);

            Venta buscada = buscarVenta(venta);
            int cantidadInicial = buscada.getCantidad();
            int cantidadTotal = cantidadInicial + cantidad;

            System.out.println("Consulta SQL: " + sql);
            pstmt.setInt(1, cantidadTotal);
            pstmt.setInt(2, venta.getIdVenta());
            pstmt.setInt(3, venta.getCodVenta());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en conexión (Actualizar venta)" + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método que devuelve una lista de tipo venta con todas las ventas de la
     * base de datos.
     *
     * @return
     */
    public Ventas listarTodasVentas() {
        Ventas listadoCompleto = new Ventas();
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM ((ventas "
                    + "INNER JOIN productos ON ventas.idProducto = productos.idProducto)"
                    + "INNER JOIN usuarios ON ventas.username = usuarios.username)");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                listadoCompleto.addVenta(new Venta(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        new Producto(rs.getInt(4), rs.getString(7), rs.getDouble(8), rs.getInt(9)),
                        new Usuario(rs.getString(5), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)
                        )));
            }
            rs.close();
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error conexión" + ex.getMessage());
        }
        return listadoCompleto;
    }

    /**
     * Método que devuelve una lista de tipo venta con todas las ventas con
     * cierto codVenta de la base de datos.
     *
     * @param codVenta
     * @return lista
     */
    public Ventas listarCiertasVentas(int codVenta) {
        Ventas listadoCod = new Ventas();
        ResultSet rs;
        conectar();
        try {
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM ((ventas "
                    + "INNER JOIN productos ON ventas.idProducto = productos.idProducto)"
                    + "INNER JOIN usuarios ON ventas.username = usuarios.username)"
                    + "WHERE codVenta='%s'", codVenta);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                listadoCod.addVenta(new Venta(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        new Producto(rs.getInt(4), rs.getString(7), rs.getDouble(8), rs.getInt(9)),
                        new Usuario(rs.getString(5), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)
                        )));
            }
            rs.close();
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error conexión" + ex.getMessage());
        }

        return listadoCod;
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
