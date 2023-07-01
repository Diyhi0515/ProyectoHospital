package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Persona {
    private int ci;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private float salario;
    private Date fechaContatavcion;
    public Persona(int ci,String nombre, String apellidoP, String apellidoM, float salario, Date fechaContatavcion){
        this.ci = ci;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.salario = salario;
        this.fechaContatavcion = fechaContatavcion;
    }
    public Persona(){};

    public int getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public float getSalario() {
        return salario;
    }

    public Date getFechaContatavcion() {
        return fechaContatavcion;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setFechaContatavcion(Date fechaContatavcion) {
        this.fechaContatavcion = fechaContatavcion;
    }
    public static Persona getPersona(int id){
        ConectarBase conexion =new ConectarBase();
        Persona per = new Persona();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Persona WHERE ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                per.setCi(rs.getInt("ci"));
                per.setNombre(rs.getString("nombre"));
                per.setApellidoP(rs.getString("apellidoP"));
                per.setApellidoM(rs.getString("apellidoM"));
                //per.setSalario(rs.getFloat(""));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return per;
    }

    /*public void insertarAdministrador(){
        try {
            ConectarBase con=new ConectarBase();
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "INSERT INTO Administrador(per_ci,experiecia,cargo,responsabilidad) VALUES(?,?,?,?)");
            ps.setInt(1, per_ci);
            ps.setString(2, experiencia);
            ps.setString(3, cargo);
            ps.setString(4,responsabilidad);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarAdministrador(int id){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "delete from Administrador where per_ci = ?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarExperiencia(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Administrador  \n" +
                            "    SET experiencia = ?   where per_ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarCargo(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Administrador  \n" +
                            "    SET cargo = ?   where per_ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarResponsabilidad(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Administrador  \n" +
                            "    SET responsabilidad = ?   where per_ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
