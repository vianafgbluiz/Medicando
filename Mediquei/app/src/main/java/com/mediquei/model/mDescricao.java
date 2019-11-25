package com.mediquei.model;

public class mDescricao {
    /*Atributos do Banco de Dados*/
    private int descId;
    private int descIdMedic;
    private String descDsc;
    private String descQuantidades;
    private String descReceita;

    /*Auxiliadores*/
    private String descNome;
    private Double descPreco;

    /*Construtores*/
    public mDescricao() {
    }

    public mDescricao(int descId, int descIdMedic, String descDsc, String descQuantidades, String descReceita, String descNome, Double descPreco) {
        this.descId = descId;
        this.descIdMedic = descIdMedic;
        this.descDsc = descDsc;
        this.descQuantidades = descQuantidades;
        this.descReceita = descReceita;
        this.descNome = descNome;
        this.descPreco = descPreco;
    }

    /*Getters and Setters*/
    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
    }

    public int getDescIdMedic() {
        return descIdMedic;
    }

    public void setDescIdMedic(int descIdMedic) {
        this.descIdMedic = descIdMedic;
    }

    public String getDescDsc() {
        return descDsc;
    }

    public void setDescDsc(String descDsc) {
        this.descDsc = descDsc;
    }

    public String getDescReceita() {
        return descReceita;
    }

    public void setDescReceita(String descReceita) {
        this.descReceita = descReceita;
    }

    public String getDescNome() {
        return descNome;
    }

    public void setDescNome(String descNome) {
        this.descNome = descNome;
    }

    public Double getDescPreco() {
        return descPreco;
    }

    public void setDescPreco(Double descPreco) {
        this.descPreco = descPreco;
    }

    public String getDescQuantidades() {
        return descQuantidades;
    }

    public void setDescQuantidades(String descQuantidades) {
        this.descQuantidades = descQuantidades;
    }
}
