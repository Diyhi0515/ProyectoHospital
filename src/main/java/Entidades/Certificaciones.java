package Entidades;

public class Certificaciones extends Farmaceutico{
   private int farm_per_ci;
   private String certificacion;
   public Certificaciones(int ci, String certificacion){

       this.farm_per_ci=ci;
       this.certificacion = certificacion;
   }

    public int getFarm_per_ci() {
        return farm_per_ci;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setFarm_per_ci(int farm_per_ci) {
        this.farm_per_ci = farm_per_ci;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

}
