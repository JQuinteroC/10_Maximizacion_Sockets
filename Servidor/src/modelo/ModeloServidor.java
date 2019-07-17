package modelo;

import controlador.Calcular;
import java.util.ArrayList;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ModeloServidor extends Thread{
    
    public double[] xdd;
    public double[] xdd2;
    public int inf;
    
    public ModeloServidor() {

    }

    public String Devolver(ArrayList miau) {
        Calcular cal = new Calcular();
        String f=cal.PuntosRegionFactible(miau);
        
        xdd=cal.getPuntosX();
        xdd2=cal.getPuntosY();
        inf=cal.AreaInfinita;
        
        for (int i = 0; i < xdd.length; i++) {
            System.out.print(xdd[i]+" ");
            System.out.println(xdd2[i]+" miau ");
        }
        
        return f;
    }
}
