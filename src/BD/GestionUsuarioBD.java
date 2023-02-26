
package BD;

import entidades.Usuario;
import entidades.Usuarios;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestionUsuarioBD {
    
    private GestionBD bd = new GestionBD("localhost", "root", "", "empresa", 3306);
    private Connection conexion;
    
    /**
     * Método para insertar un nuevo usuario en la base de datos.
     * @param prod
     * @return 
     */
    public boolean insertarUsuario(Usuario user){
        boolean resultado = false;       
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO usuarios(username, password, rol, nombre, apellidos) VALUES ('%s', '%s', '%s','%s', '%s')",
                    user.getNombreUsuario(), user.getContraseña(), user.getRol(), user.getNombre(),user.getApellidos());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar empleado)" + ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Método para buscar un usuario en la base de datos.
     * @param nombreUsuario
     * @return producto.
     */
    public Usuario buscarUsuario(String nombreUsuario) {
        Usuario usuarioBuscado = null;
        ResultSet rs;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM usuarios WHERE username ='%s'",
                    nombreUsuario);
            System.out.println("Consulta SQL: " + sql);
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                usuarioBuscado = new Usuario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                return usuarioBuscado;
            }
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Buscar departamento)" + ex.getMessage());
        }
        return usuarioBuscado;
    }
    
    /**
     * Método para cambiar el rol de un usuario.
     * @param nombreUsuario
     * @param rol
     * @return 
     */
    public boolean modificarRol(String nombreUsuario, String rol) {
        boolean resultado = false;
        ResultSet rs;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE usuarios SET rol='%s' WHERE username =%s",
                    rol, nombreUsuario);
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Actualizar departamento)" + ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Método que devuelve una lista de tipo Usuario con todos los usuarios de la base de datos.
     * @return 
     */
    public Usuarios listarUsuarios(){
        Usuarios listado = new Usuarios();
        Usuario usuarioA = null;
        ResultSet rs;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM usuarios");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while(rs.next()){
                usuarioA = buscarUsuario(rs.getString(1));
                listado.addUsuario(usuarioA);
            }
            rs.close();
            sentencia.close();
            bd.desconectar();
        } catch(SQLException ex){
            System.out.println("Error conexión" + ex.getMessage());
        }
        return listado;
    }
}
