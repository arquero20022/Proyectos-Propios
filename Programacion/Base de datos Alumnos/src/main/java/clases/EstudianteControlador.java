package clases;


import clases.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EstudianteControlador {

    private Connection conexion;

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://alumnos.cwta8ieoctbb.eu-west-3.rds.amazonaws.com:3306/estudiantes";
        String usuario = "alumnos";
        String contrasena = "pass1234";
        conexion = DriverManager.getConnection(url, usuario, contrasena);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void insertarEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO estudiantes (dni, nombre, apellidos) VALUES (?, ?, ?)";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, estudiante.getDni());
        statement.setString(2, estudiante.getNombre());
        statement.setString(3, estudiante.getApellidos());
        statement.executeUpdate();
    }

    public List<Estudiante> consultarEstudiantes() throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Estudiante> estudiantes = new ArrayList<>();
        while (resultSet.next()) {
            String dni = resultSet.getString("dni");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            Estudiante estudiante = new Estudiante(dni, nombre, apellidos);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }

    public List<Estudiante> consultarEstudiantesDni(String dni) throws SQLException {
        String sql = "SELECT * FROM estudiantes  where dni like '"+ dni + "%'";

        // Crear un objeto PreparedStatement y establecer los parámetros
        PreparedStatement statement = conexion.prepareStatement(sql);
   
        ResultSet resultSet = statement.executeQuery();
        List<Estudiante> estudiantes = new ArrayList<>();
        while (resultSet.next()) {
            String dniBusqueda = resultSet.getString("dni");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            Estudiante estudiante = new Estudiante(dniBusqueda, nombre, apellidos);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }
    public List<Estudiante> consultarEstudiantesNombre(String Nombre) throws SQLException {
        String sql = "SELECT * FROM estudiantes  where nombre like '"+ Nombre + "%'";

        // Crear un objeto PreparedStatement y establecer los parámetros
        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        List<Estudiante> estudiantes = new ArrayList<>();
        while (resultSet.next()) {
            String dni = resultSet.getString("dni");
            String nombreBusca = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            Estudiante estudiante = new Estudiante(dni, nombreBusca, apellidos);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }
    public List<Estudiante> consultarEstudiantesApellidos(String Apellidos) throws SQLException {
        String sql = "SELECT * FROM estudiantes  where apellidos like '"+ Apellidos + "%'";

        // Crear un objeto PreparedStatement y establecer los parámetros
        PreparedStatement statement = conexion.prepareStatement(sql);
        
        ResultSet resultSet = statement.executeQuery();
        List<Estudiante> estudiantes = new ArrayList<>();
        while (resultSet.next()) {
            String dni = resultSet.getString("dni");
            String nombreBusca = resultSet.getString("nombre");
            String apellidosBusca = resultSet.getString("apellidos");
            Estudiante estudiante = new Estudiante(dni, nombreBusca, apellidosBusca);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }
    

    public void eliminarEstudiante(String dni) throws SQLException {
        // Crear la sentencia SQL para eliminar un estudiante por su DNI
        String sql = "DELETE FROM estudiantes WHERE dni = ?";

        // Crear un objeto PreparedStatement y establecer los parámetros
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1, dni);

        // Ejecutar la sentencia SQL
        stmt.executeUpdate();
    }

}
