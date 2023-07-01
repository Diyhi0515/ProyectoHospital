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

    public static Proveedor getProveedor(int id){
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
