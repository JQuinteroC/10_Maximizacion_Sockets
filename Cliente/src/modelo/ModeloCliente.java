package modelo;

import controlador.ControladorCliente;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ModeloCliente extends Thread {

    public Scanner sc = new Scanner(System.in);
    public final int PUERTO = 5050;
    public final String HOST = "localhost";

    public InputStream it;
    public InputStreamReader ent;
    public BufferedReader entrada;

    public OutputStream ot;
    public OutputStreamWriter s;
    public PrintWriter salida;

    public String dato, m, d;

    public ArrayList<String> c = new ArrayList<>();

    public String fichero;

    public DataInputStream input;
    public BufferedInputStream buffIn;
    public BufferedOutputStream buffOut;

    Socket client;

    public ModeloCliente() {
        try {
            //crea conexion con el server
            client = new Socket("localhost", PUERTO);
            it = client.getInputStream();
            ent = new InputStreamReader(it);
            entrada = new BufferedReader(ent);
            ot = client.getOutputStream();
            s = new OutputStreamWriter(ot);
            salida = new PrintWriter(s, true);
            System.out.println("Número, -1 = terminar");
        } catch (IOException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String Connect() throws InterruptedException {
        try {
            //recibe y envia datos
            for (int i = 0; i < c.size(); i++) {
                salida.println(c.get(i));
                m = entrada.readLine();
                System.err.println(m);
            }
            for (;;) {
                m = entrada.readLine();
                if (Objects.equals(m, "0")) {
                    break;
                }
                d = m;
            }
            //recibe la imagen del server
            GetImg();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ModeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public void GetImg() {
        byte[] imgByte;
        int in;
        try {
            imgByte = new byte[6000];
            buffIn = new BufferedInputStream(client.getInputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());

            fichero = dis.readUTF();
            fichero = fichero.substring(fichero.indexOf('\\') + 1, fichero.length());

            buffOut = new BufferedOutputStream(new FileOutputStream(fichero));
            while ((in = buffIn.read(imgByte)) != -1) {
                buffOut.write(imgByte, 0, in);
            }
            
            System.out.println("smile, is an order >:v");
            buffOut.close();
            dis.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public ArrayList<String> getC() {
        return c;
    }

    public void setC(ArrayList<String> c) {
        this.c = c;
    }
}
