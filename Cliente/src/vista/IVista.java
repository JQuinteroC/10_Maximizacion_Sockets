package vista;

import controlador.ControladorCliente;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public interface IVista {
    final String ENVIAR = "ENVIAR";
    
    public void habilitarEnviar();
    public void deshabilitarEnviar();
    public void agnadirMensajeATrasiego(String mensaje);
    public void borrarTextoAEnviar();
    public void setControlador(ControladorCliente controlador);
    public void hacerVisible();
    public void inicializar();
    public String getMensajeAEnviar();
}
