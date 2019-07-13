package vista;

import controlador.ControladorCliente;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class GUI extends JFrame {

    ControladorCliente controlador;
    public JTextField txtX = new JTextField();
    public JTextField txtY = new JTextField();
    public JTextField txtM = new JTextField();
    public JTextField txtNumIne = new JTextField();
    public JLabel lblTitulo = new JLabel("Álgebra lineal. Max/Min");
    public JLabel lblX = new JLabel("Coef X:");
    public JLabel lblY = new JLabel("Coef Y:");
    public JLabel lblEcX = new JLabel("Coef X:");
    public JLabel lblEcY = new JLabel("Coef Y:");
    public JLabel lblEcN = new JLabel("Núm.");
    public JLabel lblNumIne = new JLabel("Número de inecuaciones:");
    public JLabel lblMaxMin = new JLabel("max-min:");
    public JScrollPane spValores = new JScrollPane();
    public JScrollPane spInecuaciones = new JScrollPane();
    public JScrollPane spResultado = new JScrollPane();
    public JButton btnCrearCampos = new JButton("Crear campos de inecuaciones");
    public JButton btnMostrarResultados = new JButton("Mostrar resultado");
    public JTextField txtEcuaciones[][];
    public JRadioButton rbtnEcuaciones[][];
    public ButtonGroup grpRbtn[];
    public JTextArea txtProceso = new JTextArea();

    public void mostrar() {
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(215, 5, 350, 45);

        spValores.setBounds(20, 50, 720, 50);

        lblX.setBounds(10, 10, 40, 25);
        txtX.setBounds(55, 10, 30, 25);

        lblY.setBounds(100, 10, 40, 25);
        txtY.setBounds(145, 10, 30, 25);

        lblNumIne.setBounds(190, 10, 150, 25);
        txtNumIne.setBounds(340, 10, 30, 25);

        lblMaxMin.setBounds(380, 10, 60, 25);
        txtM.setBounds(440, 10, 30, 25);

        btnCrearCampos.setBounds(480, 8, 220, 30);

        setSize(770, 680);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Álgebra lineal");
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
        frValores.add(lblMaxMin);
        frValores.add(txtM);
        frValores.add(btnCrearCampos);

        c.add(spInecuaciones);
        c.add(spResultado);
//        btnCrearCampos.addActionListener(this);
    }

    public void habilitarEnviar() {
        //jButtonEnviar.setEnabled(true);
    }

    public void deshabilitarEnviar() {
        //jButtonEnviar.setEnabled(false);
    }

    public void agnadirMensajeATrasiego(String mensaje) {
        //jTextAreaTrasiego.append(mensaje + "\n");
    }

    public void borrarTextoAEnviar() {
        //jTextFieldTextoAEnviar.setText("");
    }

    public void setControlador(ControladorCliente controlador) {
        this.controlador = controlador;
    }

    public void hacerVisible() {
        setVisible(true);
    }

    public void inicializar() {
        btnCrearCampos.addActionListener(controlador);
        btnMostrarResultados.addActionListener(controlador);
    }

    public String getMensajeAEnviar() {
        return null;
        //return jTextFieldTextoAEnviar.getText();
    }

}
