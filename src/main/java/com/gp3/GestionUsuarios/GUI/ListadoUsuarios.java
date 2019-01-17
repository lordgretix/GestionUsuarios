/*
 * Created by JFormDesigner on Tue Jan 01 13:37:50 CET 2019
 */

package com.gp3.GestionUsuarios.GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author o6863265urykmpdv
 */
public class ListadoUsuarios extends JFrame{

    private JLabel lblLoading;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - taxpkhqr
    private JScrollPane scrollPane1;
    private JTable tableUsuarios;
    private JTextField txtBuscar;
    private JLabel lblBuscar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnAdd;
    private JButton btnBorrar;

    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public ListadoUsuarios() {
        initComponents();
    }

    private void initComponents() {
        this.nativeLookAndFeel();
        setMinimumSize(new Dimension(550, 300));
        setTitle("Gestion Usuarios");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - taxpkhqr
        scrollPane1 = new JScrollPane();
        tableUsuarios = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        txtBuscar = new JTextField();
        lblBuscar = new JLabel();
        btnBuscar = new JButton();
        btnModificar = new JButton();
        btnAdd = new JButton();
        btnBorrar = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- tableUsuarios ----
            tableUsuarios.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "ID", "Usuario", "Rol"
                }
            ));
            tableUsuarios.setAlignmentX(5.5F);
            tableUsuarios.setRowHeight(25);
            tableUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
            scrollPane1.setViewportView(tableUsuarios);
        }

        //---- txtBuscar ----
        txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));

        //---- lblBuscar ----
        lblBuscar.setText("Buscar Usuario");
        lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));

        //---- btnBuscar ----
        btnBuscar.setText("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));

        //---- btnModificar ----
        btnModificar.setText("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));

        //---- btnAdd ----
        btnAdd.setText("A\u00f1adir");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdd.setForeground(new Color(0, 199, 24));

        //---- btnBorrar ----
        btnBorrar.setText("Borrar");
        btnBorrar.setForeground(Color.red);
        btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                            .addComponent(btnAdd)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnModificar)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBorrar))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(lblBuscar)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                    .addGap(24, 24, 24))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblBuscar)
                    .addGap(3, 3, 3)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)
                        .addComponent(btnBorrar)
                        .addComponent(btnModificar)
                        .addComponent(btnAdd))
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    private void nativeLookAndFeel(){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public JLabel getLblLoading() {
        return lblLoading;
    }

    public void setLblLoading(JLabel lblLoading) {
        this.lblLoading = lblLoading;
    }

    public JScrollPane getScrollPane1() {
        return scrollPane1;
    }

    public void setScrollPane1(JScrollPane scrollPane1) {
        this.scrollPane1 = scrollPane1;
    }

    public JTable getTableUsuarios() {
        return tableUsuarios;
    }

    public void setTableUsuarios(JTable tableUsuarios) {
        this.tableUsuarios = tableUsuarios;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JLabel getLblBuscar() {
        return lblBuscar;
    }

    public void setLblBuscar(JLabel lblBuscar) {
        this.lblBuscar = lblBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public void setBtnBorrar(JButton btnBorrar) {
        this.btnBorrar = btnBorrar;
    }
}
