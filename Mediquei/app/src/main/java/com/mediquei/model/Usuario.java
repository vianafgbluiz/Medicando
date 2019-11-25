package com.mediquei.model;

import java.sql.Date;

public class Usuario {
    private int idUser;
    private String nomeUser;
    private String emailUser;
    private String senhaUser;
    private Character ativoUser;
    private Date dateUser;


    /*Construtores*/
    public Usuario() {
    }

    public Usuario(String emailUser, String senhaUser) {
        this.emailUser = emailUser;
        this.senhaUser = senhaUser;
    }

    public Usuario(String nomeUser, String emailUser, String senhaUser) {
        this.nomeUser = nomeUser;
        this.emailUser = emailUser;
        this.senhaUser = senhaUser;
    }

    /*Getters and Setters*/
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    public Character getAtivoUser() {
        return ativoUser;
    }

    public void setAtivoUser(Character ativoUser) {
        this.ativoUser = ativoUser;
    }

    public Date getDateUser() {
        return dateUser;
    }

    public void setDateUser(Date dateUser) {
        this.dateUser = dateUser;
    }
}
