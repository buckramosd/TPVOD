package BD;

import entidades.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionProductoBD {

    private GestionBD bd = new GestionBD("localhost", "root", "", "empresa", 3306);
    private Connection conexion;
    
    // GestionProducto -> Añadir producto, modificar stock, actualizar producto
    /*
    public boolean insertarProducto(Producto prod) {
        boolean resultado = false;
        try {
            bd.conectar();
            Statement sentencia = conexion.createStatement();
            String sql = String.format("INSERT INTO productos(Nombre, Apellidos, codDepartamento, Salario, Email) VALUES ('%s', '%s', '%s', '%s', '%s')",
                    prod.getNombre(), prod.getApellidos(), emp.getDpto().getIdDepartamento(), emp.getSalario(), emp.getEmail());
            System.out.println("Consulta SQL: " + sql);
            resultadoInsertar = sentencia.execute(sql);
            sentencia.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en conexión(Insertar empleado)" + ex.getMessage());
        }

        return resultado;
    }
    */
}
