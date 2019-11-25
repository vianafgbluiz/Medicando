package com.mediquei.model;

import java.io.Serializable;
import java.util.List;

public class ProdutoFinal implements Serializable {
    private String urlImagemFinal;
    private Double qtdadeEstrelasFinal;
    private List<Precos> listaPrecosFinal;

    private int medicIdFinal;
    private String medicNomeFinal;
    private int descIdFinal;
    private String descReceitaFinal;
    private Double pPrecoFinal;
    private int farmIdFinal;
    private String farmNomeFinal;

    /*Adicionais*/
    private Double taxaEntregaFinal;
    private int qtdadeProdutoFinal;
    private int qtdadeParcelasFinal;

    /*Construtor*/
    public ProdutoFinal (){
        qtdadeProdutoFinal = 1;
        qtdadeParcelasFinal = 1;
    }

    /*Getters and Setters*/

    public String getUrlImagemFinal() {
        return urlImagemFinal;
    }

    public void setUrlImagemFinal(String urlImagemFinal) {
        this.urlImagemFinal = urlImagemFinal;
    }

    public Double getQtdadeEstrelasFinal() {
        return qtdadeEstrelasFinal;
    }

    public void setQtdadeEstrelasFinal(Double qtdadeEstrelasFinal) {
        this.qtdadeEstrelasFinal = qtdadeEstrelasFinal;
    }

    public List<Precos> getListaPrecosFinal() {
        return listaPrecosFinal;
    }

    public void setListaPrecosFinal(List<Precos> listaPrecosFinal) {
        this.listaPrecosFinal = listaPrecosFinal;
    }

    public int getQtdadeProdutoFinal() {
        return qtdadeProdutoFinal;
    }

    public void setQtdadeProdutoFinal(int qtdadeProdutoFinal) {
        this.qtdadeProdutoFinal = qtdadeProdutoFinal;
    }

    public Double getTaxaEntregaFinal() {
        return taxaEntregaFinal;
    }

    public void setTaxaEntregaFinal(Double taxaEntregaFinal) {
        this.taxaEntregaFinal = taxaEntregaFinal;
    }

    public int getMedicIdFinal() {
        return medicIdFinal;
    }

    public void setMedicIdFinal(int medicIdFinal) {
        this.medicIdFinal = medicIdFinal;
    }

    public String getMedicNomeFinal() {
        return medicNomeFinal;
    }

    public void setMedicNomeFinal(String medicNomeFinal) {
        this.medicNomeFinal = medicNomeFinal;
    }

    public String getDescReceitaFinal() {
        return descReceitaFinal;
    }

    public void setDescReceitaFinal(String descReceitaFinal) {
        this.descReceitaFinal = descReceitaFinal;
    }

    public Double getpPrecoFinal() {
        return pPrecoFinal;
    }

    public void setpPrecoFinal(Double pPrecoFinal) {
        this.pPrecoFinal = pPrecoFinal;
    }

    public int getFarmIdFinal() {
        return farmIdFinal;
    }

    public void setFarmIdFinal(int farmIdFinal) {
        this.farmIdFinal = farmIdFinal;
    }

    public String getFarmNomeFinal() {
        return farmNomeFinal;
    }

    public void setFarmNomeFinal(String farmNomeFinal) {
        this.farmNomeFinal = farmNomeFinal;
    }

    public int getQtdadeParcelasFinal() {
        return qtdadeParcelasFinal;
    }

    public void setQtdadeParcelasFinal(int qtdadeParcelasFinal) {
        this.qtdadeParcelasFinal = qtdadeParcelasFinal;
    }

    public int getDescIdFinal() {
        return descIdFinal;
    }

    public void setDescIdFinal(int descIdFinal) {
        this.descIdFinal = descIdFinal;
    }
}
