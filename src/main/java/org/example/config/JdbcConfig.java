package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {

    private final static String url = "jdbc:postgresql://localhost:5432/jdbc";
    private final static String username = "postgres";
    private final static String password = "Aliaskar";


    public static Connection getConnection (){
        Connection connection = null;
        try {
            return DriverManager.getConnection(url, username, password);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
