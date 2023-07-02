package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EquipoMedico {
    private int cod;
    private String mantenimiento;
    private String estado;
    private String tipo;
    private Date fechaAdquisison;
    private int dep_id;
    private int prov_nit;
    public EquipoMedico(int cod, String mantenimiento, String estado, String tipo, Date fechaDeAquisicion, int id, int nit){
        this.cod = cod;
        this.mantenimiento = mantenimiento;
        this.estado = estado;
        this.tipo = tipo;
        this.fechaAdquisison = fechaDeAquisicion;
        this.dep_id = id;
        this.prov_nit = nit;
    }
    public EquipoMedico(){};
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(String mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAdquisison() {
        return fechaAdquisison;
    }

    public void setFechaAdquisison(Date fechaAdquisison) {
        this.fechaAdquisison = fechaAdquisison;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public int getProv_nit() {
        return prov_nit;
    }

    public void setProv_nit(int prov_nit) {
        this.prov_nit = prov_nit;
    }

    /*public EquipoMedico getDepartamento(int id){
        ConectarBase conexion =new ConectarBase();
        EquipoMedico eqm =new EquipoMedico();
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM EquipoMedico WHERE cod = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                eqm.setCod(rs.getInt("cod"));
                eqm.setMantenimiento(rs.getString("mantenimiento"));
                eqm.setEstado(rs.getString("estado"));
                eqm.setTipo(rs.getString("tipo"));
                eqm.setFechaAdquisison(rs.getDate("fechaAdquisision"));
                eqm.setDep_id(rs.getInt("dep_id"));
                eqm.setProv_nit(rs.getInt("prov_nit"));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eqm;
    }*/

}
