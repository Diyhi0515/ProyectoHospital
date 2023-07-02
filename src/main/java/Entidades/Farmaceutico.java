package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Farmaceutico extends Persona{
    private int per_ci;
    private String horario;
    public Farmaceutico(int ci, String nombre, String apellidoP, String apellidoM, float salario, Date fechaContatacion, String horario){
        super(ci, nombre, apellidoP, apellidoM, salario, fechaContatacion);
        insertarPersona();
        this.per_ci = ci;
        this.horario = horario;
    }
    public Farmaceutico(){}

    public int getPer_Ci() {
        return per_ci;
    }

    public String getHorario() {
        return horario;
    }

    public void setPer_Ci(int ci) {
        this.per_ci = ci;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    public static Farmaceutico getFarmaceutico(int id){
        ConectarBase conexion =new ConectarBase();
        Farmaceutico fm = new Farmaceutico();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Farmaceutico WHERE per_ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                fm.setPer_Ci(rs.getInt("per_ci"));
               fm.setHorario(rs.getString("horario"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fm;
    }

    public void insertarFarmaceutico(){
        try {
            ConectarBase con=new ConectarBase();
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "INSERT INTO Farmaceutico(farm_per_ci,) VALUES(?,?)");
            ps.setInt(1, per_ci);
            ps.setString(2, horario);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarFarmaceutico(int id){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "delete from Farmaceutico where per_ci = ?");
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modificarHorario(int ci, String n){
        ConectarBase con = new ConectarBase();
        try {
            PreparedStatement ps = con.conectarMySQL().prepareStatement(
                    "UPDATE Farmaceutico  \n" +
                            "    SET horario = ?   where per_ci = ?");
            ps.setString(1, n);
            ps.setInt(2,ci);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
