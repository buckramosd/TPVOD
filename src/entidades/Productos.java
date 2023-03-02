
package entidades;

import java.util.ArrayList;


public class Productos {
    private ArrayList<Producto> listaProductos;

    public Productos() {
        listaProductos = new ArrayList();
    }
    
    public void addProducto(Producto producto){
        listaProductos.add(producto);
    }
    
    public Producto getProducto(int idProducto) {
        return listaProductos.get(idProducto);
    }
    
    public Producto get(int index) {
        return listaProductos.get(index);
    }
    
    public boolean removeEmpleado(int idProducto) {
        return listaProductos.remove(this.getProducto(idProducto));
    }
    
    public int size() {
        return listaProductos.size();
    }
}
