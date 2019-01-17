package com.gp3.GestionUsuarios.Modelos.Tablas.Usuarios;

public class Usuarios {

    private int id;
    private String nombre;
    private String password;
    private int role;

    public Usuarios() {
    }

    public Usuarios(int id, String nombre, String password, int role) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
