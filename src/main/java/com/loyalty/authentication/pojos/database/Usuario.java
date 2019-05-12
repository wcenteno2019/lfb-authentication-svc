package com.loyalty.authentication.pojos.database;

import javax.persistence.*;

@Entity(name = "USUARIO")
public class Usuario {
    @Id
    @Column(name="usr_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="usr_usuario")
    private String usuario;

    @Column(name="usr_password")
    private String password;

    @Column(name="usr_nombre")
    private String nombreUsuario;
    @Column(name="usr_apellido")
    private String apellidoUsuario;
    @Column(name="usr_correo")
    private String correo;

    @Column(name = "usr_estado")
    private String estado;

    @Column(name = "usr_conteo_fallido")
    private int conteoBloqueo;

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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getConteoBloqueo() {
        return conteoBloqueo;
    }

    public void setConteoBloqueo(int conteoBloqueo) {
        this.conteoBloqueo = conteoBloqueo;
    }
}
