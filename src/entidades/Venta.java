/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author damm
 */
public class Venta {
    private int idVenta;
    
    private int codVenta;
    
    private int cantidad;
    
    private Producto producto;
    
    private Usuario usuario;

    public Venta() {
    }

    public Venta(int idVenta, int codVenta, int cantidad, Producto producto, Usuario usuario) {
        this.idVenta = idVenta;
        this.codVenta = codVenta;
        this.cantidad = cantidad;
        this.producto = producto;
        this.usuario = usuario;
    }

    public Venta(Producto producto, Usuario usuario) {
        this.producto = producto;
        this.usuario = usuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", codVenta=" + codVenta + ", cantidad=" + cantidad + ", producto=" + producto + ", usuario=" + usuario + '}';
    }
    
    
}
