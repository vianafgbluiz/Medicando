package com.mediquei.datamodel;

public class mDescricaoDataModel {
    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase mDESCRICAO
     */

    private final static String idDesc = "desc_id";
    private final static String idMedicamentoDesc = "desc_id_medic";
    private final static String descricaoDesc = "desc_dsc";
    private final static String comprimidosDesc = "desc_quantidade";
    private final static String receitaDesc = "desc_receita";

    /*Getters*/
    public static String getIdDesc() {
        return idDesc;
    }
    public static String getIdMedicamentoDesc() {
        return idMedicamentoDesc;
    }
    public static String getDescricaoDesc() {
        return descricaoDesc;
    }
    public static String getComprimidosDesc() {
        return comprimidosDesc;
    }
    public static String getReceitaDesc() {
        return receitaDesc;
    }

}
