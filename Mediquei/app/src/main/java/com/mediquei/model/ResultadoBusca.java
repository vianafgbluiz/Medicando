package com.mediquei.model;

import java.io.Serializable;

public class ResultadoBusca implements Serializable {
    /*TODO: Dados para a TELA 'ResultadoBuscaActivity'*/
    private int medicId;
    private String medicNome;
    private int descId;
    private String descDes;
    private String descQuantidade;
    private String descReceita;
    private Double pPreco;
    private int farmId;
    private String farmNome;

    /*Construtor*/
    public ResultadoBusca(){

    }

    public ResultadoBusca(int medicId, String medicNome, int descId, String descDes, String descQuantidade, String descReceita, Double pPreco, int farmId, String farmNome) {
        this.medicId = medicId;
        this.medicNome = medicNome;
        this.descId = descId;
        this.descDes = descDes;
        this.descQuantidade = descQuantidade;
        this.descReceita = descReceita;
        this.pPreco = pPreco;
        this.farmId = farmId;
        this.farmNome = farmNome;
    }

    /*Getters and Setters*/
    public int getMedicId() {
        return medicId;
    }

    public void setMedicId(int medicId) {
        this.medicId = medicId;
    }

    public String getMedicNome() {
        return medicNome;
    }

    public void setMedicNome(String medicNome) {
        this.medicNome = medicNome;
    }

    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
    }

    public String getDescDes() {
        return descDes;
    }

    public void setDescDes(String descDes) {
        this.descDes = descDes;
    }

    public String getDescQuantidade() {
        return descQuantidade;
    }

    public void setDescQuantidade(String descQuantidade) {
        this.descQuantidade = descQuantidade;
    }

    public String getDescReceita() {
        return descReceita;
    }

    public void setDescReceita(String descReceita) {
        this.descReceita = descReceita;
    }

    public Double getpPreco() {
        return pPreco;
    }

    public void setpPreco(Double pPreco) {
        this.pPreco = pPreco;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getFarmNome() {
        return farmNome;
    }

    public void setFarmNome(String farmNome) {
        this.farmNome = farmNome;
    }


}
