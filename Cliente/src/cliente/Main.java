package cliente;

import controlador.ControladorCliente;
import modelo.ModeloCliente;
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
        GUI vista = new GUI();
        //IVista vista = new GUI();
        ModeloCliente modelo = new ModeloCliente();
        
        ControladorCliente controlador = new ControladorCliente(vista, modelo);
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista.getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        vista.mostrar();
        vista.setControlador(controlador);
        modelo.setControlador(controlador);
        
     //   controlador.arrancar();
    }
    
}
