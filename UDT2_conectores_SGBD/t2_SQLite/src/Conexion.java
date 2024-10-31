public class CreateDB {

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    
    public class Conexion {
        private static final String URL = "jdbc:sqlite:videoclub.db";
        private static Connection conexion;
    

        public static Connection getConexion() {
            if (conexion == null) {
                try {
                    conexion = DriverManager.getConnection(URL);
                } catch (SQLException e) {
                    System.out.println("Error al establecer la conexión: " + e.getMessage());
                }
            }
            return conexion;
        }
    }

    public class Videoclub {
        public static void main(String[] args) {
            CrearTablas.main(args);
        }
    }
}