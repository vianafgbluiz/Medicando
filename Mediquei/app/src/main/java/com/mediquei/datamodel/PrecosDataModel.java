package com.mediquei.datamodel;

public class PrecosDataModel {

    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase Precos
     */

    private final static String idMedicamento = "p_medicamento";
    private final static String idFarmacia = "p_farmacia";
    private final static String preco = "p_preco";

    /*Getters*/
    public static String getIdMedicamento() {
        return idMedicamento;
    }
    public static String getIdFarmacia() {
        return idFarmacia;
    }
    public static String getPreco() {
        return preco;
    }
}
