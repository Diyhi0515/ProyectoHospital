package Entidades;

public class Entrega {
    private int nit;
    private int id;
    private float precio;
    private int cantidad;
    public Entrega(int nit, int id, float precio, int cantidad){
        this.nit = nit;
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
