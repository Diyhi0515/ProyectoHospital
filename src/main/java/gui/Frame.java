package gui;

import Comandos.Sql;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Frame extends JFrame {

    JTable jTable;
    JScrollPane consultas;

    JPanel panelConsultas;

    JTextField[] atributos;
    JLabel[] atributosNombres;

    JButton btnAgregar;
    JButton btnModificar;
    JButton btnEliminar;



    public Frame() throws SQLException {

        panelConsultas = new JPanel();
        panelConsultas.setSize(600,300);
        panelConsultas.setBackground(Color.PINK);
        add(panelConsultas);
        panelConsultas.setLocation(0,300);

        jTable = Sql.consultaTotal("proveedor");
        consultas = new JScrollPane(jTable);

        JLabel titulo1 = new JLabel("Atributos");
        JLabel titulo2 = new JLabel("Acciones");

        titulo1.setFont(new Font("Arial", Font.BOLD, 24));
        titulo2.setFont(new Font("Arial", Font.BOLD, 24));

        add(titulo1);
        add(titulo2);

        titulo1.setBounds(100,0,150,50);
        titulo2.setBounds(450,0,150,50);

        actualizarAtributos("Administrador");
        inicializarBotones();
        panelConsultas.add(consultas);
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
        atributos = new JTextField[listaAtributos.length];
        atributosNombres = new JLabel[listaAtributos.length];

        int y = 40;
        for (int i = 0; i < listaAtributos.length; i++) {
            atributos[i] = new JTextField();
            atributosNombres[i] = new JLabel(listaAtributos[i]+": ", SwingConstants. RIGHT);
            atributos[i].setBounds(130,(i+1)*y,70,20);
            atributosNombres[i].setBounds(10,(i+1)*y,120,20);
            add(atributos[i]);
            add(atributosNombres[i]);

        }
    }
    public static void main(String[] args) {
        try {
            Frame frame = new Frame();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
