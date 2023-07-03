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

    public static void insertPersona(String ci, String nombre, String apellidoP, String apellidoM, String salario, String fechaContratacion) {
        String sql = "INSERT INTO Persona VALUES(" + ci + ", '" + nombre + "', '" + apellidoP + "', '" + apellidoM + "', " + salario + ", '" + fechaContratacion + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertAdministrador(String[] valores) {
        if (!getExistePersona(Integer.parseInt(valores[0]))) {
            insertPersona(valores[0], valores[1], valores[2], valores[3], valores[4], valores[5]);
            String sql = "INSERT INTO Administrador VALUES(" + valores[0] + ", '" + valores[6] + "', '" + valores[7] + "', '" + valores[8] + "', " + valores[9] + ")";
            cb.ejecutarSQL(sql);
        }
    }

    public static void insertDepartamento(String[] valores) {
        String sql = "INSERT INTO Departamento VALUES(" + valores[0] + ", '" + valores[1] + "', '" + valores[2] + "', " + valores[3] + ", " + valores[4] + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertEquipoMedico(String[] valores) {
        String sql = "INSERT INTO EquipoMedico VALUES(" + valores[0] + ", '" + valores[1] + "', '" + valores[2] + "', '" + valores[3] + "', '" + valores[4] + "', " + valores[5] + ", " + valores[6] + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertSala(String[] valores) {
        String sql = "INSERT INTO Sala VALUES(" + valores[0] + ", '" + valores[1] + "', '" + valores[2] + "', " + valores[3] + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertFarmaceutico(String[] valores) {
        if (!getExistePersona(Integer.parseInt(valores[0]))) {
            insertPersona(valores[0], valores[1], valores[2], valores[3], valores[4], valores[5]);
            String sql = "INSERT INTO Farmaceutico VALUES(" + valores[0] + ", '" + valores[6] + "')";
            cb.ejecutarSQL(sql);
        }
    }

    public static void insertCertificaciones(String[] valores) {
        String sql = "INSERT INTO Certificaciones VALUES(" + valores[0] + ", '" + valores[1] + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertMedico(String[] valores) {
        if (!getExistePersona(Integer.parseInt(valores[0]))) {
            insertPersona(valores[0], valores[1], valores[2], valores[3], valores[4], valores[5]);
            String sql = "INSERT INTO Medico VALUES(" + valores[0] + ", " + valores[6] + ", '" + valores[7] + "')";
            cb.ejecutarSQL(sql);
        }
    }

    public static void insertAsignado(String[] valores) {
        String sql = "INSERT INTO Asignado VALUES(" + valores[0] + ", " + valores[1] + ")";
        cb.ejecutarSQL(sql);
    }

    public static void insertMedicamento(String[] valores) {
        String sql = "INSERT INTO Medicamento VALUES(" + valores[0] + ", '" + valores[1] + "', '" + valores[2] + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertIngredientes(String[] valores) {
        String sql = "INSERT INTO Ingredientes VALUES(" + valores[0] + ", '" + valores[1] + "')";
        cb.ejecutarSQL(sql);
    }

    public static void insertEntrega(String[] valores) {
        String sql = "INSERT INTO Entrega VALUES(" + valores[0] + ", " + valores[1] + ", " + valores[2] + ", " + valores[3] + ")";
        cb.ejecutarSQL(sql);
    }
    public static boolean getExistePersona(int id){
        ConectarBase conexion =new ConectarBase();
        Persona per = new Persona();
        boolean existe;
        try (Connection con = conexion.conectarMySQL()) {
            PreparedStatement consulta = con.prepareStatement("SELECT * FROM Persona WHERE ci = ?");
            consulta.setInt(1,id);
            ResultSet rs=consulta.executeQuery();
            while(rs.next()){
                System.out.println("ya existe una persona con el ID: " + id);
                return true;
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static String[] getAtributos(String tableName) throws SQLException {

        String query = "SELECT * FROM " + tableName;
        if (tableName.equals("Administrador")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.experiencia,a.cargo,a.responsabilidad,a.dep_id FROM Administrador a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
        if (tableName.equals("Medico")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.nLicMedica,a.especialidad FROM Medico a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
        if (tableName.equals("Farmaceutico")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.horario FROM Farmaceutico a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
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
        if (tableName.equals("Administrador")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.experiencia,a.cargo,a.responsabilidad, a.dep_id FROM Administrador a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
        if (tableName.equals("Medico")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.nLicMedica,a.especialidad FROM Medico a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
        if (tableName.equals("Farmaceutico")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.horario FROM Farmaceutico a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
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
            }


            while(resultSet.next()) {
                String[] tbdatos = new String[columnCount];

                for(int i = 1; i <= columnCount; ++i) {
                    String columnValue = resultSet.getString(i);
                    tbdatos[i - 1] = columnValue;
                }

                tblModel.addRow(tbdatos);
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
    public static void modificar(String tabla, String nomllave, int llave, String atributo, String cambio) {
        boolean isNumeric = (cambio != null && cambio.matches("[0-9]+"));
        boolean isFecha = (cambio != null && cambio.matches("^\\d{4}-\\d{2}-\\d{2}$"));
        String c = "";
        if (isNumeric) {
            //modifica numeros
            c = "UPDATE " + tabla + " SET " + atributo + " = " + cambio + " where " + nomllave + " = " + llave;
        }else if (isFecha){
            //Modifica la fecha
            c = "UPDATE " + tabla + " SET " + atributo + " = '" + cambio + "' where " + nomllave + " = " + llave;
        }else {
            //solo se puede editar atributos string
            c = "UPDATE " + tabla + " SET " + atributo + " = '" + cambio + "' where " + nomllave + " = " + llave;
            //"UPDATE Persona  \n" + SET apellidoM = ?   where ci = ?"
        }
        cb.ejecutarSQL(c);
    }


    public static void eliminar(int id, String tabla, String nomLlave){
        //"delete from Proveedor where nit = ?");
        String c = "delete from " + tabla +" where " +nomLlave +" = " +id;
        cb.ejecutarSQL(c);
    }
    public static void eliminarPorNombre(String nombre, String tabla, String nomLlave){
        //"delete from Proveedor where nit = ?");
        String c = "delete from " + tabla +" where " +nomLlave +" = '"  +nombre +"'";
        cb.ejecutarSQL(c);
    }
    public static void main(String[] args) {
       // modificar("proveedor", "nit", 9182, "nombre", "Favio");
    }
}


