import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Oracle Database Connected!");
            return con;
        } catch (SQLException e) {
            System.out.println("Database Connection failed!");
            e.printStackTrace();
            return null;
        }
    }
    
}
