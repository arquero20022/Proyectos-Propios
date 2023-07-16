package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MenuPrincipal extends JFrame {

    private JPanel contentPane;
    public int activaMusica;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();

                    frame.setVisible(true);
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuPrincipal() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        setUndecorated(true);
        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        JLabel etiquetaTitulo = new JLabel();
        etiquetaTitulo.setIcon(new ImageIcon("src/main/resources/images/fondo.png"));
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setFont(new Font("Arial Black", Font.PLAIN, 18));
        panel.add(etiquetaTitulo, BorderLayout.NORTH);

        JLabel etiquetaInfo = new JLabel("Elige el tamaño del mapa y los iconos");
        etiquetaInfo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(etiquetaInfo, BorderLayout.SOUTH);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(null);

        JLabel EtiquetaTamanio = new JLabel("Tamaño:");
        EtiquetaTamanio.setBounds(140, 25, 120, 15);
        panel_1.add(EtiquetaTamanio);

        final JComboBox tamanio = new JComboBox();
        tamanio.setModel(new DefaultComboBoxModel(new String[]{"6x6", "8x8", "10x10", "12x12"}));
        tamanio.setBounds(215, 20, 100, 20);
        panel_1.add(tamanio);

        JButton btnSalir = new JButton();
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSalir.setIcon(new ImageIcon("src/main/resources/images/salir.png"));
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setBounds(225, 177, 90, 64);
        panel_1.add(btnSalir);

        JButton btnJugar = new JButton();
        btnJugar.setIcon(new ImageIcon("src/main/resources/images/inicio.png"));
        
        btnJugar.setContentAreaFilled(false);
        btnJugar.setBorderPainted(false);
        btnJugar.setBounds(120, 177, 89, 64);
        panel_1.add(btnJugar);

        JLabel lblVistas = new JLabel("Vistas:");
        lblVistas.setBounds(150, 60, 115, 15);
        panel_1.add(lblVistas);

        final JComboBox vistas = new JComboBox();
        vistas.setModel(new DefaultComboBoxModel(new String[]{"Iconos", "Colores", "Formas"}));
        vistas.setBounds(215, 55, 100, 20);
        panel_1.add(vistas);

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String vistasGame = vistas.getSelectedItem().toString();
                if (vistasGame.equals("Iconos")) {
                    GameCanvas.setVistas(2);
                } else if (vistasGame.equals("Colores")) {
                    GameCanvas.setVistas(1);
                } else if (vistasGame.equals("Formas")) {
                    GameCanvas.setVistas(3);
                }

                int tamanioCuad = tamanio.getSelectedIndex();

                switch (tamanioCuad) {
                    case 0:
			try {

                        Game gui = new Game(480 / 6, 0);

                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                    case 1:
						try {

                        Game gui = new Game(480 / 8, 0);
                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                    case 2:
						try {

                        Game gui = new Game(480 / 10, 0);

                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                    case 3:
						try {

                        Game gui = new Game(480 / 12, 0);

                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                    case 4:
						try {

                        Game gui = new Game(480 / 16, 0);

                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                    default:
						try {

                        Game gui = new Game(480 / 12, 0);

                        dispose();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
