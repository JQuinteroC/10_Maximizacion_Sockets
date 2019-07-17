/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelo.ModeloCliente;
import vista.GUI;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class ControladorCliente implements ActionListener {

    GUI vista;
    boolean mod;
    String solicitud;
    String tem;
    String[] puntos;
    String[] comprobar;
    int x, y;
    int numIne = 0;
    //se guardan los puntos para mandarselos a la parte logica    
    ArrayList<String> c = new ArrayList();
    ModeloCliente modelo;

    public ControladorCliente(GUI vista, ModeloCliente modelo) {
        this.vista = vista;
        this.modelo = modelo;
        mod = false;
    }

    public void arrancar() {
        vista.hacerVisible();
        vista.inicializar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCrearCampos) {
            if (vista.txtX.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "La variable X no puede estar vacía");
                vista.txtX.requestFocus();
                if (mod) {
                    vista.remove(vista.spInecuaciones);
                    vista.repaint();
                    mod = false;
                }
            } else if (vista.txtY.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "La variable Y no puede estar vacía");
                vista.txtY.requestFocus();
                if (mod) {
                    vista.remove(vista.spInecuaciones);
                    vista.repaint();
                    mod = false;
                }
            } else if (vista.txtNumIne.getText().equals("") || vista.txtNumIne.getText().equals("0")) {
                JOptionPane.showMessageDialog(null, "El número de inecuaciones no puede ser 0 (cero) ni estar vacío");
                vista.txtNumIne.requestFocus();
                if (mod) {
                    vista.remove(vista.spInecuaciones);
                    vista.repaint();
                    mod = false;
                }
            } else if (!vista.txtM.getText().equals("min") && !vista.txtM.getText().equals("max")) {
                JOptionPane.showMessageDialog(null, "Escriba 'min' si desea minimizar la ecuación o 'max' si desea maximizarla");
                vista.txtM.requestFocus();
                if (mod) {
                    vista.remove(vista.spInecuaciones);
                    vista.repaint();
                    mod = false;
                }
            } else {
                x = Integer.parseInt(vista.txtX.getText());
                y = Integer.parseInt(vista.txtY.getText());
                numIne = Integer.parseInt(vista.txtNumIne.getText());

                if (numIne >= 2 && numIne <= 20) {
                    // Contenedor
                    Container frInecuaciones = new Container();
                    frInecuaciones.setLayout(null);
                    frInecuaciones.setPreferredSize(new Dimension(350, numIne * 30 + 35));

                    // Panel scroll
                    vista.spInecuaciones.setBounds(20, 110, 370, 150);
                    vista.spInecuaciones.getViewport().add(frInecuaciones);

                    // Imagen referencia
                    Image img = new ImageIcon("referencia.jpg").getImage();
                    ImageIcon img2 = new ImageIcon(img.getScaledInstance(340, 150, Image.SCALE_SMOOTH));

                    JLabel d = new JLabel(img2);
                    d.setBounds(400, 110, 340, 150);
                    vista.add(d);
                    vista.repaint();

                    // Limpiar 
                    if (mod) {
                        // Elimiar los campos de variables
                        frInecuaciones.removeAll();
                        frInecuaciones.repaint();

                        // Elimiar los campos de Inecuacinoes
                        vista.remove(vista.spResultado);
                        vista.repaint();
                    }

                    mod = true;
                    /* variable de control de modificación*/

                    // Instancia de los arreglos de los componentes - inecuaciones
                    vista.txtEcuaciones = new JTextField[numIne][3];
                    vista.rbtnEcuaciones = new JRadioButton[numIne][4];
                    vista.grpRbtn = new ButtonGroup[numIne];

                    // Instancias de los componentes - inecuaciones
                    for (int i = 0; i < numIne; i++) {
                        for (int j = 0; j < 3; j++) {
                            // Campos
                            vista.txtEcuaciones[i][j] = new JTextField();
                        }
                        // Botones de radio
                        vista.rbtnEcuaciones[i][0] = new JRadioButton("<", false);
                        vista.rbtnEcuaciones[i][1] = new JRadioButton("<=", false);
                        vista.rbtnEcuaciones[i][2] = new JRadioButton(">", false);
                        vista.rbtnEcuaciones[i][3] = new JRadioButton(">=", false);
                        // Grupos de botones de radio
                        vista.grpRbtn[i] = new ButtonGroup();
                        vista.grpRbtn[i].add(vista.rbtnEcuaciones[i][0]);
                        vista.grpRbtn[i].add(vista.rbtnEcuaciones[i][1]);
                        vista.grpRbtn[i].add(vista.rbtnEcuaciones[i][2]);
                        vista.grpRbtn[i].add(vista.rbtnEcuaciones[i][3]);
                    }

                    // Agrega los componentes - inecuaciones
                    for (int i = 0; i < numIne; i++) {
                        for (int j = 0; j < 3; j++) {
                            frInecuaciones.add(vista.txtEcuaciones[i][j]);

                            if (j == 2) {
                                vista.txtEcuaciones[i][j].setBounds(225 + (j * 45), 25 + (i * 30), 30, 25);

                                // Redefine el tamaño del contenedor
                                if ((10 + (j * 45)) > 350 || (125 + (i * 30)) > 350) {
                                    frInecuaciones.setPreferredSize(new Dimension(65 + (j * 45), 120 + (i * 30)));
                                }
                            } else {
                                vista.txtEcuaciones[i][j].setBounds(20 + (j * 45), 25 + (i * 30), 30, 25);
                            }
                        }
                        frInecuaciones.add(vista.rbtnEcuaciones[i][0]);
                        vista.rbtnEcuaciones[i][0].setBounds(60 + 50, 25 + (i * 30), 45, 25);
                        frInecuaciones.add(vista.rbtnEcuaciones[i][1]);
                        vista.rbtnEcuaciones[i][1].setBounds(60 + 100, 25 + (i * 30), 45, 25);
                        frInecuaciones.add(vista.rbtnEcuaciones[i][2]);
                        vista.rbtnEcuaciones[i][2].setBounds(60 + 150, 25 + (i * 30), 45, 25);
                        frInecuaciones.add(vista.rbtnEcuaciones[i][3]);
                        vista.rbtnEcuaciones[i][3].setBounds(60 + 200, 25 + (i * 30), 45, 25);
                        if (i == numIne - 1) {
                            frInecuaciones.add(vista.btnMostrarResultados);
                            vista.btnMostrarResultados.setBounds(205, 60 + (i * 30), 140, 30);
                            frInecuaciones.setPreferredSize(new Dimension(frInecuaciones.getWidth(), 100 + (i * 30)));
                        }
                    }
                    frInecuaciones.add(vista.lblEcX);
                    vista.lblEcX.setBounds(13, 5, 40, 25);
                    frInecuaciones.add(vista.lblEcY);
                    vista.lblEcY.setBounds(60, 5, 40, 25);
                    frInecuaciones.add(vista.lblEcN);
                    vista.lblEcN.setBounds(310, 5, 45, 25);
                } else {
                    JOptionPane.showMessageDialog(null, "El número de inecuación no puede ser menor que 2 ni mayor a 20");
                    vista.txtNumIne.requestFocus();
                }
            }
        } else if (e.getSource() == vista.btnMostrarResultados) {
            /* Unir todas las inecuaciones en un string y separarlas por un identificador
            por ejemplo ";" */
            solicitud = vista.txtM.getText();
            c.add(vista.txtX.getText() + "," + vista.txtY.getText() + "," + solicitud);

            for (int i = 0; i < numIne; i++) {
                // Selección de tipo de inequidad
                for (Enumeration et = vista.grpRbtn[i].getElements(); et.hasMoreElements();) {
                    JRadioButton b = (JRadioButton) et.nextElement();
                    if (b.getModel() == vista.grpRbtn[i].getSelection()) {
                        solicitud = b.getText();
                    }
                }
                c.add(vista.txtEcuaciones[i][0].getText() + "," + vista.txtEcuaciones[i][1].getText() + "," + solicitud + "," + vista.txtEcuaciones[i][2].getText());
            }
            c.add("0");
            modelo.setC(c);
            try {
                tem = modelo.Connect();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            puntos = SepararPuntos(tem, 0);
            comprobar = SepararPuntos(tem, 1);
           
            /////////////////////////////////////////////////////////////////////////////            
            Container frSolucion = new Container();
            frSolucion.setLayout(null);
            frSolucion.setPreferredSize(new Dimension(700, 190));
            
             /* Poner texto en el container frSolucion
            Llenar_puntos(puntos);
            Llenar_Evaluar(evaluar);
             */
            Poner_Imagen();
            // Panel de respuestas Dar.repaint();
            
            
            vista.add(vista.spResultado);
            vista.spResultado.setBounds(20, 270, 720, 250);

            vista.spResultado.getViewport().add(frSolucion);
        }
    }

    public void Poner_Imagen() {
        ImageIcon icon = new ImageIcon("Grafica.jpg");
        Image scaleImage = icon.getImage().getScaledInstance(517, 401, Image.SCALE_DEFAULT);
        Icon iconx = new ImageIcon(scaleImage);
        // LAbel donde mostrar la imagen --- foto_label.setIcon(iconx);
    }
    
    public String[] SepararPuntos(String cadena,int x) {
        String split0[] = cadena.split("::");
        String split1[] = split0[x].split(":");
        return split1;
    }
}
