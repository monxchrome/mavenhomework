package owu.mavenhomework.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:postgresql://localhost:54320/postgresql";
        String usr = "postgres";
        String pswrd = "postgres";

        try (Connection connection = DriverManager.getConnection(url, usr, pswrd)) {
            boolean isConnected = connection.isValid(5);
            System.out.println("Is connected?" + isConnected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
