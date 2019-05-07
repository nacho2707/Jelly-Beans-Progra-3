package com.jellybeans.entidades;

public class Usuario {
    private Integer id;
    private String codigo;
    private String contrasena;

    public Usuario(Integer id, String codigo, String contrasena) {
        this.id = id;
        this.codigo = codigo;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
