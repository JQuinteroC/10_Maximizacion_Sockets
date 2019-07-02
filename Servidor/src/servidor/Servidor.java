/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controlador.ControladorServidor;
import modelo.ModeloServidor;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Servidor {
    
    public static void main(String[] args) {
        ModeloServidor modelo = new ModeloServidor();
        
        ControladorServidor controlador = new ControladorServidor(modelo);
        
        modelo.setControlador(controlador);
        
        controlador.arrancar();
    }
}
