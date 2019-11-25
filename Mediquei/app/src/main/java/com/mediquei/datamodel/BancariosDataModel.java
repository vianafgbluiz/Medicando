package com.mediquei.datamodel;

public class BancariosDataModel {
    /* Classe para criar a tabela SQLite e também para
     * devolver os IDS da tabela do banco de Dados Externo
     * Dados da Clase BANCARIOS
     */

    private final static String id = "ban_id";
    private final static String idBandeira = "ban_id_bandeira";
    private final static String numero = "ban_numero";
    private final static String codSeguranca = "ban_codseguranca";
    private final static String nomeCartao = "ban_nome_cartao";
    private final static String idUsuario = "ban_id_usuario";
    private final static String validade = "ban_validade";
    private final static String cpf = "ban_cpf";

    public static String getId() {
        return id;
    }
    public static String getIdBandeira() {
        return idBandeira;
    }
    public static String getNumero() {
        return numero;
    }
    public static String getCodSeguranca() {
        return codSeguranca;
    }
    public static String getNomeCartao() {
        return nomeCartao;
    }
    public static String getIdUsuario() {
        return idUsuario;
    }
    public static String getValidade() {
        return validade;
    }
    public static String getCpf() {
        return cpf;
    }
}
