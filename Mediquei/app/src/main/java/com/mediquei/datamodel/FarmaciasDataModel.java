package com.mediquei.datamodel;

public class FarmaciasDataModel {

    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase Farmacias
     */

    private final static String idFarmacia = "farm_id";
    private final static String nomeFarmacia = "farm_nome";
    private final static String razaoSocialFarmacia = "farm_razao_social";
    private final static String cnpjFarmacia = "farm_cnpj";
    private final static String telFarmacia = "farm_tel";

    /*Getters*/
    public static String getIdFarmacia() {
        return idFarmacia;
    }
    public static String getNomeFarmacia() {
        return nomeFarmacia;
    }
    public static String getRazaoSocialFarmacia() {
        return razaoSocialFarmacia;
    }
    public static String getCnpjFarmacia() {
        return cnpjFarmacia;
    }
    public static String getTelFarmacia() {
        return telFarmacia;
    }
}
