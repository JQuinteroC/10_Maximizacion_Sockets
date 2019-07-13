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
import javax.swing.ButtonGroup;
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
    int x, y;
    ModeloCliente modelo;

    public ControladorCliente(GUI vista, ModeloCliente modelo) {
        this.vista = vista;
        this.modelo = modelo;
        mod = false;
    }

    public void arrancar() {
        vista.hacerVisible();
        vista.inicializar();
        vista.agnadirMensajeATrasiego("conectando con el Servidor...");
        modelo.conectarConElServidor();
        vista.agnadirMensajeATrasiego("Conectado al servidor.");
        modelo.crearFlujos();
        modelo.start();
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
                int numIne = Integer.parseInt(vista.txtNumIne.getText());

                if (numIne >= 2) {
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
                    JOptionPane.showMessageDialog(null, "El número de inecuación no puede ser menor que 2");
                    vista.txtNumIne.requestFocus();
                }
            }
        } else if (e.getSource() == vista.btnMostrarResultados) {
            /* Unir todas las inecuaciones en un strign y separarlas por un identificador
            por ejemplo ";" */
            modelo.enviarMensaje("");
            
            
            String res = modelo.recibirMensaje();
            /* Des-serializar la imagen y ponerla en un label, poner todas las inceuaciones
            con sus resultados, y poner con respecto a la necesidad el usuario el maximo o 
            el minimo*/
            Container frSolucion = new Container();
            frSolucion.setLayout(null);
            frSolucion.setPreferredSize(new Dimension(700, 190));

            vista.add(vista.spResultado);
            vista.spResultado.setBounds(20, 270, 720, 250);

            vista.spResultado.getViewport().add(frSolucion);
        }

    }

    public void agnadirMensajeATrasiego(String mensaje) {
        vista.agnadirMensajeATrasiego(mensaje);
    }
}
