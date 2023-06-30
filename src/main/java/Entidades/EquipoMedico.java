package Entidades;

import java.util.Date;

public class EquipoMedico {
    private int cod;
    private String mantenimiento;
    private String estado;
    private String tipo;
    private Date fechaAdquisison;
    private int id;
    private int nit;
    public EquipoMedico(int cod, String mantenimiento, String estado, String tipo, Date fechaDeAquisicion, int id, int nit){
        this.cod = cod;
        this.mantenimiento = mantenimiento;
        this.estado = estado;
        this.tipo = tipo;
        this.fechaAdquisison = fechaDeAquisicion;
        this.id = id;
        this.nit = nit;
    }
}
