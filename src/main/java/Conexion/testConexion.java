package Conexion;

import Entidades.Proveedor;

public class testConexion {
    public static void main(String[] args) {
        ConectarBase cb = new ConectarBase();
        cb.conectarMySQL();
        Proveedor pv = Proveedor.getProveedor(123);
        System.out.println(pv);
    }
}
