package JFrame;
import java.sql.*;
public class DBConnection {
    static Connection con = null;
    public static Connection getConnection() {
        try {
                 // Initializing Drivers
                Class.forName("oracle.jdbc.driver.OracleDriver");
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                
                // Connecting with database
                final String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
                final String user = "system";
                final String pass = "sys";
                con = DriverManager.getConnection(oracleUrl, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
