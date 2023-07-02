package Conexion;

import java.sql.*;

public class ConectarBase {
    private String host = "127.0.0.1";
    private String port = "3306";
    private String database = "Hospital";
    private String user = "root";

    //Le cambie el pass de 150503 a root
    private String pass = "root";
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
    public void ejecutarSQL(String sql) {
        try (Connection con = conectarMySQL()) {
            Statement consulta = con.createStatement();
            consulta.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void obtenerSQL(String sql) {
        try (Connection con = conectarMySQL()) {
            Statement consulta = con.createStatement();
            ResultSet rs=consulta.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("numero_id")+".- "+rs.getString("nombre"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
