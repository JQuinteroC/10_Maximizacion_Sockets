/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
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

                // Contenedor
                Container frInecuaciones = new Container();
                frInecuaciones.setLayout(null);
                frInecuaciones.setPreferredSize(new Dimension(700, numIne * 30 + 35));

                // Panel scroll
                vista.spInecuaciones.setBounds(20, 110, 720, 150);
                vista.spInecuaciones.getViewport().add(frInecuaciones);

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
                            //d.txtEcuaciones[i][j].getLocation();
                            //d.lblNomEcu[j].setBounds(30 + (j * 45), 10, 30, 25);
                            vista.txtEcuaciones[i][j].setBounds(215 + (j * 45), 30 + (i * 30), 30, 25);

                            //           d.lblIgual[i].setBounds(5 + (j * 45), 40 + (i * 30), 30, 25);
                            // Redefine el tamaño del contenedor
                            if ((10 + (j * 45)) > 350 || (125 + (i * 30)) > 350) {
                                frInecuaciones.setPreferredSize(new Dimension(65 + (j * 45), 125 + (i * 30)));
                            }
                        } else {
                            //         d.lblNomEcu[j].setBounds(15 + (j * 45), 10, 30, 25);
                            vista.txtEcuaciones[i][j].setBounds(10 + (j * 45), 30 + (i * 30), 30, 25);
                        }
                    }
                    frInecuaciones.add(vista.rbtnEcuaciones[i][0]);
                    vista.rbtnEcuaciones[i][0].setBounds(50 + 50, 30 + (i * 30), 45, 25);
                    frInecuaciones.add(vista.rbtnEcuaciones[i][1]);
                    vista.rbtnEcuaciones[i][1].setBounds(50 + 100, 30 + (i * 30), 45, 25);
                    frInecuaciones.add(vista.rbtnEcuaciones[i][2]);
                    vista.rbtnEcuaciones[i][2].setBounds(50 + 150, 30 + (i * 30), 45, 25);
                    frInecuaciones.add(vista.rbtnEcuaciones[i][3]);
                    vista.rbtnEcuaciones[i][3].setBounds(50 + 200, 30 + (i * 30), 45, 25);

                }
            }
        } else if (e.getSource() == vista.btnMostrarResultados) {

            Container frSolucion = new Container();
            frSolucion.setLayout(null);
            frSolucion.setPreferredSize(new Dimension(700, 190));

            vista.add(vista.spResultado);
            vista.spResultado.setBounds(20, 270, 720, 250);

//                    frVariables.add(vista.lblVarZ);
            vista.spResultado.getViewport().add(frSolucion);
        }

    }

    public void agnadirMensajeATrasiego(String mensaje) {
        vista.agnadirMensajeATrasiego(mensaje);
    }
}
