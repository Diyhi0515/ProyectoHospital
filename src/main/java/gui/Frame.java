package gui;

import Comandos.Sql;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Frame extends JFrame {

    JTable jTable;
    public Frame() throws SQLException {
        jTable = Sql.consultaTotal("proveedor");
        add(jTable);
        add(new JScrollPane(jTable)).setBounds(50,50, 300, 300);
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

    public static void main(String[] args) {
        try {
            Frame frame = new Frame();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
