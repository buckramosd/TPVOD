package BD;

import entidades.Producto;
import entidades.Productos;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GestionProductoBD {

    private final String HOST;
    private final String USUARIO;
    private final String PASSWORD;
    private final String BD;
    private final int PUERTO;
    private Connection conexion;

    public GestionProductoBD(String HOST, String USUARIO, String PASSWORD, String BD, int PUERTO) {
        this.HOST = HOST;
        this.USUARIO = USUARIO;
        this.PASSWORD = PASSWORD;
        this.BD = BD;
        this.PUERTO = PUERTO;
    }

    /**
     * Método para insertar un nuevo producto en la base de datos sin la foto.
     *
     * @param prod
     * @return
     */
    public boolean insertarProducto(Producto prod) {
        boolean resultado = false;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO productos(nombre, pvp, stock) VALUES ('%s', '%s', '%s')",
                    prod.getNombre(), prod.getPvp(), prod.getStock());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar producto)" + ex.getMessage());
        }
        return resultado;
    }
    
    public boolean borrarProducto(Producto prod) {
        boolean resultado = false;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("DELETE FROM productos WHERE idProducto ='%s'",
                    prod.getIdProducto());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Borrar producto)" + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método para updatear los productos con sus imágenes representativas.
     *
     * @param idProducto
     * @param f
     */
    public void updatearFotoProducto(int idProducto, File f) {
        PreparedStatement pstmt;
        String sql;

        try {
            conectar();
            sql = "UPDATE productos SET imagen = ? WHERE idProducto = ?";
            pstmt = conexion.prepareStatement(sql);
            // redimensionamos la imagen a 100X100
            pstmt.setBinaryStream(1, resizeFromFile(f, 100, 100), (int) f.length());
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();

            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error excepcion" + ex.toString());
        }
    }

    /**
     * Método para buscar un producto en la base de datos.
     *
     * @param idProducto
     * @return producto.
     */
    public Producto buscarProducto(int idProducto) {
        Producto productoBuscado = null;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM productos WHERE idProducto ='%s'",
                    idProducto);
            System.out.println("Consulta SQL: " + sql);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                ImageIcon foto = getFotoProducto(idProducto);
                productoBuscado = new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        foto
                );
                return productoBuscado;
            }
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Buscar producto)" + ex.getMessage());
        }

        return productoBuscado;
    }
  
    /**
     * Método para cambiar el stock de un producto restándole cierta cantidad.
     * Si la cantidad a restar es mayor que el stock, establecemos el stock a 0.
     *
     * @param cantidad a reducir el stock
     * @param idProducto
     * @return
     */
    public boolean modificarStockProducto(int cantidad, int idProducto) {
        boolean resultado = false;
        Producto productoModificar = buscarProducto(idProducto);
        int stockActual = productoModificar.getStock();
        if (cantidad > stockActual) {
            stockActual = 0;
        } else {
            stockActual -= cantidad;
        }
        productoModificar.setStock(stockActual);
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE productos SET stock='%s' WHERE idProducto ='%s'",
                    productoModificar.getStock(), idProducto);
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Actualizar stock)" + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Método que devuelve una lista de tipo Producto con todos los productos de
     * la base de datos.
     *
     * @return
     */
    public Productos listarProductos() {
        Productos listado = new Productos();
        Producto productoA = null;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM productos");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                productoA = buscarProducto(rs.getInt(1));
                listado.addProducto(productoA);
            }
            rs.close();
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error conexión" + ex.getMessage());
        }
        return listado;
    }

    public int obtenerUltimoId() {
        int id = 0;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT MAX(idProducto) as id FROM productos");
            System.out.println("Consulta SQL: " + sql);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error en conexión(Obtener ID)" + ex.getMessage());
        }
        return id;
    }

    public int obtenerIDProducto(Producto producto) {
        int id = 0;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT idProducto FROM productos WHERE nombre='%s'", producto.getNombre());
            System.out.println("Consulta SQL: " + sql);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error en conexión(Obtener ID)" + ex.getMessage());
        }
        return id;
    }
    
    /**
     * Método que permite obtener el ImageIcon del producto según su id.
     *
     * @param idProducto
     * @return
     */
    public ImageIcon getFotoProducto(int idProducto) {
        ImageIcon imgProducto = null;
        PreparedStatement pstmt;
        int blobLength;
        byte[] blobAsBytes;
        String sql;
        try {
            conectar();
            sql = "SELECT imagen FROM productos WHERE idProducto = " + idProducto + " ";
            pstmt = conexion.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                Blob blob = rs.getBlob("imagen");
                blobLength = (int) blob.length();
                blobAsBytes = blob.getBytes(1, blobLength);
                final BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                imgProducto = new ImageIcon(bufferedImage);
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Error en excepcion" + ex.toString());
        }
        return imgProducto;
    }

    /**
     * Método que permite obtener el ImageIcon del producto según su nombre.
     * @param nombre
     * @return 
     */
    public ImageIcon getFotoProducto(String nombre) {
        ImageIcon imgProducto = null;
        PreparedStatement pstmt;
        int blobLength;
        byte[] blobAsBytes;
        try {
            conectar();
            String sql = String.format("SELECT imagen FROM productos WHERE nombre ='%s'",
                    nombre);
            pstmt = conexion.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                Blob blob = rs.getBlob("imagen");
                blobLength = (int) blob.length();
                blobAsBytes = blob.getBytes(1, blobLength);
                final BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                imgProducto = new ImageIcon(bufferedImage);
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Error en excepcion" + ex.toString());
        }
        return imgProducto;
    }
    
    /**
     * Método para modificar el tamaño de la imagen (clase).
     *
     * @param f
     * @param newW
     * @param newH
     * @return
     */
    public static BufferedImage resizeToBufferedImage(File f, int newW, int newH) {
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(f);
        } catch (IOException e) {

        }
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();

        return bufim;
    }

    /**
     * Método dado en clase para modificar el tamaño del archivo que contiene la
     * imagen.
     *
     * @param f
     * @param newW
     * @param newH
     * @return
     */
    public InputStream resizeFromFile(File f, int newW, int newH) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        RenderedImage bufferedImage = resizeToBufferedImage(f, newW, newH);
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }
        return new ByteArrayInputStream(baos.toByteArray());
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
