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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GestionProductoBD {

    private GestionBD bd = new GestionBD("localhost", "root", "", "empresa", 3306);
    private Connection conexion;

    /**
     * insertar producto    DONE
     * updatear fotos       DONE 
     * buscar foto          DONE 
     * buscar producto      DONE
     * modificar stock      DONE
     * listar productos     
     */
    public boolean insertarProducto(Producto prod) {
        boolean resultado = false;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO productos(nombre, pvp, stock) VALUES ('%s', '%s', '%s')",
                    prod.getNombre(), prod.getPvp(), prod.getStock());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar empleado)" + ex.getMessage());
        }

        return resultado;
    }

    public void updatearFotoProducto(int idProducto, File f) {
        PreparedStatement pstmt;
        String sql;

        try {
            bd.conectar();
            sql = "UPDATE productos SET imagen = ? WHERE idProducto = ?";
            pstmt = conexion.prepareStatement(sql);
            // redimensionamos la imagen a 100X100
            pstmt.setBinaryStream(1, resizeFromFile(f, 100, 100), (int) f.length());
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();

            bd.desconectar();
        } catch (SQLException ex) {
            System.err.println("Error excepcion" + ex.toString());
        }
    }

    public Producto buscarProducto(int idProducto) {
        Producto productoBuscado = null;
        ResultSet rs;
        try {
            bd.conectar();
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
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Buscar departamento)" + ex.getMessage());
        }

        return productoBuscado;
    }

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
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE productos SET stock='%s' WHERE idProducto =%s",
                    productoModificar.getStock(), idProducto);
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Actualizar departamento)" + ex.getMessage());
        }

        return resultado;
    }
    
    public Productos listarProductos(){
        Productos listado = new Productos();
        Producto productoA = null;
        ResultSet rs;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM productos");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while(rs.next()){
                productoA = buscarProducto(rs.getInt(1));
                listado.addProducto(productoA);
            }
            rs.close();
            sentencia.close();
            bd.desconectar();
        } catch(SQLException ex){
            System.out.println("Error conexión" + ex.getMessage());
        }
        
        return listado;
    }

    public ImageIcon getFotoProducto(int idProducto) {
        ImageIcon imgProducto = null;
        PreparedStatement pstmt;
        int blobLength;
        byte[] blobAsBytes;
        String sql;
        try {
            bd.conectar();
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
}
