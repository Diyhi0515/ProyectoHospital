package Entidades;

public class Farmaceutico {
    private int ci;
    private String horario;
    public Farmaceutico(int ci, String horario){
        this.ci = ci;
        this.horario = horario;
    }

    public int getCi() {
        return ci;
    }

    public String getHorario() {
        return horario;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
