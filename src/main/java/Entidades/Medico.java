package Entidades;

public class Medico {
    private int ci;
    private int nLicMedica;
    private String especialidad;
    public Medico(int ci, int nLicMedica, String especialidad){
        this.ci = ci;
        this.nLicMedica = nLicMedica;
        this.especialidad = especialidad;
    }

    public int getCi() {
        return ci;
    }

    public int getnLicMedica() {
        return nLicMedica;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setnLicMedica(int nLicMedica) {
        this.nLicMedica = nLicMedica;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
