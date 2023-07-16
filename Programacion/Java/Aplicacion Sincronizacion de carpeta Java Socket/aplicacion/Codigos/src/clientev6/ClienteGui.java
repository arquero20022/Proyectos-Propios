package clientev6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteGui {

    private static String DEFAULT_SERVER_HOST = "localhost";
    private static int DEFAULT_SERVER_PORT = 9999;
    private static String DEFAULT_CLIENT_DIRECTORY = "C:\\Users\\arque\\Desktop\\sincronizacion de carpeta perfecta\\carpeta_cliente";

    private String clientDirectory;
    private String serverDirectory;

    private String peticion = " ";

    private JFrame frame;
    private JTextField clientDirTextField;
    private JButton clientDirButton;
    private JTextField serverDirTextField;
    private JButton serverDirButton;
    private JTextField serverHostTextField;
    private JTextField serverPortTextField;
    private JTextArea filesServerTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClienteGui();
            }
        });
    }

    public ClienteGui() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Cliente de Sincronización");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel clientDirLabel = new JLabel("Carpeta del Cliente:");
        clientDirTextField = new JTextField(DEFAULT_CLIENT_DIRECTORY);
        clientDirButton = new JButton("Seleccionar");
        clientDirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectClientDirectory();
            }
        });

        JLabel serverDirLabel = new JLabel("Carpeta del Servidor:");
        serverDirTextField = new JTextField();
        

        JLabel serverHostLabel = new JLabel("Dirección IP:");
        serverHostTextField = new JTextField(DEFAULT_SERVER_HOST);

        JLabel serverPortLabel = new JLabel("Puerto:");
        serverPortTextField = new JTextField(String.valueOf(DEFAULT_SERVER_PORT));

        JLabel filesServerLabel = new JLabel("Archivos en el Servidor:");
        filesServerTextArea = new JTextArea();
        filesServerTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(filesServerTextArea);
        scrollPane.setPreferredSize(new Dimension(200, 150)); // Ajusta el tamaño preferido del scrollPane
        

        JButton syncButton = new JButton("Sincronizar Cliente con Servidor");
        syncButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientDirectory = clientDirTextField.getText();
                serverDirectory = serverDirTextField.getText();
                DEFAULT_SERVER_PORT = Integer.parseInt(serverPortTextField.getText());
                DEFAULT_SERVER_HOST = serverHostTextField.getText();

                peticion = " ";
                synchronizeFiles();
            }
        });

        JButton listButton = new JButton("Listar Archivos Servidor");
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serverDirectory = serverDirTextField.getText();
                DEFAULT_SERVER_PORT = Integer.parseInt(serverPortTextField.getText());
                DEFAULT_SERVER_HOST = serverHostTextField.getText();
                peticion = "list";
                listarServer();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(clientDirLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        panel.add(clientDirTextField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        panel.add(clientDirButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(serverDirLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        panel.add(serverDirTextField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        panel.add(new JLabel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(serverHostLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(serverHostTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(serverPortLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(serverPortTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(filesServerLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.weighty = 1.0; // Ajusta el peso vertical para el scrollPane
        constraints.fill = GridBagConstraints.BOTH; // Ajusta el relleno para el scrollPane
        panel.add(scrollPane, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel(), constraints); // Espacio vacío

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(listButton);
        buttonPanel.add(syncButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void selectClientDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Carpeta del Cliente");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            clientDirTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void selectServerDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Carpeta del Servidor");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            serverDirTextField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    private void listarServer() {
        Socket socket;
        try {
            String serverHost = serverHostTextField.getText();
            int serverPort = Integer.parseInt(serverPortTextField.getText());
            socket = new Socket(serverHost, serverPort);

            System.out.println("Conectado al servidor.");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(peticion);
            objectOutputStream.writeObject(serverDirectory);

            List<String> filesServer = (List<String>) objectInputStream.readObject();

            System.out.println("Archivos listados.");
            StringBuilder filesServerText = new StringBuilder();
            for (String file : filesServer) {
                filesServerText.append(file).append("\n");
            }
            filesServerTextArea.setText(filesServerText.toString());

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void synchronizeFiles() {
        try {
            String serverHost = serverHostTextField.getText();
            int serverPort = Integer.parseInt(serverPortTextField.getText());
            Socket socket = new Socket(serverHost, serverPort);
            System.out.println("Conectado al servidor.");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // Obtain the list of files in the client's source directory
            List<String> clientFiles = obtenerListaArchivos(clientDirectory);

            // Obtain the files' contents and hashes
            Map<String, FileData> clientFilesData = obtenerDatosArchivos(clientFiles, clientDirectory);

            // Send the map of file names, contents, and hashes to the server
            objectOutputStream.writeObject(peticion);
            objectOutputStream.writeObject(serverDirectory);
            objectOutputStream.writeObject(clientFilesData);

            // Receive the list of files to receive and send from the server
            List<String> filesToReceive = (List<String>) objectInputStream.readObject();
            List<String> filesToSend = (List<String>) objectInputStream.readObject();

            // Send the modified files to the server
            enviarArchivosAlServidor(socket, clientFilesData, filesToReceive);

            // Receive the missing files from the server
            recibirArchivosDelServidor(socket, filesToSend);

            System.out.println("Archivos enviados y recibidos correctamente.");

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<String> obtenerListaArchivos(String directory) throws IOException {
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

    private Map<String, FileData> obtenerDatosArchivos(List<String> files, String clientDirectory) throws IOException {
        Map<String, FileData> filesData = new HashMap<>();
        for (String file : files) {
            String filePath = clientDirectory + File.separator + file;
            byte[] content = obtenerContenidoArchivo(filePath);
            String hash = calcularHashArchivo(content);

            filesData.put(file, new FileData(content, hash));
        }
        return filesData;
    }

    private void enviarArchivosAlServidor(Socket socket, Map<String, FileData> clientFilesData, List<String> filesToReceive) throws IOException {
        for (String fileName : filesToReceive) {
            FileData fileData = clientFilesData.get(fileName);
            enviarArchivo(socket, fileName, fileData.getContent());
        }
    }

    private void enviarArchivo(Socket socket, String fileName, byte[] fileContent) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(fileName);
        dataOutputStream.writeInt(fileContent.length);
        dataOutputStream.write(fileContent);
    }

    private void recibirArchivosDelServidor(Socket socket, List<String> filesToSend) throws IOException {
        for (String fileName : filesToSend) {
            recibirArchivo(socket, fileName);
        }
    }

    private void recibirArchivo(Socket socket, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(clientDirectory + File.separator + fileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
    }

    private byte[] obtenerContenidoArchivo(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    private String calcularHashArchivo(byte[] content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(content);

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
}
