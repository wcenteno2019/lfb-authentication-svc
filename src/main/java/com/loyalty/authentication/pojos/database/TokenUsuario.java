package com.loyalty.authentication.pojos.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "USUARIO_TOKEN")
public class TokenUsuario {
    @Id
    @Column(name="tkn_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "tkn_token")
    private String tkn;

    @Column(name = "tkn_usr_id")
    private int idUser;

    @Column(name = "tkn_date_expiration")
    private LocalDateTime fechaExpiracion;
     public  TokenUsuario(){}
    public TokenUsuario(String tkn, int idUser, LocalDateTime fechaExpiracion) {
        this.tkn = tkn;
        this.idUser = idUser;
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTkn() {
        return tkn;
    }

    public void setTkn(String tkn) {
        this.tkn = tkn;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

}
