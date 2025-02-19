package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the correct driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDDD", "root", "Vrb@08642");

            if (connection != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Database connection failed!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
