package clases;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class EstudianteVista extends JFrame implements ActionListener {

    private JTextArea areaTexto;
    private JButton botonConectar, botonDesconectar, botonInsertar, botonConsultar, botonEliminar, botonLimpiar;
    private JLabel etiquetaDni, etiquetaNombre, etiquetaApellidos;
    private JTextField campoDni, campoNombre, campoApellidos;
    private JMenuBar barraMenu;
    JMenu menuArchivo;
    JMenuItem opcionConectar, opcionDesconectar, opcionSalir;

    private EstudianteControlador controlador;

    public EstudianteVista(EstudianteControlador controlador) {
        this.controlador = controlador;

        // Crear componentes de la interfaz gráfica
        areaTexto = new JTextArea();
        botonConectar = new JButton(new ImageIcon("src/main/resources/icono_conectarInicio.png"));
        botonDesconectar = new JButton(new ImageIcon("src/main/resources/icono_desconectar.png"));
        botonInsertar = new JButton(new ImageIcon("src/main/resources/icono_insertar.png"));
        botonConsultar = new JButton(new ImageIcon("src/main/resources/icono_consultar.png"));
        botonEliminar = new JButton(new ImageIcon("src/main/resources/icono_eliminar.png"));
        botonLimpiar = new JButton("Limpiar");
        etiquetaDni = new JLabel("DNI");
        etiquetaNombre = new JLabel("Nombre");
        etiquetaApellidos = new JLabel("Apellidos");
        campoDni = new JTextField();
        campoNombre = new JTextField();
        campoApellidos = new JTextField();

        // Crear paneles para organizar los componentes
        JPanel panelBotones = new JPanel(new GridLayout(1, 6));
        panelBotones.add(botonConectar);
        panelBotones.add(botonDesconectar);
        panelBotones.add(botonInsertar);
        panelBotones.add(botonConsultar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonLimpiar);
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
        panelFormulario.add(etiquetaDni);
        panelFormulario.add(campoDni);
        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaApellidos);
        panelFormulario.add(campoApellidos);

        // Crear barra de menú y elementos del menú
        barraMenu = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        opcionConectar = new JMenuItem("Conectar");
        opcionDesconectar = new JMenuItem("Desconectar");
        opcionSalir = new JMenuItem("Salir");
        menuArchivo.add(opcionConectar);
        menuArchivo.add(opcionDesconectar);
        menuArchivo.addSeparator();
        menuArchivo.add(opcionSalir);
        barraMenu.add(menuArchivo);

        // Añadir componentes al marco principal
        setJMenuBar(barraMenu);
        setLayout(new BorderLayout());
        add(panelBotones, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
        add(areaTexto, BorderLayout.SOUTH);

        // Establecer propiedades de la ventana
        setTitle("Gestión de estudiantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Registrarse como oyente de eventos para los botones y elementos del menú
        botonConectar.addActionListener(this);
        botonDesconectar.addActionListener(this);
        botonInsertar.addActionListener(this);
        botonConsultar.addActionListener(this);
        botonEliminar.addActionListener(this);
        botonLimpiar.addActionListener(this);
        opcionConectar.addActionListener(this);
        opcionDesconectar.addActionListener(this);
        opcionSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonConectar || e.getSource() == opcionConectar) {
            // Conectarse a la base de datos cuando se haga clic en el botón "Conectar" o en la opción del menú "Conectar"
            areaTexto.setText("Conectando a la base de datos...");
            try {
                controlador.conectar();
                areaTexto.setText("Conexión exitosa");
                botonConectar.setIcon(new ImageIcon("src/main/resources/icono_conectado.png"));
            } catch (SQLException ex) {
                areaTexto.setText("Error al conectar a la base de datos" + ex);
            }
        } else if (e.getSource() == botonDesconectar || e.getSource() == opcionDesconectar) {
            // Desconectarse de la base de datos cuando se haga clic en el botón "Desconectar" o en la opción del menú "Desconectar"
            areaTexto.setText("Desconectando de la base de datos...");
            try {
                controlador.desconectar();
                areaTexto.setText("Desconexión exitosa");
                botonConectar.setIcon(new ImageIcon("src/main/resources/icono_conectarInicio.png"));
            } catch (SQLException ex) {
                areaTexto.setText("Error al desconectar de la base de datos");
            }
        } else if (e.getSource() == botonInsertar) {
            // Insertar un nuevo estudiante cuando se haga clic en el botón "Insertar"
            String dni = campoDni.getText();
            String nombre = campoNombre.getText();
            String apellidos = campoApellidos.getText();
            if (dni.trim().length() == 0 || nombre.trim().length() == 0 || apellidos.trim().length() == 0) {
                areaTexto.setText("Por favor, rellene todos los campos");
            } else {
                try {

                    Estudiante estudiante = new Estudiante(dni, nombre, apellidos);
                    controlador.insertarEstudiante(estudiante);
                    campoDni.setText("");
                    campoApellidos.setText("");
                    campoNombre.setText("");

                    areaTexto.setText("Estudiante insertado exitosamente");
                } catch (SQLException ex) {
                    areaTexto.setText("Error al insertar el estudiante");
                }
            }
        } else if (e.getSource() == botonConsultar) {
            // Consultar todos los estudiantes cuando se haga clic en el botón "Consultar"
            String dni = campoDni.getText();
            String nombre = campoNombre.getText();
            String apellidos = campoApellidos.getText();
            if (dni.trim().length() == 0 && nombre.trim().length() == 0 && apellidos.trim().length() == 0) {
                try {
                    List<Estudiante> estudiantes = controlador.consultarEstudiantes();
                    StringBuilder sb = new StringBuilder();
                    sb.append("DNI\tNOMBRE\tApellidos\n");
                    for (Estudiante estudiante : estudiantes) {
                        sb.append(estudiante.getDni()).append("\t")
                                .append(estudiante.getNombre()).append("\t")
                                .append(estudiante.getApellidos()).append("\n");
                    }
                    areaTexto.setText(sb.toString());
                } catch (SQLException ex) {
                    areaTexto.setText("Error al consultar los estudiantes");
                }
            }else if(dni.trim().length() != 0){
            try {
                    List<Estudiante> estudiantes = controlador.consultarEstudiantesDni(dni);
                    StringBuilder sb = new StringBuilder();
                    sb.append("DNI\tNOMBRE\tApellidos\n");
                    for (Estudiante estudiante : estudiantes) {
                        sb.append(estudiante.getDni()).append("\t")
                                .append(estudiante.getNombre()).append("\t")
                                .append(estudiante.getApellidos()).append("\n");
                    }
                    areaTexto.setText(sb.toString());
                } catch (SQLException ex) {
                    areaTexto.setText("Error al consultar los estudiantes");
                }
            
        }else if(nombre.trim().length() != 0){
            try {
                    List<Estudiante> estudiantes = controlador.consultarEstudiantesNombre(nombre);
                    StringBuilder sb = new StringBuilder();
                    sb.append("DNI\tNOMBRE\tApellidos\n");
                    for (Estudiante estudiante : estudiantes) {
                        sb.append(estudiante.getDni()).append("\t")
                                .append(estudiante.getNombre()).append("\t")
                                .append(estudiante.getApellidos()).append("\n");
                    }
                    areaTexto.setText(sb.toString());
                } catch (SQLException ex) {
                    areaTexto.setText("Error al consultar los estudiantes por nombre");
                }
            
        }
            else if(apellidos.trim().length() != 0){
            try {
                    List<Estudiante> estudiantes = controlador.consultarEstudiantesApellidos(apellidos);
                    StringBuilder sb = new StringBuilder();
                    sb.append("DNI\tNOMBRE\tApellidos\n");
                    for (Estudiante estudiante : estudiantes) {
                        sb.append(estudiante.getDni()).append("\t")
                                .append(estudiante.getNombre()).append("\t")
                                .append(estudiante.getApellidos()).append("\n");
                    }
                    areaTexto.setText(sb.toString());
                } catch (SQLException ex) {
                    areaTexto.setText("Error al consultar los estudiantes");
                }
            
        }
        } else if (e.getSource() == botonEliminar) {
            // Eliminar un estudiante cuando se haga clic en el botón "Eliminar"
            String dni = campoDni.getText();
            if (dni.trim().length() == 0) {
                areaTexto.setText("Por favor, ingrese el DNI del estudiante que desea eliminar");
            } else {
                try {
                    controlador.eliminarEstudiante(dni);
                    areaTexto.setText("Estudiante eliminado exitosamente");
                } catch (SQLException ex) {
                    areaTexto.setText("Error al eliminar el estudiante");
                }
            }
        } else if (e.getSource() == botonLimpiar) {
            // Limpiar el área de texto cuando se haga clic en el botón "Limpiar"
            areaTexto.setText("");
        } else if (e.getSource() == opcionSalir) {
            // Salir de la aplicación cuando se haga clic en la opción del menú "Salir"
            System.exit(0);
        }
    }
}
