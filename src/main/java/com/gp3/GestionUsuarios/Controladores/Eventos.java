package com.gp3.GestionUsuarios.Controladores;

import com.gp3.GestionUsuarios.GUI.ListadoUsuarios;
import com.gp3.GestionUsuarios.GUI.Loggin;
import com.gp3.GestionUsuarios.GUI.ModificarUsuario;
import com.gp3.GestionUsuarios.GUI.NuevoUsuario;
import com.gp3.GestionUsuarios.Main;
import com.gp3.GestionUsuarios.Modelos.Tablas.Usuarios.Usuarios;
import org.hibernate.exception.ConstraintViolationException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class Eventos {

    public static void setLogginListenners(Loggin window){

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            boolean dispatched = false;
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER && !dispatched){
                    window.getBtnLogIn().doClick();
                    dispatched=true;
                }
                return false;
            }
        });

        // Boton Login
        window.getBtnLogIn().addActionListener(e -> {

            window.getLblLoading().setVisible(true);

            // Log In
            new Thread(() -> {

                if (UsuariosMannager.getInstance().logIn(window.getTxtUsuario().getText(), String.valueOf(window.getTxtPassword().getPassword()))) {
                    Main.controlWindow();
                    window.dispose();
                }else{
                    window.getLblLoading().setVisible(false);
                    JOptionPane.showMessageDialog(window, "Usuario o contraseña no validos", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }).start();
        });

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                window.setVisible(false);
                if(!UsuariosMannager.isLogged()){
                    UsuariosMannager.getInstance().closeFactory();
                }
            }
        });
    }

    public static void setListadoUsuariosListenners(ListadoUsuarios window){

        WindowAdapter wAdapter = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                refreshUsersTable(window);
            }
        } ;


        window.getBtnBuscar().addActionListener(e -> {

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(window.getTableUsuarios().getModel());
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+window.getTxtBuscar().getText()));
            window.getTableUsuarios().setRowSorter(sorter);
        });

        window.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    window.getBtnBuscar().doClick();
                }
            }
        });

        window.getBtnAdd().addActionListener(e -> {

            NuevoUsuario nu = new NuevoUsuario(window);
            setNuevoUsuarioListenners(nu);
            nu.setVisible(true);

            nu.addWindowListener(wAdapter);

        });

        window.getBtnModificar().addActionListener(e -> {
            ModificarUsuario mu = new ModificarUsuario(window);

            setModificarUsuariosListenners(mu);

            Usuarios user = UsuariosMannager.getInstance().getUsuario(Integer.parseInt(window.getTableUsuarios().getValueAt(window.getTableUsuarios().getSelectedRow(), 0).toString()));
            mu.setUser(user);
            mu.getTxtUsuario().setText(user.getNombre());
            mu.getComboRol().setSelectedIndex(user.getRole()-2);
            mu.setVisible(true);

            mu.addWindowListener(wAdapter);
        });

        window.getBtnBorrar().addActionListener(e -> {

            Object[] options = {"Aceptar","Cancelar"};
            int op = JOptionPane.showOptionDialog(window,
                    "¿Estas seguro de que deseas eliminar este usuario?",
                    "Borrar Usuario",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[1]);

            if(op == JOptionPane.YES_OPTION){
                int id = Integer.parseInt(window.getTableUsuarios().getValueAt(window.getTableUsuarios().getSelectedRow(), 0).toString());
                Usuarios user = UsuariosMannager.getInstance().getUsuario(id);
                UsuariosMannager.getInstance().removeUsuario(user);
                refreshUsersTable(window);
            }
        });

        window.getTableUsuarios().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount()==2){
                    window.getBtnModificar().doClick();
                }
            }
        });

        window.getTableUsuarios().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE){
                    window.getBtnModificar().doClick();
                }
            }
        });

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UsuariosMannager.getInstance().closeFactory();
            }
        });


        refreshUsersTable(window);
    }

    private static void refreshUsersTable(ListadoUsuarios window){

        new Thread(() -> {

            DefaultTableModel model = ((DefaultTableModel) window.getTableUsuarios().getModel());
            while (model.getRowCount()>0){
                model.removeRow(model.getRowCount()-1);
            }
            UsuariosMannager.getInstance().getUsers().forEach(user ->
                model.addRow(new Object[]{
                        user.getId(),
                        user.getNombre(),
                        UsuariosMannager.getInstance().getRoleAsString(user.getRole())
                }));
        }
        ).start();
    }

    public static void setNuevoUsuarioListenners(NuevoUsuario window){

        window.getBtnCrear().addActionListener(e -> {
            if(!isValidPassword(window.getTxtPassword().getPassword(), window.getTxtPasswordRepeat().getPassword())) return;
            try{
                UsuariosMannager.getInstance().createUsuario(window.getUser());
                window.dispose();
            }
            catch(ConstraintViolationException cve){
                JOptionPane.showMessageDialog(null, "El usuario ya existe", "Error: "+cve.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {

            if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                window.dispose();
            }
            return false;
        });

    }

    public static void setModificarUsuariosListenners(ModificarUsuario window){

        window.getCbPassword().addActionListener(e -> {
            boolean status = window.getCbPassword().isSelected();
            window.getTxtPassword().setEnabled(status);
            window.getTxtPasswordRepeat().setEnabled(status);
        });

        window.getBtnModificar().addActionListener(e -> {
            if(!isValidPassword(window.getTxtPassword().getPassword(), window.getTxtPasswordRepeat().getPassword())) return;
            UsuariosMannager.getInstance().updateUsuario(window.getUser());
            window.dispose();
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {

            if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
                window.dispose();
            }
            return false;
        });

    }

    private static boolean isValidPassword(char[] password , char[] repeat){

        if(!Arrays.equals(password, repeat)){
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(password.length<6 || repeat.length<6){
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }


}
