package edu.pucp.mechatronics.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection con;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lab01",
                "root",
                "root"
            );

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public Connection getConnection() {
        return con;
    }
}