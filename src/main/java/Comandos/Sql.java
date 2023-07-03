package Comandos;

import Conexion.ConectarBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Sql {

    static private ConectarBase cb = new ConectarBase();

    static private Connection cn = cb.conectarMySQL();

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
            sql = "UPDATE Departamento set cantPersonal = cantPersonal + 1 WHERE id = " + valores[9];
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
        sql = "UPDATE Departamento set numCamas = numCamas + 1 WHERE id = " + valores[3];
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
        sql = "UPDATE Departamento set cantPersonal = cantPersonal + 1 WHERE id = " + valores[1];
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
        //Persona per = new Persona();
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
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.experiencia,a.cargo,a.responsabilidad, a.dep_id, d.tipoSala  FROM Administrador a JOIN Persona p ON a.per_ci = p.ci JOIN Departamento d ON a.dep_id = d.id;" ;
        }
        if (tableName.equals("Medico")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.nLicMedica,a.especialidad FROM Medico a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
        if (tableName.equals("Farmaceutico")){
            query = "SELECT a.per_ci,p.nombre,p.apellidoP,p.apellidoM,p.salario,p.fechaContratacion,a.horario FROM Farmaceutico a JOIN Persona p ON a.per_ci = p.ci;" ;
        }
        if (tableName.equals("Asignado")){
            query = "SELECT a.med_per_ci,a.dep_id,p.nombre,apellidoP ,d.tipoSala  FROM Asignado a JOIN Medico m ON a.med_per_ci = m.per_ci JOIN Departamento d ON a.dep_id = d.id JOIN Persona p ON m.per_ci = p.ci;" ;
        }
        if (tableName.equals("Sala")){
            query = "SELECT a.numHabitacion,a.estadoLimpieza,a.disponibilidad,a.dep_id ,d.tipoSala  FROM Sala a JOIN Departamento d ON a.dep_id = d.id ;" ;
        }
        if (tableName.equals("Ingredientes")){
            query = "SELECT i.med_id,i.ingrediente,m.nombre  FROM Ingredientes i JOIN Medicamento m ON i.med_id = m.id ;" ;
        }
        if (tableName.equals("Entrega")){
            query = "SELECT e.prov_nit,e.med_id, e.precio, e.cantidad , p.nombre ,m.nombre  FROM Entrega e JOIN Medicamento m ON e.med_id = m.id JOIN Proveedor p ON e.prov_nit = p.nit ;" ;
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

        System.out.println(tabla);
        if(tabla.equals("Administrador")) {
            System.out.println("-------------------------");
            String sql = "UPDATE Departamento SET cantPersonal = cantPersonal - 1 WHERE id = (SELECT dep_id FROM (SELECT a.dep_id FROM Administrador a JOIN Departamento d ON a.dep_id = d.id WHERE a.per_ci =" +id+")subquery);";
            System.out.println(sql);
            cb.ejecutarSQL(sql);
        }
        if(tabla.equals("Asignado") ) {
            String sql = "UPDATE Departamento set cantPersonal = cantPersonal - 1 WHERE id = " + id;
            cb.ejecutarSQL(sql);
        }
        if(tabla.equals("Sala") ) {
            String sql = "UPDATE Departamento SET numCamas = numCamas - 1 WHERE id = (SELECT dep_id FROM (SELECT a.dep_id FROM Sala a JOIN Departamento d ON a.dep_id = d.id WHERE a.numHabitacion =" +id+")subquery);";
            cb.ejecutarSQL(sql);
        }
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


