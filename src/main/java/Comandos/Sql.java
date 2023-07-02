package Comandos;

import Conexion.ConectarBase;
import Entidades.*;
import org.w3c.dom.NodeList;

import java.sql.*;

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


    //geters

    public Persona getPersona(int id){
        Persona per = new Persona();
        try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Persona WHERE ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                per.setCi(rs.getInt("ci"));
                per.setNombre(rs.getString("nombre"));
                per.setApellidoP(rs.getString("apellidoP"));
                per.setApellidoM(rs.getString("apellidoM"));
                per.setSalario(rs.getFloat("salario"));
                per.setFechaContatacion(rs.getDate("fechaContratacion"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return per;
    }
    public  Proveedor getProveedor(int id){
        Proveedor buscado=new Proveedor();
        try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Proveedor WHERE nit = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                buscado.setNit(rs.getInt("nit"));
                buscado.setNombre(rs.getString("nombre"));
                buscado.setContacto(rs.getInt("contacto"));
                buscado.setDireccion(rs.getString("direccion"));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buscado;
    }
    public Administrador getAdministrador(int id){
        Administrador ad = new Administrador();
        try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Administrador WHERE per_ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                ad.setPer_Ci(rs.getInt("per_ci"));
                ad.setExperiencia(rs.getString("experiencia"));
                ad.setCargo(rs.getString("cargo"));
                ad.setResponsabilidad(rs.getString("responsabilidad"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ad;
    }
    public Medico getMedico(int id){
        Medico md = new Medico();
            try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Medico WHERE per_ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                md.setPer_Ci(rs.getInt("per_ci"));
                md.setnLicMedica(rs.getInt("nLicMedica"));
                md.setEspecialidad(rs.getString("especialidad"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return md;
    }

    public Departamento getDepartamento(int id){
        Departamento dp =new Departamento();
        try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Departamento WHERE id = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                dp.setId(rs.getInt("id"));
                dp.setTipoSala(rs.getString("tipoSala"));
                dp.setPresupuesto(rs.getString("presupuesto"));
                dp.setNumCamas(rs.getInt("numCamamas"));
                dp.setCantPersonal(rs.getInt("cantPersonal"));
                dp.setCi(rs.getInt("ad_per_ci"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dp;
    }
    public EquipoMedico getEquipoMedico(int id){
        EquipoMedico eqm =new EquipoMedico();
        try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM EquipoMedico WHERE cod = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                eqm.setCod(rs.getInt("cod"));
                eqm.setMantenimiento(rs.getString("mantenimiento"));
                eqm.setEstado(rs.getString("estado"));
                eqm.setTipo(rs.getString("tipo"));
                eqm.setFechaAdquisison(rs.getDate("fechaAdquisision"));
                eqm.setDep_id(rs.getInt("dep_id"));
                eqm.setProv_nit(rs.getInt("prov_nit"));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return eqm;
    }

    public static void main(String[] args) {


    }
}


