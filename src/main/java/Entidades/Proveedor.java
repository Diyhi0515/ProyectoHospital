package Entidades;

public class Proveedor {
    private  int nit;
    private String nombre;
    private  int contacto;
    private String dirección;
    public Proveedor(int nit,String nombre,int contacto, String dirección){
        this.nit = nit;
        this.nombre = nombre;
        this.contacto = contacto;
        this.dirección = dirección;
    }

    public int getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public int getContacto() {
        return contacto;
    }

    public String getDirección() {
        return dirección;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }
}
