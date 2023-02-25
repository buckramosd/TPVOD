
package entidades;

import java.util.ArrayList;

public class Ventas {
    private ArrayList<Venta> listaVentas;

    public Ventas() {
        listaVentas = new ArrayList();
    }
    
    public void addVenta(Venta venta){
        listaVentas.add(venta);
    }
    
    public Venta getVenta(int idVenta) {
        return listaVentas.get(idVenta);
    }
    
    public boolean removeVenta(int idVenta) {
        return listaVentas.remove(this.getVenta(idVenta));
    }
    
    public int size() {
        return listaVentas.size();
    }
}
