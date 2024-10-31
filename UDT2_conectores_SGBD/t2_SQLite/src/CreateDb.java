import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDb {
    private static final String URL = "jdbc:sqlite:videoclub.db";
    private static Connection conexion;

    public static void main(String[] args) {
        try {
            // Establecer la conexión a la base de datos
            conexion = DriverManager.getConnection(URL);

            // Crear las tablas
            crearTablas();

            // Mostrar las tablas
            mostrarTablas();
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }

    private static void crearTablas() throws SQLException {
        Statement statement = conexion.createStatement();

        // Crear la tabla de clientes
        String sqlClientes = "CREATE TABLE clientes (" +
                "cliente_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "direccion VARCHAR(255), " +
                "telefono VARCHAR(20), " +
                "email VARCHAR(100), " +
                "fecha_registro DATE " +
                ")";
        statement.executeUpdate(sqlClientes);

        // Crear la tabla de películas
        String sqlPeliculas = "CREATE TABLE peliculas (" +
                "pelicula_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "titulo VARCHAR(255) NOT NULL, " +
                "descripcion TEXT, " +
                "director VARCHAR(100), " +
                "anio_lanzamiento YEAR, " +
                "duracion INT, " +
                "stock INT DEFAULT 0, " +
                "fecha_agregado DATE " +
                ")";
        statement.executeUpdate(sqlPeliculas);

        // Crear la tabla de categorías de películas
        String sqlCategorias = "CREATE TABLE categorias (" +
                "categoria_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre_categoria VARCHAR(100) NOT NULL " +
                ")";
        statement.executeUpdate(sqlCategorias);

        // Crear la tabla intermedia para relacionar películas con categorías
        String sqlPeliculasCategorias = "CREATE TABLE peliculas_categorias (" +
                "pelicula_id INT, " +
                "categoria_id INT, " +
                "PRIMARY KEY (pelicula_id, categoria_id), " +
                "FOREIGN KEY (pelicula_id) REFERENCES peliculas(pelicula_id) ON DELETE CASCADE, " +
                "FOREIGN KEY (categoria_id) REFERENCES categorias(categoria_id) ON DELETE CASCADE " +
                ")";
        statement.executeUpdate(sqlPeliculasCategorias);

        // Crear la tabla de alquileres
        String sqlAlquileres = "CREATE TABLE alquileres (" +
                "alquiler_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cliente_id INT, " +
                "pelicula_id INT, " +
                "empleado_id INT, " +
                "fecha_alquiler DATE, " +
                "fecha_devolucion DATE, " +
                "FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id), " +
                "FOREIGN KEY (pelicula_id) REFERENCES peliculas(pelicula_id), " +
                "FOREIGN KEY (empleado_id) REFERENCES empleados(empleado_id) " +
                ")";
        statement.executeUpdate(sqlAlquileres);

        statement.close();

    }

    private static void mostrarTablas() throws SQLException {
        Statement statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");

        System.out.println("Tablas en la base de datos:");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }
}