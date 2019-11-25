package com.mediquei.datamodel;

public class EnderecoDataModel {

    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase ENDERECO
     */

    private final static String id = "end_id";
    private final static String tipo = "end_tipo";
    private final static String idfk = "end_idfk";
    private final static String cep = "end_cep";
    private final static String uf = "end_uf";
    private final static String cidade = "end_cidade";
    private final static String bairro = "end_bairro";
    private final static String logradouro = "end_logradouro";
    private final static String complemento = "end_complemento";
    private final static String numero = "end_numero";

    public static String getId() {
        return id;
    }
    public static String getTipo() {
        return tipo;
    }
    public static String getIdfk() {
        return idfk;
    }
    public static String getCep() {
        return cep;
    }
    public static String getUf() {
        return uf;
    }
    public static String getCidade() {
        return cidade;
    }
    public static String getBairro() {
        return bairro;
    }
    public static String getLogradouro() {
        return logradouro;
    }
    public static String getComplemento() {
        return complemento;
    }
    public static String getNumero() {
        return numero;
    }
}
