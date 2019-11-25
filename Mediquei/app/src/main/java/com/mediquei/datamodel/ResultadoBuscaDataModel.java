package com.mediquei.datamodel;

public class ResultadoBuscaDataModel {
    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase Precos
     */

    private final static String idMedicamento = "medic_id";
    private final static String nomeMedicamento = "medic_nome";

    private final static String idDescricao = "desc_id";
    private final static String descDescricao = "desc_dsc";
    private final static String quantidadeDescricao = "desc_quantidade";
    private final static String receitaDescricao = "desc_receita";

    private final static String idFarmacia = "farm_id";
    private final static String nomeFarmacia = "farm_nome";

    private final static String precoPreco = "p_preco";

    /*Getters*/
    public static String getIdMedicamento() {
        return idMedicamento;
    }
    public static String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public static String getIdDescricao() {
        return idDescricao;
    }
    public static String getDescDescricao() {
        return descDescricao;
    }
    public static String getQuantidadeDescricao() {
        return quantidadeDescricao;
    }
    public static String getReceitaDescricao() {
        return receitaDescricao;
    }

    public static String getIdFarmacia() {
        return idFarmacia;
    }
    public static String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public static String getPrecoPreco() {
        return precoPreco;
    }
}
