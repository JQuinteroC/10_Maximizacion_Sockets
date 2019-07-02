package modelo;

import controlador.ControladorCliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ModeloCliente extends Thread {
    ControladorCliente controlador;
    final int PUERTO = 40080;
    final String HOST = "localhost";
    Socket socket;
    BufferedReader br;
    BufferedWriter bw;
    
    public void setControlador(ControladorCliente controlador){
        this.controlador = controlador;
    }
    
    public void conectarConElServidor(){
        try {
            socket = new Socket(HOST, PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearFlujos(){
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
        } catch (IOException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarMensaje(String mensaje){
        try {
            bw.write(mensaje);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String recibirMensaje(){
        try {
            String mensaje = br.readLine();
            return mensaje;
        } catch (IOException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    @Override
    public void run(){
        while(true){
            String mensaje = recibirMensaje();
            //controlador.agnadirMensajeATrasiego("El Servidor dice: " + mensaje);
        }
    }
}
