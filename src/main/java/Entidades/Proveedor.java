package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proveedor {
    private  int nit;
    private String nombre;
    private  int contacto;
    private String direccion;

    public Proveedor(int nit,String nombre,int contacto, String dirección){
        this.nit = nit;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = dirección;
    }
    public Proveedor(){};

    public int getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public int getContacto() {
        return contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
  //Guardar datos de la tabla al objeto por medio del id
    /*public static Proveedor getProveedor(int id){
        ConectarBase conexion =new ConectarBase();
        Proveedor buscado=new Proveedor();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Proveedor WHERE nit = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                buscado.setNit(rs.getInt("nit"));
                buscado.setNombre(rs.getString("nombre"));
                buscado.setContacto(rs.getInt("contacto"));
                buscado.setDireccion(rs.getString("direccion"));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buscado;
    }*/
    //insertar datos a la tabla en la base
    public void insertarProveedor(){
        try {
            ConectarBase con=new ConectarBase();
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "INSERT INTO Proveedor(nit,nombre,contacto,direccion) VALUES(?,?,?,?)");
            ps.setInt(1, nit);
            ps.setString(2, nombre);
            ps.setInt(3,contacto);
            ps.setString(4,direccion);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarProveedor(int id){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "delete from Proveedor where nit = ?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarNombre(int nit, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Proveedor  \n" +
                            "    SET nombre = ?   where nit = ?");
            ps.setString(1, n);
            ps.setInt(2,nit);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarContacto(int nit, int co){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Proveedor  \n" +
                            "    SET contacto = ?   where nit = ?");
            ps.setInt(1, co);
            ps.setInt(2,nit);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarDireccion(int nit, String di){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Proveedor  \n" +
                            "    SET direccion = ?   where nit = ?");
            ps.setString(1, di);
            ps.setInt(2,nit);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nit=" + nit +
                ", nombre='" + nombre + '\'' +
                ", contacyto='" + contacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
