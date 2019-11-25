package com.mediquei.datamodel;

public class BuscasDataModel {

    /*Dados para as buscas pessoas das pessoas*/
    private final static String TABELA = "med_buscas";

    private final static String idBusca = "busca_id";
    private final static String dscBusca = "busca_dsc";
    private final static String recenteBusca = "busca_recente";

    public static String criarTabela(){
        String queryCriarTabela = "CREATE TABLE IF NOT EXISTS " + TABELA;
        queryCriarTabela += "(";
        queryCriarTabela += idBusca + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += dscBusca + " TEXT, ";
        queryCriarTabela += recenteBusca + " INTEGER";
        queryCriarTabela += ")";

        return queryCriarTabela;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getIdBusca() {
        return idBusca;
    }

    public static String getDscBusca() {
        return dscBusca;
    }


    public static String getRecenteBusca() {
        return recenteBusca;
    }
}
