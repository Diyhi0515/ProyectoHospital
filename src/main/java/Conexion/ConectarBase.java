package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBase {
    private String host = "127.0.0.1";
    private String port = "3306";
    private String database = "Hospital";
    private String user = "root";
    private String pass = "150503";
            //"toor";

    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
            conn = DriverManager.getConnection(connectionString, user, pass);
            System.out.println("Conexion MySQL - Java EXITOSA");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
