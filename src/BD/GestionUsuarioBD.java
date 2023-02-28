
package BD;

import entidades.Usuario;
import entidades.Usuarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestionUsuarioBD {
    
    private final String HOST;
    private final String USUARIO;
    private final String PASSWORD;
    private final String BD;
    private final int PUERTO;
    private Connection conexion;

    public GestionUsuarioBD(String HOST, String USUARIO, String PASSWORD, String BD, int PUERTO) {
        this.HOST = HOST;
        this.USUARIO = USUARIO;
        this.PASSWORD = PASSWORD;
        this.BD = BD;
        this.PUERTO = PUERTO;
    }
    
    /**
     * Método para insertar un nuevo usuario en la base de datos.
     * @param usuario
     * @return 
     */
    public boolean insertarUsuario(Usuario usuario){
        boolean resultado = false;       
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO usuarios(username, password, rol, nombre, apellidos) VALUES ('%s', '%s', '%s','%s', '%s')",
                    usuario.getNombreUsuario(), usuario.getContraseña(), usuario.getRol(), usuario.getNombre(),usuario.getApellidos());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar usuario)" + ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Método para buscar un usuario en la base de datos.
     * @param nombreUsuario
     * @return producto.
     */
    public Usuario buscarUsuario(String nombreUsuario, String password) {
        Usuario usuarioBuscado = null;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM usuarios WHERE username ='%s' AND password='%s'",
                    nombreUsuario, password);
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
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Buscar usuario)" + ex.getMessage());
        }
        return usuarioBuscado;
    }
    
    /**
     * Método para cambiar el rol de un usuario.
     * @param usuario
     * @return 
     */
    public boolean modificarRol(Usuario usuario) {
        boolean resultado = false;
        ResultSet rs;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE usuarios SET rol='%s' WHERE username ='%s'",
                    usuario.getRol(), usuario.getNombreUsuario());
            System.out.println("Consulta SQL: " + sql);
            resultado = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Actualizar rol usuario)" + ex.getMessage());
        }
        return resultado;
    }
    
    /**
     * Método para modificar campos de usuario.
     * @param user
     * @param user_new
     * @return 
     */
    public boolean modificarUsuario(Usuario user, Usuario user_new) {
        boolean resultadoModificar = false;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("UPDATE usuarios SET password='%s',rol='%s',nombre='%s',apellidos='%s' WHERE username='%s'",
                                        user_new.getContraseña(), user_new.getRol(), user_new.getNombre(),user_new.getApellidos(), user.getNombreUsuario());
            System.out.println("Consulta SQL: " + sql);
            resultadoModificar = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Actualizar empleado)" + ex.getMessage());
        }
        
        return resultadoModificar;
    }
    
    /**
     * Borra usuario de la base de datos, por si hubiera un despido.
     * @param usuario
     * @return 
     */
    public boolean borrarUsuario(Usuario usuario) {
        boolean resultadoBorrar = false;
        try {
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("DELETE FROM usuarios WHERE username ='%s'", 
                    usuario.getNombreUsuario());
            System.out.println("Consulta SQL: " + sql);
            resultadoBorrar = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Borrar usuario)" + ex.getMessage());
        }
        
        return resultadoBorrar;
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
            conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM usuarios");
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while(rs.next()){
                usuarioA = buscarUsuario(rs.getString(1), rs.getString(2));
                listado.addUsuario(usuarioA);
            }
            rs.close();
            sentencia.close();
            desconectar();
        } catch(SQLException ex){
            System.out.println("Error conexión" + ex.getMessage());
        }
        return listado;
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
