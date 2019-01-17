package com.gp3.GestionUsuarios.Controladores;

import com.gp3.GestionUsuarios.Modelos.Tablas.Usuarios.Usuarios;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.gp3.GestionUsuarios.Main.LOGGER;

public class UsuariosMannager {

    private static SessionFactory factory = null;
    private static UsuariosMannager manager;
    private static Usuarios loggedUser = null;

    public static UsuariosMannager getInstance(){

        if(factory == null){
            manager = new UsuariosMannager();
        }

        return manager;
    }

    private UsuariosMannager() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            factory.close();
            JOptionPane.showMessageDialog(null, "No se ha podido conectar con el servidor", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean logIn(String username, String password){
        Session session = factory.openSession();

        boolean usingPassword = password.length()>0;

        Transaction trans = null;

        boolean logged = false;
        password = DigestUtils.sha256Hex(password);

        try{
            trans = session.beginTransaction();
            List users = session.createQuery("FROM Usuarios WHERE nombre = '"+username+"' AND password = '"+password+"'").list();

            if (users.size()>0){
                loggedUser = (Usuarios) users.get(0);
                logged = true;
                LOGGER.info("Session Iniciada (Username: "+username+")");
            }else{
                LOGGER.error("Fallo de inicio de session usuario (Username: "+username+") o contraseña (Using password: "+usingPassword+") invalidos");
            }

            trans.commit();

        }catch (HibernateException e) {
            if (trans!=null) trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return logged;
    }

    public void logOut(){
        LOGGER.info("Session Finalizada (Username: "+loggedUser.getNombre()+")");
        loggedUser = null;
    }

    public static boolean isLogged(){
        return loggedUser != null;
    }

    public static boolean isFactorySet(){
        return factory != null;
    }

    public ArrayList<Usuarios> getUsers(){

        ArrayList<Usuarios> usuarios = new ArrayList<>();

        Session session = factory.openSession();
        Transaction trans = null;

        try{
            trans = session.beginTransaction();
            List ul = session.createQuery("FROM Usuarios WHERE role>1").list();

            for(Object obj : ul){
                usuarios.add((Usuarios) obj);
            }

            trans.commit();

        }catch (HibernateException e) {
            if (trans!=null) trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return usuarios;
    }

    public Usuarios getUsuario(int id){
        Session session = factory.openSession();
        Transaction trans = null;

        Usuarios user=null;

        try{
            trans = session.beginTransaction();
            user = (Usuarios) session.createQuery("FROM Usuarios WHERE id="+id).list().get(0);

            trans.commit();

        }catch (HibernateException e) {
            if (trans!=null) trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public String getRoleAsString(int role){
        switch (role){
            case 1: return "Admin";
            case 2: return "Moderador";
            case 3: return "Usuario";
        }
        return null;
    }

    public void updateUsuario(Usuarios user){
        Session session = factory.openSession();
        Transaction trans = null;

        try{
            trans = session.beginTransaction();
            session.update(user);
            trans.commit();

            LOGGER.warn("Actualizado Usuario (Username: "+user.getNombre()+" , ID: "+user.getId()+") por el usuario (Username: "+loggedUser.getNombre()+" , ID: "+loggedUser.getId()+")");

        }catch (HibernateException e) {
            if (trans!=null) trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void createUsuario(Usuarios user){
        Session session = factory.openSession();
        Transaction trans = null;

        try{
            trans = session.beginTransaction();
            session.save(user);
            trans.commit();

            LOGGER.warn("Creado nuevo Usuario (Username: "+user.getNombre()+" , ID: "+user.getId()+") por el usuario (Username: "+loggedUser.getNombre()+" , ID: "+loggedUser.getId()+")");

        }catch (HibernateException e) {
            if (trans!=null) trans.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
            session.close();
        }
    }

    public void removeUsuario(Usuarios user){
        Session session = factory.openSession();
        Transaction trans = null;

        try{
            trans = session.beginTransaction();
            session.remove(user);
            trans.commit();

            LOGGER.warn("Borrado Usuario (Username: "+user.getNombre()+" , ID: "+user.getId()+") por el usuario (Username: "+loggedUser.getNombre()+" , ID: "+loggedUser.getId()+")");

        }catch (HibernateException e) {
            if (trans!=null) trans.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void closeFactory(){
        logOut();
        factory.close();
    }
}
