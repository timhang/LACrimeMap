import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MultipleMySQLConnections {
    MultipleMySQLConnections() {};
    public static void main(String[] args) {
        // Connection details
        String databaseName = "your_database_name";
        String user = "root";
        String password = "your_password";

        // Connection URLs for three MySQL instances
        String url1 = "jdbc:mysql://localhost:3306/CrimeData";
        String url2 = "jdbc:mysql://localhost:3307/CrimeData";
        String url3 = "jdbc:mysql://localhost:3308/CrimeData";

        // Try-with-resources to automatically close connections after use
        try (Connection conn1 = DriverManager.getConnection(url1, user, "crimedata1");
             Connection conn2 = DriverManager.getConnection(url2, user, "crimedata2");
             Connection conn3 = DriverManager.getConnection(url3, user, "crimedata3")) {

            System.out.println("Successfully connected to MySQL instance on port 3306");
            // Perform database operations for instance 1...

            System.out.println("Successfully connected to MySQL instance on port 3307");
            // Perform database operations for instance 2...

            System.out.println("Successfully connected to MySQL instance on port 3308");
            // Perform database operations for instance 3...

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
