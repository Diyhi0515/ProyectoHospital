package Comandos;

import Conexion.ConectarBase;
import org.w3c.dom.NodeList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {

    static private ConectarBase cb = new ConectarBase();;

    static private Connection cn = cb.conectarMySQL();;

    public Sql() {

    }

    public static void insertProveedor(int nit, String nombre, int contacto, String direccion) {
        String sql = "INSERT INTO Proveedor VALUES(" + nit + ", '" + nombre + "', " + contacto + ", '" + direccion + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertPersona(int ci, String nombre, String apellidoP, String apellidoM, int salario, String fechaContratacion) {
        String sql = "INSERT INTO Persona VALUES(" + ci + ", '" + nombre + "', '" + apellidoP + "', '" + apellidoM + "', " + salario + ", '" + fechaContratacion + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertAdministrador(int perCi, String experiencia, String cargo, String responsabilidad) {
        String sql = "INSERT INTO Administrador VALUES(" + perCi + ", '" + experiencia + "', '" + cargo + "', '" + responsabilidad + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertDepartamento(int id, String tipoSala, String presupuesto, int numCamas, int cantPersonal, int adPerCi) {
        String sql = "INSERT INTO Departamento VALUES(" + id + ", '" + tipoSala + "', '" + presupuesto + "', " + numCamas + ", " + cantPersonal + ", " + adPerCi + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertEquipoMedico(int cod, String mantenimiento, String estado, String tipo, String fechaAdquisicion, int depId, int provNit) {
        String sql = "INSERT INTO EquipoMedico VALUES(" + cod + ", '" + mantenimiento + "', '" + estado + "', '" + tipo + "', '" + fechaAdquisicion + "', " + depId + ", " + provNit + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertSala(int numHabitacion, String estadoLimpieza, String disponibilidad, int depId) {
        String sql = "INSERT INTO Sala VALUES(" + numHabitacion + ", '" + estadoLimpieza + "', '" + disponibilidad + "', " + depId + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertFarmaceutico(int perCi, String horario) {
        String sql = "INSERT INTO Farmaceutico VALUES(" + perCi + ", '" + horario + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertCertificaciones(int farmPerCi, String certificacion) {
        String sql = "INSERT INTO Certificaciones VALUES(" + farmPerCi + ", '" + certificacion + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertMedico(int perCi, int nLicMedica, String especialidad) {
        String sql = "INSERT INTO Medico VALUES(" + perCi + ", " + nLicMedica + ", '" + especialidad + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertAsignado(int medPerCi, int depId) {
        String sql = "INSERT INTO Asignado VALUES(" + medPerCi + ", " + depId + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertMedicamento(int id, String nombre, String tipo) {
        String sql = "INSERT INTO Medicamento VALUES(" + id + ", '" + nombre + "', '" + tipo + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertIngredientes(int medId, String ingrediente) {
        String sql = "INSERT INTO Ingredientes VALUES(" + medId + ", '" + ingrediente + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertEntrega(int provNit, int medId, float precio, int cantidad) {
        String sql = "INSERT INTO Entrega VALUES(" + provNit + ", " + medId + ", " + precio + ", " + cantidad + ")";
        cb.ejecutarSQL(sql);
    }



    public static void main(String[] args) {


    }
}


