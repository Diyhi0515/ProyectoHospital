package Entidades;

import java.util.Date;

public class Persona {
    private int ci;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private int salario;
    private Date fechaContatavcion;
    public Persona(int ci,String nombre, String apellidoP, String apellidoM, int salario, Date fechaContatavcion){
        this.ci = ci;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.salario = salario;
        this.fechaContatavcion = fechaContatavcion;
    }

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

    public int getSalario() {
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
}
