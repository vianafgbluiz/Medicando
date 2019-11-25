package com.mediquei.model;

import java.io.Serializable;

public class Endereco implements Serializable {
    /*TODO: Atributos*/
    private int endId;
    private String endTipo;
    private int endIdfk;
    private String endCep;
    private String endUF;
    private String endCidade;
    private String endBairro;
    private String endLogradouro;
    private String endComplemento;
    private String endNumero;

    /*Apenas para calculos das Taxas*/
    private String taxaCalculada;
    private Double taxaEscolhida;

    /* TODO: Construtores */
    public Endereco() {
    }

    /* TODO: Getters and Setters */
    public int getEndId() {
        return endId;
    }
    public void setEndId(int endId) {
        this.endId = endId;
    }

    public String getEndTipo() {
        return endTipo;
    }
    public void setEndTipo(String endTipo) {
        this.endTipo = endTipo;
    }

    public int getEndIdfk() {
        return endIdfk;
    }
    public void setEndIdfk(int endIdfk) {
        this.endIdfk = endIdfk;
    }

    public String getEndCep() {
        return endCep;
    }
    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndUF() {
        return endUF;
    }
    public void setEndUF(String endUF) {
        this.endUF = endUF;
    }

    public String getEndCidade() {
        return endCidade;
    }
    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndBairro() {
        return endBairro;
    }
    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }
    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public String getEndComplemento() {
        return endComplemento;
    }
    public void setEndComplemento(String endComplemento) {
        this.endComplemento = endComplemento;
    }

    public String getEndNumero() {
        return endNumero;
    }
    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    /*Apenas para calculo das taxas*/
    public String getTaxaCalculada() {
        return taxaCalculada;
    }

    public void setTaxaCalculada(String taxaCalculada) {
        this.taxaCalculada = taxaCalculada;
    }

    public Double getTaxaEscolhida() {
        return taxaEscolhida;
    }

    public void setTaxaEscolhida(Double taxaEscolhida) {
        this.taxaEscolhida = taxaEscolhida;
    }
}
