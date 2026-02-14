/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionproductos;

import controlador.ControlProducto;
import modelo.Producto;
import vista.VistaGestor;
import vista.VistaNeto;
import vista.VistaParcial;

/**
 *
 * @author julia
 */
public class GestionProductos {

    public static void main(String[] args) {
        
        VistaGestor vista = new VistaGestor();
        VistaParcial vista2 = new VistaParcial();
        VistaNeto vista3 = new VistaNeto();
        Producto modelo = new Producto();
        ControlProducto control = new ControlProducto(vista, vista2, vista3, modelo);
        vista.setVisible(true);
        vista.error.setVisible(false);
    }
}
