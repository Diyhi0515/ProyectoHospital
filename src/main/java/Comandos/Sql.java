package Comandos;

import Conexion.ConectarBase;

import Entidades.*;

import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Sql {

    static private ConectarBase cb = new ConectarBase();;

    static private Connection cn = cb.conectarMySQL();;

    public Sql() {

    }

    public static void insertProveedor(String[] valores) {
        String sql = "INSERT INTO Proveedor VALUES(" + valores[0] + ", '" + valores[1] + "', " + valores[2] + ", '" + valores[3] + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertPersona(int ci, String nombre, String apellidoP, String apellidoM, int salario, String fechaContratacion) {
        String sql = "INSERT INTO Persona VALUES(" + ci + ", '" + nombre + "', '" + apellidoP + "', '" + apellidoM + "', " + salario + ", '" + fechaContratacion + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertAdministrador(int perCi, String nombre, String apellidoP, String apellidoM, int salario, String fechaContratacion, String experiencia, String cargo, String responsabilidad) {
        String sql = "INSERT INTO Administrador VALUES(" + perCi + ", '" + experiencia + "', '" + cargo + "', '" + responsabilidad + "')";
        insertPersona(perCi,nombre, apellidoP, apellidoM, salario, fechaContratacion);
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

    public static void insertFarmaceutico(int perCi, String nombre, String apellidoP, String apellidoM, int salario, String fechaContratacion,  String horario) {
        String sql = "INSERT INTO Farmaceutico VALUES(" + perCi + ", '" + horario + "')";
        insertPersona(perCi,nombre, apellidoP, apellidoM, salario, fechaContratacion);
        cb.ejecutarSQL(sql);
    }

    public static void insertCertificaciones(int farmPerCi, String certificacion) {
        String sql = "INSERT INTO Certificaciones VALUES(" + farmPerCi + ", '" + certificacion + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertMedico(int perCi, String nombre, String apellidoP, String apellidoM, int salario, String fechaContratacion,  int nLicMedica, String especialidad) {
        String sql = "INSERT INTO Medico VALUES(" + perCi + ", " + nLicMedica + ", '" + especialidad + "')";
        insertPersona(perCi,nombre, apellidoP, apellidoM, salario, fechaContratacion);
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

    public static String[] getAtributos(String tableName) throws SQLException {

        String query = "SELECT * FROM " + tableName;
        Statement st = cn.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            String[] str = new String[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                String columnName = metaData.getColumnName(i);
                str[i-1] = columnName;
            }
            return str;
        }catch (SQLException var11) {
            var11.printStackTrace();
            return null;
        }

    }


    public static JTable consultaTotal(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        Statement st = cn.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            JTable jTable = new JTable(0, 0);
            DefaultTableModel tblModel = (DefaultTableModel)jTable.getModel();

            for(int i = 1; i <= columnCount; ++i) {
                String columnName = metaData.getColumnName(i);
                tblModel.addColumn(columnName);
                System.out.print(columnName + "\t");
            }

            System.out.println();

            while(resultSet.next()) {
                String[] tbdatos = new String[columnCount];

                for(int i = 1; i <= columnCount; ++i) {
                    String columnValue = resultSet.getString(i);
                    tbdatos[i - 1] = columnValue;
                    System.out.print(columnValue + "\t");
                }

                tblModel.addRow(tbdatos);
                System.out.println();
            }

            resultSet.close();
            return new JTable(tblModel);
        } catch (SQLException var11) {
            var11.printStackTrace();
            return null;
        }
    }

    //Getters de entidades
    public Persona getPersona(int id){
        ConectarBase conexion =new ConectarBase();
        Persona per = new Persona();
        try (Connection con = conexion.conectarMySQL()) {
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
    public Proveedor getProveedor(int id){
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
    public  Medico getMedico(int id){
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
    public Farmaceutico getFarmaceutico(int id){
        Farmaceutico fm = new Farmaceutico();
        try (Connection con = cb.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Farmaceutico WHERE per_ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                fm.setPer_Ci(rs.getInt("per_ci"));
                fm.setHorario(rs.getString("horario"));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fm;
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
    public static Departamento getDepartamento(int id){
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
    //Modificar atributo
    public static void modificar(String tabla, String nomllave, int llave, String atributo, String cambio){
        boolean isNumeric = (cambio != null && cambio.matches("[0-9]+"));
        String c = "";
        if(isNumeric){
            //modifica numeros
            c = "UPDATE " + tabla + " SET " + atributo + " = " + cambio + " where " + nomllave + " = " + llave;
        }else {
            //solo se puede editar atributos string
            c = "UPDATE " + tabla + " SET " + atributo + " = '" + cambio + "' where " + nomllave + " = " + llave;
            //"UPDATE Persona  \n" + SET apellidoM = ?   where ci = ?"
        }
        cb.ejecutarSQL(c);
    }


    public void eliminar(int id, String tabla, String nomLlave){
        //"delete from Proveedor where nit = ?");
        String c = "delete from " + tabla +" where " +nomLlave +" = " +id;
        cb.ejecutarSQL(c);
    }
    public static void main(String[] args) {
       // modificar("proveedor", "nit", 9182, "nombre", "Favio");
    }
}


