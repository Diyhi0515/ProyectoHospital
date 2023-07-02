package Entidades;

public class Ingredientes{
    private int id;
    private String ingredientes;
    public Ingredientes(int id, String ingredientes){
        this.id = id;
        this.ingredientes = ingredientes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
}
