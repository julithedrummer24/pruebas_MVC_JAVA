/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author julia
 */
public class Producto {
    
    String nombre;
    int precio;
    int cantidad;
    
    private ArrayList<Producto> productos = new ArrayList<>();


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    public void addProducto(String nombre, int precio, int cantidad){
        
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        productos.add(p);
        
    }
    
    public boolean validar(){
        return productos.size() > 0;
    }
    
    public ArrayList llenar(){
        return new ArrayList<>(productos);
    }
    
    
    public int totalPorProducto(String nombre){
        int precio = 0;
        
        for (Producto producto : productos) {
            String nombreTemp = producto.nombre.toLowerCase();
            if(nombreTemp.contains(nombre.toLowerCase())){
                precio = (producto.precio*producto.cantidad);
                break;
            }
        }
        
        return precio;
    }
    
    
    public double totalProductos(){
        int precio = 0;
        
        for (Producto producto : productos) {
            precio += (producto.precio*producto.cantidad);
        }
        
        return precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", productos=" + productos + '}';
    }
    
    
}
