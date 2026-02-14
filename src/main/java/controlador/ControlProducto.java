/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Producto;
import vista.VistaGestor;
import vista.VistaNeto;
import vista.VistaParcial;

/**
 *
 * @author julia
 */
public class ControlProducto implements ActionListener{
    
    private VistaGestor vista;
    private VistaParcial parcial;
    private VistaNeto neto;
    private Producto modelo;
    
    private ArrayList<Producto> tempProductos;

    public ControlProducto(VistaGestor vista, VistaParcial parcial, VistaNeto neto,Producto modelo) {
        this.vista = vista;
        this.parcial = parcial;
        this.neto = neto;
        this.modelo = modelo;
        tempProductos =  new ArrayList<>();
        
        this.vista.nombre.addActionListener(this);
        this.vista.cantidad.addActionListener(this);
        this.vista.precio.addActionListener(this);
        this.vista.agregar.addActionListener(this);
        
        this.vista.xproducto.addActionListener(this);
        this.parcial.combopPro.addActionListener(this);  
        
        this.vista.btnNeto.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == vista.agregar){
            validarDatos();
        }
        
        if(e.getSource() == vista.xproducto){
            if(mostrarvista()){
                llenarCombo();
                this.parcial.setVisible(true);
            }else{
                vista.erorDePagina();
            }
        }
        
        if(e.getSource() == parcial.combopPro){
            buscarItems();
        }
        
        if(e.getSource() == vista.btnNeto){
            if(mostrarvista()){
                evaluarNeto();
                this.neto.setVisible(true);
            }else{
                vista.erorDePagina();
            }
        }        
    }

    private void validarDatos() {
        String nombre = this.vista.nombre.getText().toString().trim();
        String precio = this.vista.precio.getText().toString().trim();
        String cantidad = this.vista.cantidad.getText().trim();
        
        if(nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty()){
            vista.datosNulos();
        }else{
            if(!nombre.matches(".*[0-9].*")&& Integer.parseInt(precio) > 0 && Integer.parseInt(cantidad) > 0){
                
            modelo.addProducto(nombre, Integer.parseInt(precio), Integer.parseInt(cantidad));
            vista.nombre.setText("");
            vista.precio.setText("");
            vista.cantidad.setText("");
            vista.creacion();
            
        }else{
            vista.errorDatos();
        }
        }
    }
    
    
    /*
    parte de la otra ventana
    */

    private boolean mostrarvista() {
        
        boolean flag = false;
        if(modelo.validar()){
            flag = true;
        }
        return flag;
    }   

    private void llenarCombo() {
        parcial.combopPro.removeAllItems();
        
        tempProductos = modelo.llenar();
        for (Producto item : tempProductos) {
            this.parcial.combopPro.addItem(item.getNombre());
        }

    }
    
    private void buscarItems() {
        String nombre = (String) this.parcial.combopPro.getSelectedItem();
        
        for(Producto item : tempProductos){
            if(item.getNombre().equals(nombre)){
                
                int precio = item.getPrecio();
                int cantidad = item.getCantidad();
                int resultado = modelo.totalPorProducto(nombre);
                
                
                llenarcampos(nombre, precio, cantidad, resultado);
                break;
            }
        }
        
    }

    private void llenarcampos(String nombre, int precio, int cantidad, int resultado) {
        
        parcial.labelName.setText(nombre);
        parcial.labelPrice.setText(String.valueOf(precio));
        parcial.labelQuantiti.setText(String.valueOf(cantidad));
        parcial.total.setBackground(Color.red);
        parcial.total.setText(String.valueOf(resultado));
        
        
    }

    private void evaluarNeto() {
        tempProductos = modelo.llenar();
        int valneto = 0;
        int cantidad = tempProductos.size();
        
        for(Producto item : tempProductos){
            valneto += item.getPrecio() * item.getCantidad();
        }
        
        this.neto.labelNeto.setText(String.valueOf(cantidad));
        this.neto.precioNeto.setText(String.valueOf(valneto));
        this.neto.salida();
    }

    
}
