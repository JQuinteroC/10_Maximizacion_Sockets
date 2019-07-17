package controlador;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Calcular {
    
    public double[] puntosX;
    public double[] puntosY;
    public int AreaInfinita = 0;

    public double[] getPuntosX() {
        return puntosX;
    }

    public void setPuntosX(double[] puntosX) {
        this.puntosX = puntosX;
    }

    public double[] getPuntosY() {
        return puntosY;
    }

    public void setPuntosY(double[] puntosY) {
        this.puntosY = puntosY;
    }

    public String PuntosRegionFactible(ArrayList<String> todo) {
        //para guardar las mkadas
        ArrayList<Double> x = new ArrayList();//restricciones en X
        ArrayList<Double> y = new ArrayList();//restricciones en Y
        ArrayList<Double> mayor_menor = new ArrayList();//restricciones signo mayor o menor
        ArrayList<Double> igual = new ArrayList();//restricciones igualadas a--

        //aqui se guardan los puntos de las rectas (en la posicion 0 el punto 0,0)
        ArrayList<Double> puntoX = new ArrayList();
        ArrayList<Double> puntoY = new ArrayList();
        puntoX.add(0.0);
        puntoY.add(0.0);

        //aqui se guarda la ecuacion objetivo
        String[] objetivo = todo.get(0).split(",");

        //se guardan las ecuaciones
        for (int i = 1; i < todo.size(); i++) {
            String aux[] = todo.get(i).split(",");
            x.add(Double.parseDouble(aux[0]));
            y.add(Double.parseDouble(aux[1]));
            if (aux[2].equals("<")) {
                mayor_menor.add(0.0);
            } else if (aux[2].equals("<=")) {
                mayor_menor.add(1.0);
            } else if (aux[2].equals(">")) {
                mayor_menor.add(2.0);
            } else if (aux[2].equals(">=")) {
                mayor_menor.add(3.0);
            } else {
                System.out.println("faltan datos weee");
            }
            igual.add(Double.parseDouble(aux[3]));
        }

        int miau=0;
        for (int i = 0; i < mayor_menor.size(); i++) {
            if(mayor_menor.get(i)==3.0){
                miau++;
            }
        }
        
        if(miau==mayor_menor.size()){
            JOptionPane.showMessageDialog(null, "El area es infinita");
            AreaInfinita=1;
        }
        
        //obtengo los puntos
        for (int i = 0; i < x.size(); i++) {
            double aux[] = punto(x.get(i), y.get(i), igual.get(i));
            puntoX.add(aux[0]);
            puntoY.add(aux[1]);
            puntoX.add(aux[2]);
            puntoY.add(aux[3]);
        }

        //aqui guardo los puntos en un array bien melo
        puntosX = new double[puntoX.size() - 1];
        puntosY = new double[puntoY.size() - 1];
        int aux = 0;
        for (int i = 1; i < puntoX.size(); i++) {
            double xx = puntoX.get(i);
            double yy = puntoY.get(i);
            puntosX[aux] = xx;
            puntosY[aux] = yy;
            // JOptionPane.showMessageDialog(null, puntosX[aux]+" "+puntosY[aux]);
            aux++;
        }

        ArrayList<Double> cortes = Cortes(x, y, mayor_menor, igual);
        for (int i = 0; i < cortes.size(); i++) {

            if (i % 2 == 0) {
                puntoX.add(cortes.get(i));
            } else {
                puntoY.add(cortes.get(i));
            }
        }

        //funcion que rectifica los puntos de la región factible
        ArrayList<Double> region = Comprobar(x, y, mayor_menor, igual, puntoX, puntoY);

        //ultimo calculo :3 el maximo o el minimo
        ArrayList<String> maximizado = Max_min(objetivo[2], region, Double.parseDouble(objetivo[0]), Double.parseDouble(objetivo[1]));

        //toda esta mamada de aqui es para enviar las cosas
        String s = "";

        for (int i = 0; i < region.size(); i++) {
            if (i % 2 == 0) {
                s += ":" + region.get(i) + ",";
            } else {
                s += +region.get(i);
            }
        }

        for (int i = 0; i < maximizado.size(); i++) {
            if (i == 0) {
                s += ":";
            }
            s += ":" + maximizado.get(i);
        }

        System.err.println(s);
        return s;

    }

    public double[] punto(double x, double y, double igual) {
        double[] retorno = new double[4];

        //calculo el putos
        if (x == 0) {
            retorno[0] = 0;
            retorno[1] = igual / y;
            retorno[2] = 100; //tiende a infinito
            retorno[3] = igual / y;
        } else if (y == 0) {
            retorno[0] = igual / x;
            retorno[1] = 0;
            retorno[2] = igual / x;
            retorno[3] = 100; //tiende a infinito
        } else if (igual == 0) {

            if ((x < 0 && y < 0) || (x > 0 && y > 0)) {
                //si ambos tienen el mismo signo esa grafica vale pito
            } else if (x < 0) {
                retorno[0] = 0;
                retorno[1] = 0;
                retorno[2] = 50;
                retorno[3] = ((-x) / y) * 50;
            } else if (y < 0) {
                retorno[0] = 0;
                retorno[1] = 0;
                retorno[2] = 50;
                retorno[3] = (x / (-y)) * 50;
            }

        } else {
            retorno[0] = igual / x;
            retorno[1] = 0;
            retorno[2] = 0;
            retorno[3] = igual / y;
        }

        return retorno;
    }

    //caculo los puntos de corte de cada recta, y con ellos  los vertices del area esa mamona
    public ArrayList<Double> Cortes(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> mayor_menor, ArrayList<Double> igual) {

        ArrayList<Double> cortes = new ArrayList();

        double[][] matriz = new double[2][3];

        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < y.size(); j++) {
                if (j != i && j > i) {
                    matriz[0][0] = x.get(i);
                    matriz[0][1] = y.get(i);
                    matriz[0][2] = igual.get(i);
                    matriz[1][0] = x.get(j);
                    matriz[1][1] = y.get(j);
                    matriz[1][2] = igual.get(j);
                    cortes.add(Gaus_Jordan(matriz)[0]);
                    cortes.add(Gaus_Jordan(matriz)[1]);
                }
            }
        }

        while (cortes.contains(-1.0)) {
            cortes.remove(-1.0);
            //System.out.println("miau");
        }

        return cortes;
    }

    public double[] Gaus_Jordan(double[][] matriz) {
        double retorno[] = new double[2];

        if (matriz[0][0] == 0.0) {

            double aux[][] = matriz;
            matriz[0][0] = aux[1][0];
            matriz[0][1] = aux[1][1];
            matriz[0][2] = aux[1][2];

            matriz[1][0] = aux[0][0];
            matriz[1][1] = aux[0][1];
            matriz[1][2] = aux[0][2];
        }

        matriz[0][2] /= matriz[0][0];
        matriz[0][1] /= matriz[0][0];
        matriz[0][0] /= matriz[0][0];

        matriz[1][1] = matriz[1][1] + (matriz[0][1] * -matriz[1][0]);
        matriz[1][2] = matriz[1][2] + (matriz[0][2] * -matriz[1][0]);
        matriz[1][0] = matriz[1][0] + (matriz[0][0] * -matriz[1][0]);

        matriz[1][2] /= matriz[1][1];
        matriz[1][1] /= matriz[1][1];

        matriz[0][2] = matriz[0][2] + (matriz[1][2] * -matriz[0][1]);
        matriz[0][1] = matriz[0][1] + (matriz[1][1] * -matriz[0][1]);

        retorno[0] = matriz[0][2];
        retorno[1] = matriz[1][2];

        if (retorno[0] < 0 || retorno[1] < 0) {
            retorno[0] = -1;
            retorno[1] = -1;
        }

        return retorno;
    }

    ArrayList<Double> Comprobar(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> mayor_menor, ArrayList<Double> igual, ArrayList<Double> corteX, ArrayList<Double> corteY) {

        ArrayList<Double> vertices = new ArrayList<>();
        ArrayList<String> queHay = new ArrayList<>();

        for (int i = 0; i < corteX.size(); i++) {
            int aux2 = 0;
            for (int j = 0; j < x.size(); j++) {

                //System.err.println(corteX.size() + " " + corteX.get(i) + " " + corteY.get(i));
                double aux = corteX.get(i) * x.get(j) + corteY.get(i) * y.get(j);

                //System.err.println(aux);
                if (mayor_menor.get(j) == 0.0 && aux < igual.get(j)) {//<
                    aux2++;
                }
                if (mayor_menor.get(j) == 1.0 && aux <= igual.get(j)) {//<=
                    aux2++;
                }
                if (mayor_menor.get(j) == 2.0 && aux > igual.get(j)) {//>
                    aux2++;
                }
                if (mayor_menor.get(j) == 3.0 && aux >= igual.get(j)) {//>=
                    aux2++;
                }

            }
            if (aux2 == x.size()) {
                //JOptionPane.showMessageDialog(null, corteX.get(i) + " " + corteY.get(i));
                if (queHay.contains(corteX.get(i) + " " + corteY.get(i)) || corteX.get(i) < 0 || corteX.get(i).equals(-0.0) || corteY.get(i).equals(-0.0)) {
                } else {
                    vertices.add(corteX.get(i));
                    vertices.add(corteY.get(i));
                    if(corteX.get(i)>1000000 || corteY.get(i)>1000000){
                        JOptionPane.showMessageDialog(null, "El area es infinita");
                        AreaInfinita=1;
                    }
                    System.out.println("puntos guardados " + corteX.get(i) + " " + corteY.get(i));
                    queHay.add(corteX.get(i) + " " + corteY.get(i));
                }

            }
        }

        return vertices;

    }

    public ArrayList<String> Max_min(String s, ArrayList<Double> region, double x, double y) {
        ArrayList<String> resultado = new ArrayList();

        ArrayList<Double> auxX = new ArrayList<>();
        ArrayList<Double> auxY = new ArrayList<>();
        double[] puntosPros = new double[2];

        for (int i = 0; i < region.size(); i++) {
            if (i % 2 == 0) {
                auxX.add(region.get(i));
            } else {
                auxY.add(region.get(i));
            }
        }

        if (s.equals("MAX")) {
            double aux = x * auxX.get(0) + y * auxY.get(0);

            for (int i = 0; i < auxX.size(); i++) {
                String aux2 = x + "*" + auxX.get(i) + "+" + y + "*" + auxY.get(i) + " = " + (x * auxX.get(i) + y * auxY.get(i));
                if (aux < (x * auxX.get(i) + y * auxY.get(i))) {
                    aux = x * auxX.get(i) + y * auxY.get(i);
                    puntosPros[0] = auxX.get(i);
                    puntosPros[1] = auxY.get(i);
                }
                resultado.add(aux2);
            }

            if (resultado.contains(x + "*" + puntosPros[0] + "+" + y + "*" + puntosPros[1] + " = " + (x * puntosPros[0] + y * puntosPros[1]))) {
                resultado.remove(x + "*" + puntosPros[0] + "+" + y + "*" + puntosPros[1] + " = " + (x * puntosPros[0] + y * puntosPros[1]));
                resultado.add(x + "*" + puntosPros[0] + "+" + y + "*" + puntosPros[1] + " = " + (x * puntosPros[0] + y * puntosPros[1]));
            }

            /*for (int i = 0; i < resultado.size(); i++) {
                System.out.println(resultado.get(i));
            }*/
        } else {
            double aux = x * auxX.get(0) + y * auxY.get(0);

            for (int i = 0; i < auxX.size(); i++) {
                String aux2 = x + "*" + auxX.get(i) + "+" + y + "*" + auxY.get(i) + " = " + (x * auxX.get(i) + y * auxY.get(i));
                if (aux > (x * auxX.get(i) + y * auxY.get(i))) {
                    aux = x * auxX.get(i) + y * auxY.get(i);
                    puntosPros[0] = auxX.get(i);
                    puntosPros[1] = auxY.get(i);
                }
                resultado.add(aux2);
            }

            if (resultado.contains(x + "*" + puntosPros[0] + "+" + y + "*" + puntosPros[1] + " = " + (x * puntosPros[0] + y * puntosPros[1]))) {
                resultado.remove(x + "*" + puntosPros[0] + "+" + y + "*" + puntosPros[1] + " = " + (x * puntosPros[0] + y * puntosPros[1]));
                resultado.add(x + "*" + puntosPros[0] + "+" + y + "*" + puntosPros[1] + " = " + (x * puntosPros[0] + y * puntosPros[1]));
            }
        }
        return resultado;
    }
}
