package Entidades;

public class Certificaciones {
   private int ci;
   private String certificacion;
   public Certificaciones(int ci, String certificacion){
       this.ci=ci;
       this.certificacion = certificacion;
   }

    public int getCi() {
        return ci;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }
}
