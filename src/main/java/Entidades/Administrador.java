package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Administrador extends Persona {
    private int per_ci;
    private String experiencia;
    private String cargo;
    private String responsabilidad;
    //Persona p;
    public Administrador(int ci, String nombre, String apellidoP, String apellidoM, float salario, Date fechaContatacion, String experiencia, String cargo, String responsabilidad){
        super(ci,nombre,apellidoP,apellidoM,salario,fechaContatacion);
        insertarPersona();
        this.per_ci = ci;
        this.experiencia = experiencia;
        this.cargo = cargo;
        this.responsabilidad = responsabilidad;
    }
    public Administrador(){}

    public int getPer_Ci() {
        return per_ci;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public String getCargo() {
        return cargo;
    }

    public String getResponsabilidad() {
        return responsabilidad;
    }

    public void setPer_Ci(int ci) {
        this.per_ci = ci;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setResponsabilidad(String responsabilidad) {
        this.responsabilidad = responsabilidad;
    }
    /*public static Administrador getAdministrador(int id){
        ConectarBase conexion =new ConectarBase();
        Administrador ad = new Administrador();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Administrador WHERE per_ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                ad.setPer_Ci(rs.getInt("per_ci"));
                ad.setExperiencia(rs.getString("experiencia"));
                ad.setCargo(rs.getString("cargo"));
                ad.setResponsabilidad(rs.getString("responsabilidad"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ad;
    }*/

    public void insertarAdministrador(){
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
    }
    @Override
    public String toString() {
        return "Administrador{" +
                "ci=" + per_ci +
                //", nombre='" + getNombre() + '\'' +
                ", experiencia='" + experiencia + '\'' +
                ", cargo='" + cargo + '\'' +
                ", responsabilidad='" + responsabilidad + '\'' +
                '}';
    }

}
