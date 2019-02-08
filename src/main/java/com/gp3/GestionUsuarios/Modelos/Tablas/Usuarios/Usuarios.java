package com.gp3.GestionUsuarios.Modelos.Tablas.Usuarios;

public class Usuarios {

    private int id;
    private String usuario;
    private String password;
    private int role;
    private String nombre="";
    private String apellidos="";
    private String email="";

    public Usuarios() {
    }

    public Usuarios(int id, String usuario, String password, int role, String nombre, String apellidos, String email) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.role = role;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
