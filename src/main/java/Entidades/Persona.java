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
    private Date fechaContatacion;
    public Persona(int ci,String nombre, String apellidoP, String apellidoM, float salario, Date fechaContatacion){
        this.ci = ci;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.salario = salario;
        this.fechaContatacion = fechaContatacion;
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

    public Date getFechaContatacion() {
        return fechaContatacion;
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

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setFechaContatacion(Date fechaContatacion) {
        this.fechaContatacion = fechaContatacion;
    }
    /*public static Persona getPersona(int id){
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
                per.setSalario(rs.getFloat("salario"));
                per.setFechaContatacion(rs.getDate("fechaContratacion"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return per;
    }*/

    public void insertarPersona(){
        try {
            ConectarBase con=new ConectarBase();
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "INSERT INTO Persona(ci,nombre,apellidoP,apellidoM, salario, fechaContratacion) VALUES(?,?,?,?,?,?)");
            ps.setInt(1, ci);
            ps.setString(2, nombre);
            ps.setString(3, apellidoP);
            ps.setString(4, apellidoM);
            ps.setFloat(5,salario);
            ps.setDate(6, (java.sql.Date) fechaContatacion);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarPersona(int id){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "delete from Persona where ci = ?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarNombre(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Persona  \n" +
                            "    SET nombre = ?   where ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarApellidoP(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Persona  \n" +
                            "    SET apellidoP = ?   where ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarApellidoM(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Persona  \n" +
                            "    SET apellidoM = ?   where ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarSalario(int ci, float s){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Persona  \n" +
                            "    SET responsabilidad = ?   where ci = ?");
            ps.setFloat(1, s);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarFechaContratacion(int ci, Date dt){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Persona  \n" +
                            "    SET fechaContratacion = ?   where ci = ?");
            ps.setDate(1, (java.sql.Date) dt);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return "Persona{" +
                "ci=" + ci +
                ", nombre='" + nombre + '\'' +
                ", apellidoP='" + apellidoP+ '\'' +
                ", apellidoM='" + apellidoM + '\'' +
                ", salario='" + salario + '\'' +
                ", fechaContratacion='" + fechaContatacion + '\'' +
                '}';
    }
}
