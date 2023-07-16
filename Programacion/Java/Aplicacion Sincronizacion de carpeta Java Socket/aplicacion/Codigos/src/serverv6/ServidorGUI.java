package serverv6;

import clientev6.FileData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServidorGUI extends JFrame {

    private JLabel puertoLabel;
    private JTextField puertoTextField;
    private JButton iniciarButton;

    public ServidorGUI() {
        super("Servidor");

        puertoLabel = new JLabel("Puerto:");
        puertoTextField = new JTextField(10);
        iniciarButton = new JButton("Iniciar Servidor");

        // Configurar el diseño del marco
        setLayout(new FlowLayout());

        // Agregar los componentes al marco
        add(puertoLabel);
        add(puertoTextField);
        add(iniciarButton);

        // Agregar un ActionListener al botón de iniciar
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarServidor();
            }
        });

        // Configurar las propiedades del marco
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null); // Centrar la ventana
        setResizable(false);
    }

    private void iniciarServidor() {
        int puerto = Integer.parseInt(puertoTextField.getText());

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("El servidor está en espera de conexiones en el puerto " + puerto + "...");

            while (true) {
                System.out.println("El servidor está en espera de conexiones...");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                
               

                System.out.println("Cliente conectado.");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                String peticion = (String) objectInputStream.readObject();

                if (peticion.equalsIgnoreCase("list")) {
                    String serverFolder = (String) objectInputStream.readObject();
                    File carpeta = new File(serverFolder);
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();

                    }

                    List<String> filesServer = obtenerListaArchivos(serverFolder);
                    objectOutputStream.writeObject(filesServer);
                    objectInputStream.close();
                    objectOutputStream.close();
                    socket.close();
                    

                } else {
                    String serverFolder = (String) objectInputStream.readObject();
                    File carpeta = new File(serverFolder);
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();

                    }

                    // Receive the map of file names, contents, and hashes from the client
                    Map<String, FileData> clientFiles = (Map<String, FileData>) objectInputStream.readObject();

                    // Check if the files on the server are modified or missing
                    List<String> filesToReceive = obtenerArchivosModificados(clientFiles, serverFolder);
                    List<String> filesToSend = obtenerArchivosFaltantes(clientFiles, serverFolder);

                    // Send the list of files to receive and send back to the client
                    objectOutputStream.writeObject(filesToReceive);
                    objectOutputStream.writeObject(filesToSend);

                    // Receive the modified files from the client and save them on the server
                    recibirArchivosDelCliente(socket, clientFiles, filesToReceive, serverFolder);

                    // Send the missing files to the client
                    enviarArchivosAlCliente(socket, filesToSend, serverFolder);

                    System.out.println("Archivos recibidos y guardados correctamente.");

                    objectInputStream.close();
                    objectOutputStream.close();
                    socket.close();
                    
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ServidorGUI servidorGUI = new ServidorGUI();
                servidorGUI.setVisible(true);
            }
        });
    }


    private static List<String> obtenerArchivosModificados(Map<String, FileData> clientFiles, String serverFolder) {
        List<String> filesToReceive = new ArrayList<>();

        for (String fileName : clientFiles.keySet()) {
            FileData fileData = clientFiles.get(fileName);
            String filePath = serverFolder + File.separator + fileName;

            if (!Files.exists(Paths.get(filePath))) {
                filesToReceive.add(fileName);
            } else {
                try {
                    String currentHash = calcularHashArchivo(filePath);

                    if (!currentHash.equals(fileData.getHash())) {
                        filesToReceive.add(fileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return filesToReceive;
    }

    private static List<String> obtenerArchivosFaltantes(Map<String, FileData> clientFiles, String serverFolder) throws IOException {
        List<String> filesToSend = new ArrayList<>();

        List<String> serverFiles = obtenerListaArchivos(serverFolder);
        for (String fileName : serverFiles) {
            if (!clientFiles.containsKey(fileName)) {
                filesToSend.add(fileName);
            }
        }

        return filesToSend;
    }

    private static void recibirArchivosDelCliente(Socket socket, Map<String, FileData> clientFiles, List<String> filesToReceive, String serverFolder) throws IOException {
        for (String fileName : filesToReceive) {
            FileData fileData = clientFiles.get(fileName);
            recibirArchivo(socket, fileName, fileData.getContent(), serverFolder);
        }
    }

    private static void recibirArchivo(Socket socket, String fileName, byte[] fileContent, String serverFolder) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(serverFolder + File.separator + fileName);
        fileOutputStream.write(fileContent);
        fileOutputStream.close();
    }

    private static void enviarArchivosAlCliente(Socket socket, List<String> filesToSend, String serverFolder) throws IOException {
        for (String fileName : filesToSend) {
            enviarArchivo(socket, fileName, serverFolder);
        }
    }

    private static void enviarArchivo(Socket socket, String fileName, String serverFolder) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(serverFolder + File.separator + fileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            socket.getOutputStream().write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
    }

    private static String calcularHashArchivo(String filePath) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Files.readAllBytes(Paths.get(filePath)));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> obtenerListaArchivos(String directory) throws IOException {
        List<String> files = new ArrayList<>();
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    files.add(file.getName());
                }
            }
        }

        return files;
    }
}
