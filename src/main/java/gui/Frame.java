package gui;

import Comandos.Sql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class Frame extends JFrame implements ActionListener {

    JTable jTable;
    JScrollPane consultas;

    JPanel panelConsultas;

    JTextField[] atributos;
    JLabel[] atributosNombres;

    JMenuBar menuBarra;
    JButton btnAgregar;
    JButton btnModificar;
    JButton btnEliminar;

    JComboBox<String> entidades;

    private JMenu menuArchivo;
    private JMenuItem itemSalir;

    public Frame() throws SQLException {

        panelConsultas = new JPanel();
        panelConsultas.setSize(600,300);
        panelConsultas.setBackground(Color.PINK);
        add(panelConsultas);
        panelConsultas.setLocation(0,300);

        jTable = Sql.consultaTotal("proveedor");
        consultas = new JScrollPane(jTable);

        crearComboBox();

        JLabel titulo1 = new JLabel("Atributos");
        JLabel titulo2 = new JLabel("Acciones");

        titulo1.setFont(new Font("Arial", Font.BOLD, 24));
        titulo2.setFont(new Font("Arial", Font.BOLD, 24));

        add(titulo1);
        add(titulo2);

        titulo1.setBounds(100,0,150,50);
        titulo2.setBounds(450,0,150,50);

        declararMenu();

        actualizarAtributos("proveedor");
        panelConsultas.add(consultas);
        inicializarBotones();
        botones();
        setBackground(Color.PINK);
        iniciarPantalla();

    }
    private void iniciarPantalla() {
        setLayout(null);
        setTitle("Hospital");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void declararMenu(){
        menuBarra = new JMenuBar();

        menuArchivo = new JMenu("Archivo");

        itemSalir = new JMenuItem("Salir");

        itemSalir.addActionListener(this);

        setJMenuBar(menuBarra);

        menuBarra.add(menuArchivo);

        menuArchivo.add(itemSalir);
    }

    private void actualizarTabla(String tabla) throws SQLException {
        panelConsultas.remove(consultas);
        jTable.setModel(Sql.consultaTotal(tabla).getModel());
        consultas = new JScrollPane(jTable);
        panelConsultas.add(consultas);
        panelConsultas.updateUI();
    }

    private void inicializarBotones(){
        btnAgregar = new JButton();
        btnEliminar = new JButton();
        btnModificar = new JButton();

        btnAgregar.setText("Agregar");
        btnModificar.setText("Modificar");
        btnEliminar.setText("Eliminar");


        add(btnAgregar);
        add(btnModificar);
        add(btnEliminar);


        btnAgregar.setBounds(450,50,100,30);
        btnModificar.setBounds(450,100,100,30);
        btnEliminar.setBounds(450,150,100,30);

    }
    private void actualizarAtributos(String tabla) throws SQLException {
        String[] listaAtributos = Sql.getAtributos(tabla);

        if (atributosNombres != null){
            for (int i = 0; i < atributosNombres.length; i++) {
                remove(atributos[i]);
                remove(atributosNombres[i]);
            }
        }
        atributos = new JTextField[listaAtributos.length];
        atributosNombres = new JLabel[listaAtributos.length];


        int y = 40;
        for (int i = 0; i < listaAtributos.length; i++) {
            atributos[i] = new JTextField();
            atributosNombres[i] = new JLabel(listaAtributos[i], SwingConstants. RIGHT);
            add(atributos[i]);
            add(atributosNombres[i]);
            atributos[i].setBounds(130,(i+1)*y,70,20);
            atributosNombres[i].setBounds(10,(i+1)*y,120,20);
        }
    }
    public void botones(){
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] temporal = new String[atributos.length];
                for (int i = 0; i < atributos.length; i++) {
                    temporal[i] = atributos[i].getText();
                }
                System.out.println("sirve");
                if ((entidades.getSelectedItem()).equals("Proveedor")){
                    Sql.insertProveedor(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Administrador")){

                }
                if ((entidades.getSelectedItem()).equals("Departamento")){
                    Sql.insertDepartamento(temporal);
                }
                if ((entidades.getSelectedItem()).equals("EquipoMedico")){
                    Sql.insertEquipoMedico(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Sala")){
                    Sql.insertSala(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Farmaceutico")){

                }
                if ((entidades.getSelectedItem()).equals("Certificaciones")){
                    Sql.insertCertificaciones(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Medico")){

                }
                if ((entidades.getSelectedItem()).equals("Asignado")){
                    Sql.insertAsignado(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Medicamento")){
                    Sql.insertMedicamento(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Ingredientes")){
                    Sql.insertIngredientes(temporal);
                }
                if ((entidades.getSelectedItem()).equals("Entrega")){
                    Sql.insertEntrega(temporal);
                }


                try {
                    actualizarTabla((String)entidades.getSelectedItem());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] temporal = new String[atributos.length];
                String[] temporal2 = new String[atributosNombres.length];
                for (int i = 0; i < atributos.length; i++) {
                    temporal2[i] = atributosNombres[i].getText();
                    temporal[i] = atributos[i].getText();
                }
                if ((entidades.getSelectedItem()).equals("Proveedor")){
                    for (int i = 1; i < temporal.length; i++) {
                       if(!Objects.equals(temporal[i], "")){
                           Sql.modificar("Proveedor", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                       }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Administrador")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Administrador", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Departamento")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Departamento", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("EquipoMedico")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("EquipoMedico", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Sala")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Sala", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Farmaceutico")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Farmaceutico", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Certificaciones")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Certificaciones", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Medico")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Medico", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Asignado")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Asignado", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Medicamento")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Medicamento", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Ingredientes")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Ingredientes", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }
                if ((entidades.getSelectedItem()).equals("Entrega")){
                    for (int i = 1; i < temporal.length; i++) {
                        if(!Objects.equals(temporal[i], "")){
                            Sql.modificar("Entrega", temporal2[0],Integer.parseInt(temporal[0]), temporal2[i], temporal[i]);
                        }
                    }
                }


                try {
                    actualizarTabla((String)entidades.getSelectedItem());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] temporal = new String[atributos.length];
                String[] temporal2 = new String[atributosNombres.length];
                for (int i = 0; i < atributos.length; i++) {
                    temporal2[i] = atributosNombres[i].getText();
                    temporal[i] = atributos[i].getText();
                }
                if ((entidades.getSelectedItem()).equals("Proveedor")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Proveedor", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Administrador")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Administrador", temporal2[0] );

                }
                if ((entidades.getSelectedItem()).equals("Departamento")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Departamento", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("EquipoMedico")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"EquipoMedico", temporal2[0] );

                }
                if ((entidades.getSelectedItem()).equals("Sala")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Sala", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Farmaceutico")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Farmaceutico", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Certificaciones")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Certificaciones", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Medico")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Medico", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Asignado")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Asignado", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Medicamento")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Medicamento", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Ingredientes")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Ingredientes", temporal2[0] );
                }
                if ((entidades.getSelectedItem()).equals("Entrega")){
                    Sql.eliminar(Integer.parseInt(temporal[0]),"Entrega", temporal2[0] );
                }
            }
        });
    }

    public void crearComboBox(){
        entidades = new JComboBox<>();
        entidades.addItem("Proveedor");
        entidades.addItem("Administrador");
        entidades.addItem("Departamento");
        entidades.addItem("EquipoMedico");
        entidades.addItem("Sala");
        entidades.addItem("Farmaceutico");
        entidades.addItem("Certificaciones");
        entidades.addItem("Medico");
        entidades.addItem("Asignado");
        entidades.addItem("Medicamento");
        entidades.addItem("Ingredientes");
        entidades.addItem("Entrega");
        add(entidades);
        entidades.setBounds(250,200,130,30);

        entidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarAtributos((String)entidades.getSelectedItem());
                    actualizarTabla((String)entidades.getSelectedItem());
                    consultas.updateUI();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        }

    public static void main(String[] args) {
        try {
            Frame frame = new Frame();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemSalir) {
            System.exit(0);
        }
    }
}
