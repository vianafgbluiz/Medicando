package com.mediquei.model;

import java.io.Serializable;

public class Pedidos implements Serializable {

    private String pedId;
    private int pedIdSituacao;
    private int pedIdUser;
    private int pedIdUserEndereco;
    private int pedIdUserBancarios;
    private int pedIdFarmacia;
    private int pedIdMedicamento;
    private int pedQtdadeMedicamento;
    private Double pedValorTotal;
    private int pedIdFormaPagamento;
    private int pedQtdadeParcelas;
    private String pedDataSolicitacao;
    private String pedDataEntregue;
    private String pedLinkReceita;
    private Double pedTaxaEntrega;
    private int pedIdDesc;

    /*Atributos Auxiliares*/
    private String nomeRemedio;
    private String nomeFarmacia;
    private String numeroCartao;

    /*Constrtutor*/
    public Pedidos() {
        pedIdSituacao = 1;
        pedIdFormaPagamento = 1;
        pedLinkReceita = "";
    }

    /*Getters and Setters*/
    public String getPedId() {
        return pedId;
    }
    public void setPedId(String pedId) {
        this.pedId = pedId;
    }

    public int getPedIdSituacao() {
        return pedIdSituacao;
    }
    public void setPedIdSituacao(int pedIdSituacao) {
        this.pedIdSituacao = pedIdSituacao;
    }

    public int getPedIdUser() {
        return pedIdUser;
    }
    public void setPedIdUser(int pedIdUser) {
        this.pedIdUser = pedIdUser;
    }

    public int getPedIdUserEndereco() {
        return pedIdUserEndereco;
    }
    public void setPedIdUserEndereco(int pedIdUserEndereco) {
        this.pedIdUserEndereco = pedIdUserEndereco;
    }

    public int getPedIdUserBancarios() {
        return pedIdUserBancarios;
    }
    public void setPedIdUserBancarios(int pedIdUserBancarios) {
        this.pedIdUserBancarios = pedIdUserBancarios;
    }

    public int getPedIdFarmacia() {
        return pedIdFarmacia;
    }
    public void setPedIdFarmacia(int pedIdFarmacia) {
        this.pedIdFarmacia = pedIdFarmacia;
    }

    public int getPedIdMedicamento() {
        return pedIdMedicamento;
    }
    public void setPedIdMedicamento(int pedIdMedicamento) {
        this.pedIdMedicamento = pedIdMedicamento;
    }

    public int getPedQtdadeMedicamento() {
        return pedQtdadeMedicamento;
    }
    public void setPedQtdadeMedicamento(int pedQtdadeMedicamento) {
        this.pedQtdadeMedicamento = pedQtdadeMedicamento;
    }

    public Double getPedValorTotal() {
        return pedValorTotal;
    }
    public void setPedValorTotal(Double pedValorTotal) {
        this.pedValorTotal = pedValorTotal;
    }

    public int getPedIdFormaPagamento() {
        return pedIdFormaPagamento;
    }
    public void setPedIdFormaPagamento(int pedIdFormaPagamento) {
        this.pedIdFormaPagamento = pedIdFormaPagamento;
    }

    public int getPedQtdadeParcelas() {
        return pedQtdadeParcelas;
    }
    public void setPedQtdadeParcelas(int pedQtdadeParcelas) {
        this.pedQtdadeParcelas = pedQtdadeParcelas;
    }

    public String getPedDataSolicitacao() {
        return pedDataSolicitacao;
    }
    public void setPedDataSolicitacao(String pedDataSolicitacao) {
        this.pedDataSolicitacao = pedDataSolicitacao;
    }

    public String getPedDataEntregue() {
        return pedDataEntregue;
    }
    public void setPedDataEntregue(String pedDataEntregue) {
        this.pedDataEntregue = pedDataEntregue;
    }

    public String getPedLinkReceita() {
        return pedLinkReceita;
    }
    public void setPedLinkReceita(String pedLinkReceita) {
        this.pedLinkReceita = pedLinkReceita;
    }

    public Double getPedTaxaEntrega() {
        return pedTaxaEntrega;
    }
    public void setPedTaxaEntrega(Double pedTaxaEntrega) {
        this.pedTaxaEntrega = pedTaxaEntrega;
    }

    public int getPedIdDesc() {
        return pedIdDesc;
    }
    public void setPedIdDesc(int pedIdDesc) {
        this.pedIdDesc = pedIdDesc;
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }
    public String getNomeFarmacia() {
        return nomeFarmacia;
    }
    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio = nomeRemedio;
    }
    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
