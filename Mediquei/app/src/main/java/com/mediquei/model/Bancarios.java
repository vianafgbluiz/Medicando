package com.mediquei.model;

import java.io.Serializable;

public class Bancarios implements Serializable {
    /*TODO: Atributos*/
    private int banId;
    private int banIdBandeira;
    private String banNumero;
    private String banCodSeguranca;
    private String banNomeCartao;
    private int banIdUsuario;
    private String banValidade;
    private String banCPF;

    /*TODO: Construtor*/
    public Bancarios() {
    }

    /*TODO: Getters and Setters*/
    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public int getBanIdBandeira() {
        return banIdBandeira;
    }

    public void setBanIdBandeira(int banIdBandeira) {
        this.banIdBandeira = banIdBandeira;
    }

    public String getBanNumero() {
        return banNumero;
    }

    public void setBanNumero(String banNumero) {
        this.banNumero = banNumero;
    }

    public String getBanCodSeguranca() {
        return banCodSeguranca;
    }

    public void setBanCodSeguranca(String banCodSeguranca) {
        this.banCodSeguranca = banCodSeguranca;
    }

    public String getBanNomeCartao() {
        return banNomeCartao;
    }

    public void setBanNomeCartao(String banNomeCartao) {
        this.banNomeCartao = banNomeCartao;
    }

    public int getBanIdUsuario() {
        return banIdUsuario;
    }

    public void setBanIdUsuario(int banIdUsuario) {
        this.banIdUsuario = banIdUsuario;
    }

    public String getBanValidade() {
        return banValidade;
    }

    public void setBanValidade(String banValidade) {
        this.banValidade = banValidade;
    }

    public String getBanCPF() {
        return banCPF;
    }

    public void setBanCPF(String banCPF) {
        this.banCPF = banCPF;
    }
}
