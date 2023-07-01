package Entidades;

import Conexion.ConectarBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrador {
    private int ci;
    private String experiencia;
    private String cargo;
    private String responsabilidad;
    public Administrador(int ci, String experiencia, String cargo, String responsabilidad){
        this.ci = ci;
        this.experiencia = experiencia;
        this.cargo = responsabilidad;
        this.responsabilidad = responsabilidad;
    }
    public Administrador(){}

    public int getCi() {
        return ci;
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

    public void setCi(int ci) {
        this.ci = ci;
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


}
