package Entidades;

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

}
