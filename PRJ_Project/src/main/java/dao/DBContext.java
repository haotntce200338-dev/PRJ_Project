package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static final String SERVER_NAME = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE = "CinemaHub";
    private static final String USER = "sa";
    private static final String PASSWORD = "Admin123@";

    private static final String URL =
            "jdbc:sqlserver://"
            + SERVER_NAME + ":" + PORT
            + ";databaseName=" + DATABASE
            + ";encrypt=true"
            + ";trustServerCertificate=true";

    public Connection getConnection() {

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

}