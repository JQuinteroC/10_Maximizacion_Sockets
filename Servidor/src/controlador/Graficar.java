/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Graficar extends javax.swing.JFrame{

    private javax.swing.JPanel p;
    File fichero = new File("Grafica.jpg");
    String formato = "jpg";
    BufferedImage imagen;
    Graphics2D g2d;
    Graphics g;

    Graficar() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(500, 500));
        
        p = new javax.swing.JPanel();
        p.setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout pLayout = new javax.swing.GroupLayout(p);
        p.setLayout(pLayout);
        pLayout.setHorizontalGroup(
                pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        pLayout.setVerticalGroup(
                pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 481, Short.MAX_VALUE)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addComponent(p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addContainerGap())
        );

        pack();
    }

    public void takePhoto(int inf, double[] xdouble, double[] ydouble, double[] xlinedouble, double[] ylinedouble) {

        int x[] = new int[xdouble.length];
        int y[] = new int[ydouble.length];
        int xline[] = new int[xlinedouble.length];
        int yline[] = new int[ylinedouble.length];

        for (int i = 0; i < xdouble.length; i++) {
            xdouble[i] = xdouble[i] * 100;
            x[i] = (int) xdouble[i];

            ydouble[i] = ydouble[i] * 100;
            y[i] = (int) ydouble[i];

        }
        for (int i = 0; i < xlinedouble.length; i++) {
            xlinedouble[i] = xlinedouble[i] * 100;
            xline[i] = (int) xlinedouble[i];

            ylinedouble[i] = ylinedouble[i] * 100;
            yline[i] = (int) ylinedouble[i];

        }

        int mayor = x[0];

        for (int i = 0; i < x.length; i++) {
            if (x[i] > mayor) {
                mayor = x[i];

            }
        }

        if (mayor >= 840) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] / (20);
                yline[i] = yline[i] / (20);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] / (40);
                y[i] = y[i] / (40);
            }
        }
        if (mayor >= 480 && mayor < 840) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] / (8);
                yline[i] = yline[i] / (8);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] / (16);
                y[i] = y[i] / (16);
            }
        }

        if (mayor >= 240 && mayor < 480) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] / (4);
                yline[i] = yline[i] / (4);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] / (8);
                y[i] = y[i] / (8);
            }
        }

        if (mayor >= 120 && mayor < 240) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] / (2);
                yline[i] = yline[i] / (2);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] / (4);
                y[i] = y[i] / (4);
            }
        }

        if (mayor >= 60 && mayor < 120) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] * (2);
                yline[i] = yline[i] * (2);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] * (1);
                y[i] = y[i] * (1);
            }
        }

        if (mayor >= 30 && mayor < 60) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] * (4);
                yline[i] = yline[i] * (4);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] * (2);
                y[i] = y[i] * (2);
            }
        }
        if (mayor >= 15 && mayor < 30) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] * (8);
                yline[i] = yline[i] * (8);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] * (4);
                y[i] = y[i] * (4);
            }
        }
        if (mayor >= 7 && mayor < 15) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] * (16);
                yline[i] = yline[i] * (16);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] * (8);
                y[i] = y[i] * (8);
            }
        }

        if (mayor < 7) {

            for (int i = 0; i < xline.length; i++) {
                xline[i] = xline[i] * (30);
                yline[i] = yline[i] * (30);
            }
            for (int i = 0; i < x.length; i++) {
                x[i] = x[i] * (15);
                y[i] = y[i] * (15);
            }
        }

        int w = 632;
        int h = 500;
        imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        g2d = (Graphics2D) imagen.getGraphics();

        p.paint(g2d);
        drawPlane();
        drawLines(inf, x, y, xline, yline);
        try {
            ImageIO.write(imagen, formato, fichero);
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
    }

    public void drawPlane() {
        g = getGraphics();
        int x1, y1, x2, y2;
        g.setColor(new Color(193, 81, 103));
        g2d.setColor(new Color(193, 81, 103));
        //g= Dibujo para que el server visualice y g2d= dibujo offscreen
        x1 = 20;
        y1 = 500;
        x2 = 650;
        y2 = 500;
        g.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x1 - 20, y1 - 50, x2 - 20, y2 - 50);

        x1 = 50;
        y1 = 42;
        x2 = 50;
        y2 = 540;
        g.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x1 - 20, y1 - 50, x2 - 20, y2 - 50);
    }

    public void drawLines(int infinita, int[] xs_Solucion, int ys_Solucion[], int xs_Lineas[], int ys_lineas[]) {
        int x0, y0, x1, y1, x2, y2;
        int p1[] = new int[2];
        int p2[] = new int[2];
        x0 = 50;
        y0 = 500;
        g.setColor(Color.gray);
        g2d.setColor(Color.gray);

        //  Arrays.sort(xs_Solucion);
        //System.out.println(Arrays.toString(xs_Solucion));
        //esta vrga de aca reorganiza los arreglos de puntos para que el area de soluciones se vea cool
        if (infinita == 0) {
            for (int x = 0; x < xs_Solucion.length; x++) {
                for (int i = 0; i < xs_Solucion.length - x - 1; i++) {
                    if (xs_Solucion[i] == xs_Solucion[i + 1]) {
                        if (ys_Solucion[i] < ys_Solucion[i + 1]) {
                            int tmpx = xs_Solucion[i + 1];
                            xs_Solucion[i + 1] = xs_Solucion[i];
                            xs_Solucion[i] = tmpx;

                            int tmpy = ys_Solucion[i + 1];
                            ys_Solucion[i + 1] = ys_Solucion[i];
                            ys_Solucion[i] = tmpy;
                        }

                    } else if (xs_Solucion[i] < xs_Solucion[i + 1]) {

                        int tmpx = xs_Solucion[i + 1];
                        xs_Solucion[i + 1] = xs_Solucion[i];
                        xs_Solucion[i] = tmpx;

                        int tmpy = ys_Solucion[i + 1];
                        ys_Solucion[i + 1] = ys_Solucion[i];
                        ys_Solucion[i] = tmpy;
                    }
                }
            }

            for (int i = 0; i < xs_Solucion.length;
                    i++) {
                xs_Solucion[i] = 30 + xs_Solucion[i] * 2;
                ys_Solucion[i] = 450 - ys_Solucion[i] * 2;
            }

//Esto dibuja el area de las soluciones posibles usando un arreglo de las coordenas x y uno de las 
//coordenadas y , n es el numero de puntos que define la figura
            int n = xs_Solucion.length;

            g.setColor(new Color(56,170,235));

            g2d.setColor(new Color(56,170,235));

            g.fillPolygon(xs_Solucion, ys_Solucion, n);

            g.drawPolyline(xs_Solucion, ys_Solucion, n);

            g2d.fillPolygon(xs_Solucion, ys_Solucion, n);

            g2d.drawPolyline(xs_Solucion, ys_Solucion, n);
        } else {
            g.drawString("Region infinita", 250, 250);
        }

        for (int i = 0; i < xs_Lineas.length; i++) {
            x1 = xs_Lineas[i];
            y1 = ys_lineas[i];

            x2 = xs_Lineas[i + 1];
            y2 = ys_lineas[i + 1];
            p1[0] = (x0 + x1);
            p1[1] = (y0 - y1);

            p2[0] = (x0 + x2);
            p2[1] = (y0 - y2);
            g.setColor(Color.BLACK);
            g2d.setColor(Color.BLACK);

            //Dibujo para el server
            g.drawLine(p1[0], p1[1], p2[0], p2[1]);
            //Dibujo off-screen
            g2d.drawLine(p1[0] - 20, p1[1] - 50, p2[0] - 20, p2[1] - 50);
            i++;
        }
    }
}
