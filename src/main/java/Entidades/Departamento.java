package Entidades;


import Conexion.ConectarBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Departamento {
    private int id;
    private String tipoSala;
    private String presupuesto;
    private int numCamas;
    private int cantPersonal;
    private int ci;
    public Departamento(int id, String tipoSala, String presupuesto, int numCamas, int cantPersonal, int ci ){
        this.id = id;
        this.tipoSala = tipoSala;
        this.presupuesto = presupuesto;
        this.numCamas = numCamas;
        this.cantPersonal = cantPersonal;
        this.ci = ci;
    }

    public Departamento(){}

    public int getId() {
        return id;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public int getCantPersonal() {
        return cantPersonal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public void setCantPersonal(int cantPersonal) {
        this.cantPersonal = cantPersonal;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }
    /*public static Departamento getDepartamento(int id){
        ConectarBase conexion =new ConectarBase();
        Departamento dp =new Departamento();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Departamento WHERE id = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                dp.setId(rs.getInt("id"));
                dp.setTipoSala(rs.getString("tipoSala"));
                dp.setPresupuesto(rs.getString("presupuesto"));
                dp.setNumCamas(rs.getInt("numCamamas"));
                dp.setCantPersonal(rs.getInt("cantPersonal"));
                dp.setCi(rs.getInt("ad_per_ci"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dp;
    }*/



}
