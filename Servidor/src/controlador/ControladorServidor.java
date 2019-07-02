package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloServidor;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ControladorServidor implements ActionListener{
    ModeloServidor modelo;

    public ControladorServidor(ModeloServidor modelo) {
        this.modelo = modelo;
    }
    public void arrancar(){
        modelo.abrirPuerto();        
        modelo.esperarAlCliente();
        modelo.crearFlujos();
        modelo.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       /* switch(e.getActionCommand()){
            case IVista.ENVIAR:
                vista.agnadirMensajeATrasiego("Enviando mensaje al cliente...");
                modelo.enviarMensaje(vista.getMensajeAEnviar());
                vista.agnadirMensajeATrasiego("Mensaje enviado.");
                vista.borrarTextoAEnviar();
                break;
        } */
    }
    
}
