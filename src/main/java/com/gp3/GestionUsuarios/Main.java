package com.gp3.GestionUsuarios;

import com.gp3.GestionUsuarios.Controladores.Eventos;
import com.gp3.GestionUsuarios.Controladores.UsuariosMannager;
import com.gp3.GestionUsuarios.GUI.ListadoUsuarios;
import com.gp3.GestionUsuarios.GUI.Loggin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;


public class Main {

    public final static Logger LOGGER = LogManager.getLogger("com.gp3.GestionUsuarios.Main");


    public static void main(String[] args) {
        System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "WARNING");
        System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");

        UsuariosMannager.getInstance();

        EventQueue.invokeLater(() -> {
            try {
                Loggin logWindow = new Loggin();
                logWindow.setVisible(true);

                Eventos.setLogginListenners(logWindow);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void controlWindow(){

        EventQueue.invokeLater(() -> {
            try {
                ListadoUsuarios gestionWindow = new ListadoUsuarios();
                gestionWindow.setVisible(true);

                Eventos.setListadoUsuariosListenners(gestionWindow);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}