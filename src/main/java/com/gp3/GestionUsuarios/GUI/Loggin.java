package com.gp3.GestionUsuarios.GUI;

import com.gp3.GestionUsuarios.Modelos.GUI.JTextFieldLimit;

import javax.swing.*;
import java.awt.*;

public class Loggin extends JFrame{

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogIn;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JLabel lblLoading;


    /**
     * Create the application.
     */
    public Loggin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.nativeLookAndFeel();
        setBounds(100, 100, 300, 270);
        setTitle("Gestion Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblLoading = new JLabel(new ImageIcon(this.getClass().getResource("/com/gp3/Resources/Icons/loading2.gif")));
        lblLoading.setBounds(90, 46, 100, 100);
        lblLoading.setVisible(false);
        getContentPane().add(lblLoading);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsuario.setBounds(42, 46, 200, 25);
        getContentPane().add(txtUsuario);
        txtUsuario.setDocument(new JTextFieldLimit(20));

        btnLogIn = new JButton("Log in");
        btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogIn.setBounds(42, 170, 200, 44);
        getContentPane().add(btnLogIn);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBounds(42, 121, 200, 25);
        getContentPane().add(txtPassword);

        lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsuario.setBounds(42, 15, 63, 20);
        getContentPane().add(lblUsuario);

        lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(42, 90, 63, 20);
        getContentPane().add(lblPassword);

        setLocationRelativeTo(null);

    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JButton getBtnLogIn() {
        return btnLogIn;
    }

    public void setBtnLogIn(JButton btnLogIn) {
        this.btnLogIn = btnLogIn;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JLabel getLblPassword() {
        return lblPassword;
    }

    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }

    public JLabel getLblLoading() {
        return lblLoading;
    }

    public void setLblLoading(JLabel lblLoading) {
        this.lblLoading = lblLoading;
    }

    private void nativeLookAndFeel() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

