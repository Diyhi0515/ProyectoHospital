package Entidades;

public class Sala {
    private int numHabitacion;
    private String estadoLimpieza;
    private String disponibilidad;
    private int id;
    public Sala(int numHabitacion, String estadoLimpieza, String disponibilidad, int id){
        this.numHabitacion = numHabitacion;
        this.estadoLimpieza = estadoLimpieza;
        this.disponibilidad = disponibilidad;
        this.id = id;
    }
    public Sala(){}
    public int getNumHabitacion() {
        return numHabitacion;
    }

    public String getEstadoLimpieza() {
        return estadoLimpieza;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setEstadoLimpieza(String estadoLimpieza) {
        this.estadoLimpieza = estadoLimpieza;
    }
}
