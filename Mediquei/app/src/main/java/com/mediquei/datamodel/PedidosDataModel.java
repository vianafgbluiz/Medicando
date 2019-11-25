package com.mediquei.datamodel;

public class PedidosDataModel {
    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase PEDIDOS
     */

    private final static String id = "ped_id";
    private final static String idSituacao = "ped_id_situacao";
    private final static String idUser = "ped_id_user";
    private final static String idUserEndereco = "ped_id_user_endereco";
    private final static String idUserBancarios = "ped_id_user_bancarios";
    private final static String idFarmacia = "ped_id_farmacia";
    private final static String idMedicamento = "ped_id_medicamento";
    private final static String idDescricao = "ped_id_descricao";
    private final static String QtdadeMedicamentos = "ped_qtdade_medicamentos";
    private final static String valorTotal = "ped_valor_total";
    private final static String idFormaPagamento = "ped_id_forma_pagamento";
    private final static String qtdadeParcelas = "ped_qtdade_parcelas";
    private final static String dataSolicitacao= "ped_data_solicitacao";
    private final static String dataEntregue = "ped_data_entregue";
    private final static String linkReceita = "ped_link_receita";
    private final static String taxaEntrega = "ped_taxa_entrega";

    /*Getters*/
    public static String getId() {
        return id;
    }

    public static String getIdSituacao() {
        return idSituacao;
    }

    public static String getIdUser() {
        return idUser;
    }

    public static String getIdUserEndereco() {
        return idUserEndereco;
    }

    public static String getIdUserBancarios() {
        return idUserBancarios;
    }

    public static String getIdFarmacia() {
        return idFarmacia;
    }

    public static String getIdMedicamento() {
        return idMedicamento;
    }

    public static String getQtdadeMedicamentos() {
        return QtdadeMedicamentos;
    }

    public static String getValorTotal() {
        return valorTotal;
    }

    public static String getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public static String getQtdadeParcelas() {
        return qtdadeParcelas;
    }

    public static String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public static String getDataEntregue() {
        return dataEntregue;
    }

    public static String getLinkReceita() {
        return linkReceita;
    }

    public static String getIdDescricao() {
        return idDescricao;
    }

    public static String getTaxaEntrega() {
        return taxaEntrega;
    }
}
