package controlador;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ModeloServidor;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ControladorServidor {

    public Scanner sc = new Scanner(System.in);

    public Socket cliente = null;
    public final int PUERTO1 = 5050;
    public ServerSocket ss;

    public InputStream it;
    public InputStreamReader e1;
    public BufferedReader entrada;
    public BufferedInputStream buffIn;

    public OutputStream ot;
    public OutputStreamWriter s;
    public PrintWriter salida;
    public DataOutputStream output;
    public BufferedOutputStream buffOut;

    public String mensage, aux;
    public double n;
    public int in;
    String xy;

    public ByteArrayOutputStream baos;
    public BufferedImage originalImage;

    public ArrayList<String> c = new ArrayList<>();
    ArrayList<double[]> xyx = new ArrayList<>();
    public byte[] imageInByte;

    File localFile;
    ModeloServidor metod;

    public ControladorServidor() {
        try {
            //establece conexion con el usuario
            ss = new ServerSocket(PUERTO1);
            cliente = ss.accept();
            it = cliente.getInputStream();
            e1 = new InputStreamReader(it);
            entrada = new BufferedReader(e1);
            ot = cliente.getOutputStream();
            s = new OutputStreamWriter(ot);
            salida = new PrintWriter(s, true);
            //recibe los datos de la parte grafica del usuario
            for (;;) {
                mensage = entrada.readLine();
                c.add(mensage);
                salida.println("1");
                if (Objects.equals(c.get(c.size() - 1), "0")) {
                    break;
                }
            }

            //
            Graficar gg = new Graficar();
            //quita el 0 restante del arraylist para que mateo no estalle
            c.remove(c.size() - 1);

            for (int i = 0; i < c.size(); i++) {
                System.out.println(c.get(i));
            }

            //parte logica de mateo que recibe mis datos bergas y se los manda a ferr
            metod = new ModeloServidor();
            String meow = metod.Devolver(c);
            xyx = SepararPuntos(meow);
            salida.println(meow);
            salida.println("0");

            gg.takePhoto(metod.inf,xyx.get(0), xyx.get(1),metod.xdd,metod.xdd2);
            //toma la imagen y la manda al user
            SendImg();
            entrada.close();
            salida.close();
            cliente.close();
            it.close();
            ss.close();

        } catch (IOException ex) {
            Logger.getLogger(ControladorServidor.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        }
    }

    public void SendImg() {
        localFile = new File("Grafica.jpg");
        try {
            buffIn = new BufferedInputStream(new FileInputStream(localFile));
            buffOut = new BufferedOutputStream(cliente.getOutputStream());

            DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
            dos.writeUTF(localFile.getName());
            imageInByte = new byte[6000];
            while ((in = buffIn.read(imageInByte)) != -1) {
                buffOut.write(imageInByte, 0, in);
            }

            buffIn.close();
            buffOut.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public ArrayList SepararPuntos(String cadena) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<double[]> xy = new ArrayList<>();
        String split1[] = cadena.split("::");
        String split2[] = split1[0].split(":");
        String split3[];

        for (int i = 1; i < split2.length; i++) {
            split3 = split2[i].split(",");
            x.add(Double.parseDouble(split3[0]));
            y.add(Double.parseDouble(split3[1]));
        }
        double[] xs = new double[x.size()];
        double[] ys = new double[y.size()];
        for (int i = 0; i < x.size(); i++) {
            xs[i] = x.get(i);
            ys[i] = y.get(i);
        }
        xy.add(xs);
        xy.add(ys);
        return xy;
    }
}
