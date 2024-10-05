package murach.sql;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionPool {

    private ConnectionPool() {}

    public static Connection getConnection() {
        try {
            Dotenv dotenv = Dotenv.load();
            String driverName = dotenv.get("DRIVER");
            String dbURL = dotenv.get("DB_URL");
            String username = dotenv.get("DB_USERNAME");
            String password = dotenv.get("DB_PASSWORD");
            Class.forName(driverName);
            assert dbURL != null;
            return DriverManager.getConnection(dbURL, username, password);
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}