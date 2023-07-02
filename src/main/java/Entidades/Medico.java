package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Medico extends Persona{
    private int per_ci;
    private int nLicMedica;
    private String especialidad;
    public Medico(int ci, String nombre, String apellidoP, String apellidoM, float salario, Date fechaContatacion, int nLicMedica, String especialidad){
        super(ci, nombre, apellidoP, apellidoM, salario, fechaContatacion);
        this.per_ci = ci;
        this.nLicMedica = nLicMedica;
        this.especialidad = especialidad;
    }
    public Medico(){
    }

    public int getPer_Ci() {
        return per_ci;
    }

    public int getnLicMedica() {
        return nLicMedica;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setPer_Ci(int per_ci) {
        this.per_ci = per_ci;
    }

    public void setnLicMedica(int nLicMedica) {
        this.nLicMedica = nLicMedica;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public static Medico getMedico(int id){
        ConectarBase conexion =new ConectarBase();
        Medico md = new Medico();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Medico WHERE per_ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                md.setPer_Ci(rs.getInt("per_ci"));
                md.setnLicMedica(rs.getInt("nLicMedica"));
                md.setEspecialidad(rs.getString("especialidad"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return md;
    }
    public void eliminarMedico(int id){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "delete from Medico where per_ci = ?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarNLicMedica(int ci, int n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Medico  \n" +
                            "    SET nLicMedica = ?   where per_ci = ?");
            ps.setInt(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificarEspecialidad(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Medico  \n" +
                            "    SET especialidad = ?   where per_ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return "Medico{" +
                "ci=" + per_ci +
                ", especialidad='" + especialidad + '\'' +
                ", nLicMedica='" + nLicMedica + '\'' +
                '}';
    }
}
