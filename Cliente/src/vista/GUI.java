package vista;

import controlador.ControladorCliente;
import java.awt.Container;
import java.awt.Dimension;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class GUI extends JFrame implements IVista {

    ControladorCliente controlador;
    PrintWriter sok;
    public JTextField txtX = new JTextField();
    public JTextField txtY = new JTextField();
    public JTextField txtNumIne = new JTextField();
    public JLabel lblTitulo = new JLabel("Algebra líneal. Max/Min");
    public JLabel lblX = new JLabel("Coef X:");
    public JLabel lblY = new JLabel("Coef Y:");
    public JLabel lblNumIne = new JLabel("Número de inecuaciones:");
    public JLabel lblVarZ = new JLabel("Variables de Z:");
    public JScrollPane spValores = new JScrollPane();
    public JScrollPane spVariables = new JScrollPane();
    public JScrollPane spInecuaciones = new JScrollPane();
    public JScrollPane spDesarollo = new JScrollPane();
    public JButton btnCrearCampos = new JButton("Crear campos de inecuaciones");
    public JButton btnMostrarResultados = new JButton("Mostrar resultado");
    public JTextField txtEcuaciones[][];
    public JTextField txtVariables[];
    public JLabel lblNomEcu[];
    public JLabel lblIgual[];
    public JTextArea txtProceso = new JTextArea();

    void mostrar() {
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(215, 5, 350, 45);

        spValores.setBounds(20, 50, 720, 50);

        lblX.setBounds(10, 10, 40, 25);
        txtX.setBounds(55, 10, 50, 25);

        lblY.setBounds(120, 10, 40, 25);
        txtY.setBounds(165, 10, 50, 25);

        lblNumIne.setBounds(230, 10, 150, 25);
        txtNumIne.setBounds(380, 10, 50, 25);
        btnCrearCampos.setBounds(460, 8, 240, 30);
        lblVarZ.setBounds(10, 10, 100, 25);

        setSize(780, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Factorial");
    }

    public GUI() {
        Container c = getContentPane();

        c.setLayout(null);

        // Crear segmento Scroll de Variables
        c.add(spValores);
        c.add(lblTitulo);
        Container frValores = new Container();
        frValores.setLayout(null);
        frValores.setPreferredSize(new Dimension(700, 45));
        spValores.getViewport().add(frValores);

        // Ingresar componentes a Scroll de Variables
        frValores.add(lblX);
        frValores.add(txtX);
        frValores.add(lblY);
        frValores.add(txtY);
        frValores.add(lblNumIne);
        frValores.add(txtNumIne);
        frValores.add(btnCrearCampos);

        c.add(spVariables);
        c.add(spInecuaciones);
        c.add(spDesarollo);

//        btnCrearCampos.addActionListener(this);
    }

    @Override
    public void habilitarEnviar() {
        //jButtonEnviar.setEnabled(true);
    }

    @Override
    public void deshabilitarEnviar() {
        //jButtonEnviar.setEnabled(false);
    }

    @Override
    public void agnadirMensajeATrasiego(String mensaje) {
        //jTextAreaTrasiego.append(mensaje + "\n");
    }

    @Override
    public void borrarTextoAEnviar() {
        //jTextFieldTextoAEnviar.setText("");
    }

    @Override
    public void setControlador(ControladorCliente controlador) {
        this.controlador = controlador;
    }

    @Override
    public void hacerVisible() {
        setVisible(true);
    }

    @Override
    public void inicializar() {
        //jButtonEnviar.setActionCommand(ENVIAR);
        //jButtonEnviar.addActionListener(controlador);
    }

    @Override
    public String getMensajeAEnviar() {
        return null;
        //return jTextFieldTextoAEnviar.getText();
    }

}
