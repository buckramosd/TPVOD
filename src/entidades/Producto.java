/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.swing.ImageIcon;

/**
 *
 * @author damm
 */
public class Producto {
    
    private int idProducto;
    
    private String nombre;
    
    private Double pvp;
    
    private int stock;
    
    private ImageIcon imagen;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, Double pvp, int stock, ImageIcon imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.pvp = pvp;
        this.stock = stock;
        this.imagen = imagen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", pvp=" + pvp + ", stock=" + stock + ", imagen=" + imagen + '}';
    }
}
