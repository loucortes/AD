import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateDB {
    static final String user = "root";
    static final String password = "example";
    static final String url = "jdbc:mysql://localhost:3306";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE DATABASE luciano");
            System.out.println("Database created successfully");

            stmt.execute("CREATE TABLE luciano.users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(100), PRIMARY KEY(id))");
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}