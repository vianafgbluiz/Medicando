package com.mediquei.model;

public class Farmacias {
    /*Atributos do Banco de Dados*/
    private int farmId;
    private String farmNome;
    private String farmRazaoSocial;
    private String farmCnpj;
    private String farmTel;

    /*Construtores*/
    public Farmacias(int farmId, String farmNome, String farmRazaoSocial, String farmCnpj, String farmTel) {
        this.farmId = farmId;
        this.farmNome = farmNome;
        this.farmRazaoSocial = farmRazaoSocial;
        this.farmCnpj = farmCnpj;
        this.farmTel = farmTel;
    }

    public Farmacias() {

    }

    /*Getters and Setters*/
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

    public String getFarmRazaoSocial() {
        return farmRazaoSocial;
    }

    public void setFarmRazaoSocial(String farmRazaoSocial) {
        this.farmRazaoSocial = farmRazaoSocial;
    }

    public String getFarmCnpj() {
        return farmCnpj;
    }

    public void setFarmCnpj(String farmCnpj) {
        this.farmCnpj = farmCnpj;
    }

    public String getFarmTel() {
        return farmTel;
    }

    public void setFarmTel(String farmTel) {
        this.farmTel = farmTel;
    }
}
