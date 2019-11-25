package com.mediquei.datamodel;

public class UsuarioDataModel {
    /* Classe para criar a tabela SQLite e também para
    * devolver os IDS da tabela do banco de Dados Externo
    * Dados da Clase USUARIO
    */

    //private final static String TABELA = "med_usuarios";

    private final static String id = "user_id";
    private final static String nome = "user_nome";
    private final static String email = "user_email";
    private final static String senha = "user_senha";
    private final static String ativo = "user_ativo";
    private final static String dataCadastro = "user_data_cadastro";

    public static String getId() {
        return id;
    }

    public static String getNome() {
        return nome;
    }

    public static String getEmail() {
        return email;
    }

    public static String getSenha() {
        return senha;
    }

    public static String getAtivo() {
        return ativo;
    }

    public static String getDataCadastro() {
        return dataCadastro;
    }

}
