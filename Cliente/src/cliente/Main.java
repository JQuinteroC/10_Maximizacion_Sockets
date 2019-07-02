package cliente;

import controlador.ControladorCliente;
import modelo.ModeloCliente;
import vista.IVista;
import vista.GUI;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IVista vista = new GUI();
        ModeloCliente modelo = new ModeloCliente();
        
        ControladorCliente controlador = new ControladorCliente(vista, modelo);
        
        vista.setControlador(controlador);
        modelo.setControlador(controlador);
        
        controlador.arrancar();
    }
    
}
