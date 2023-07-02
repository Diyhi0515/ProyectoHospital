package Entidades;

public class Medicamento {
    private int id;
    private String nombre;
    private String tipo;
    private static Ingredientes[] ingredientes;
    private static int  cant;
    public Medicamento(int id, String nombre, String tipo){
       this.id = id;
       this.nombre = nombre;
       this.tipo = tipo;
       cant = 0;
       ingredientes = new Ingredientes[cant];
    }
    public void agregarMedicamento(int llave, Ingredientes i){
        if(id == llave){
            ingredientes[cant] = i;
            cant++;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
