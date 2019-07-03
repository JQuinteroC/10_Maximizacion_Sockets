/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloCliente;
import vista.GUI;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ControladorCliente implements ActionListener {

    GUI vista;
    //IVista vista;
    ModeloCliente modelo;

    public ControladorCliente(GUI vista, ModeloCliente modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void arrancar() {
        vista.hacerVisible();
        vista.inicializar();
        vista.agnadirMensajeATrasiego("conectando con el Servidor...");
        modelo.conectarConElServidor();
        vista.agnadirMensajeATrasiego("Conectado al servidor.");
        modelo.crearFlujos();
        modelo.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnCrearCampos){
            vista.spVariables.setBounds(10, 50, 100, 300);
            vista.getContentPane().add(vista.spVariables);
        } else if(e.getSource() == vista.btnMostrarResultados){
            
        }
       
    }

    public void agnadirMensajeATrasiego(String mensaje) {
        vista.agnadirMensajeATrasiego(mensaje);
    }
}
