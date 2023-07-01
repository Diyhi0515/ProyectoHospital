package Conexion;

import Entidades.Proveedor;

public class testConexion {
    public static void main(String[] args) {
        ConectarBase cb = new ConectarBase();
        cb.conectarMySQL();
        Proveedor pv = Proveedor.getProveedor(123);
        System.out.println(pv);

        Proveedor pv2 = new Proveedor(124, "Bagó", 335633, "Calle Equipetrol");
        pv2.insertarProveedor();

        System.out.println(Proveedor.getProveedor(124));


    }
}
