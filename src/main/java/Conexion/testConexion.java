package Conexion;

import Comandos.Sql;
import Entidades.Administrador;
import Entidades.Persona;
import Entidades.Proveedor;

public class testConexion {
    public static void main(String[] args) {
        ConectarBase cb = new ConectarBase();
        cb.conectarMySQL();
        Sql sql = new Sql();
        //Proveedor pv = Proveedor.getProveedor(123);
        //System.out.println(pv);
        /*Proveedor pv2 = new Proveedor(124, "Bagó", 335633, "Calle Equipetrol");
        pv2.insertarProveedor();
        System.out.println(Proveedor.getProveedor(124));*/
        //Proveedor pv3 = new Proveedor();
        /*pv3.eliminarProveedor(124);
        System.out.println(Proveedor.getProveedor(124));*/
        //pv.modificarNombre(123, "Sentirse Bien");
        //pv.modificarContacto(124, 311122);
        //pv.modificarDireccion(124, "Calle Tartagal");
        //System.out.println(Proveedor.getProveedor(124));
        //System.out.println(Administrador.getAdministrador(1));
        Persona p = sql.getPersona(1);
        System.out.println(p);
        sql.modificar("Persona","ci", 1, "Baldelomar", "apellidoP");
        System.out.println(sql.getPersona(1));
    }
}
